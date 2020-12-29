import com.github.javafaker.Faker;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.musicalinstrument.demo.jpa.Simple_Instrument;
import org.bson.Document;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class lab3_nosql_mainfunc_version2
{

/*
   // private SimpleInstrumentRepository simpleInstrumentRepository;
    public main1(SimpleInstrumentRepository simpleInstrumentRepository) {
        this.simpleInstrumentRepository = simpleInstrumentRepository;
    }*/
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/lab4?useUnicode=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "1234";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    public static void main(String[] args) {


        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("instrument");
        MongoCollection<Document> collection = database.getCollection("simple_instrument");

        List<TestResultsObject> results = new ArrayList<>();
        int[] tests = {1};
        for (int numberOfEntries : tests) {
            TestResultsObject testResult = new TestResultsObject();
            testResult.numberOfEntries = numberOfEntries;

            long startTimeOfInsert = System.nanoTime();
            while (numberOfEntries > 0) {
                Faker faker = new Faker();
                Document sp_inst = new Document()
                        .append("name", faker.music().instrument())
                        .append("material", faker.commerce().material())
                        .append("size", Math.random() * 3)
                        .append("price", Math.random() * 10000)
                        .append("nameOfType", "simple")
                        .append("type", "type")
                        .append("characteristics", new Document()
                                .append("numOfString", 5)
                                .append("lenght_string", 0.95));
                collection.insertOne(sp_inst);
                numberOfEntries--;
            }
            long endTimeOfInsert = System.nanoTime();
            testResult.timeToInsertMs = endTimeOfInsert - startTimeOfInsert;

            results.add(testResult);

        }

        for (TestResultsObject result: results) {
            System.out.println("===================================================");
            System.out.println("The insert of " + result.numberOfEntries + " took "
                    + result.timeToInsertMs );
            System.out.println("===================================================");
        }


        String query = "\"INSERT INTO person (type, material, rezonator,num_srtings_stringed,lenght_string_stringed) VALUES (?,?,?,?,?)\")\n" +
                "                .setParameter(1, ins.getType())\n" +
                "                .setParameter(2, ins.getMaterial())\n" +
                "                .setParameter(3, ins.getRezonator())\n" +
                "                .setParameter(4, ins.getNum_srtings_stringed())\n" +
                "                .setParameter(5, ins.getLenght_string_stringed())\n" +
                "                .executUpdate();";





        try {
            String sql = "INSERT INTO simple_instrument (type, material, rezonator,num_srtings_stringed,lenght_string_stringed) VALUES (?,?,?,?,?)";
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            for (int numberOfEntries : tests) {
                TestResultsObject testResult = new TestResultsObject();
                testResult.numberOfEntries = numberOfEntries;

                long startTimeOfInsert = System.nanoTime();
                while (numberOfEntries > 0) {
                    Faker faker = new Faker();

                    Simple_Instrument sp_ins=new Simple_Instrument();
                    sp_ins.setMaterial(faker.commerce().material());
                    sp_ins.setType("type");
                    sp_ins.setRezonator((int) (Math.random() * 3));
                    sp_ins.setNum_srtings_stringed(5);
                    sp_ins.setLenght_string_stringed(90.5);

                    preparedStatement.setString(1, sp_ins.getType());
                    preparedStatement.setString( 2,sp_ins.getMaterial());
                      preparedStatement.setInt(3,sp_ins.getRezonator());
                      preparedStatement.setInt( 4, sp_ins.getNum_srtings_stringed());
                     preparedStatement.setDouble( 5, sp_ins.getLenght_string_stringed());
                    int rows = preparedStatement.executeUpdate();

                    numberOfEntries--;

                }

                long endTimeOfInsert = System.nanoTime();
                testResult.timeToInsertMs = endTimeOfInsert - startTimeOfInsert;

                long startTimeOfSelect = System.nanoTime();
                String query2 = "select * from simple_instrument where material='Copper' ";
                rs = stmt.executeQuery(query2);
                long endTimeOfSelect = System.nanoTime();

                testResult.timeToSelectMs = endTimeOfSelect - startTimeOfSelect;

                results.add(testResult);

            }

            for (TestResultsObject result: results) {
                System.out.println("===================================================");
                System.out.println("The insert of " + result.numberOfEntries + " took "
                        + result.timeToInsertMs + " took "
                        + result.timeToInsertMs + " the search took " + result.timeToSelectMs );
                System.out.println("===================================================");
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }


        }


    }


}
