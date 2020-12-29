package com.musicalinstrument.demo.TTPP.Dao_for_brands;

import com.musicalinstrument.demo.TTPP.common.EventManager;
import com.musicalinstrument.demo.TTPP.common.IEventListener;
import com.musicalinstrument.demo.jpa.Brand;
import com.musicalinstrument.demo.jpa.Role;
import com.musicalinstrument.demo.jpa.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDAO implements IDAO{
    private static final String url = "jdbc:mysql://localhost:3306/lab4?useUnicode=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "1234";
    private static Connection connection = null;
    private static MySQLDAO instance = null;
    private EventManager events;


    public static String BRAND_CHANGE = "BRAND_CHANGE";
    public static String NEW_BRAND = "NEW_BRAND";
    public static String DELETE_BRAND = "DELETE_BRAND";

    private MySQLDAO() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        MySQLDAO.connection = DriverManager.getConnection(MySQLDAO.url, MySQLDAO.user, MySQLDAO.password);
        events = new EventManager();
    }

    @Override
    public void subscribe (String eventType, IEventListener listener) {
        this.events.subscribe(eventType, listener);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        connection.close();
    }

    public static synchronized MySQLDAO getInstance() throws NoSuchMethodException, IllegalAccessException, InstantiationException, SQLException, InvocationTargetException, ClassNotFoundException {
        if (MySQLDAO.instance == null)
            MySQLDAO.instance = new MySQLDAO();

        return MySQLDAO.instance;
    }
    @Override
    public List<Brand> getAllBrands (ERole userRole) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Brand;");

        ResultSet result = statement.executeQuery();

        List<Brand> brands = new ArrayList<>();

        while (result.next()) {
            brands.add(new Brand(
                    result.getString("name"),
                    result.getString("country"),
                    result.getLong("id")

            ));
        }

        return brands;
    }

    @Override
    public void deleteBrand (Brand brand, ERole userRole) throws Exception {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM Brand WHERE id = ?"
        );

        statement.setLong(1, brand.getId());

        lockTableWrite("Brand");

        statement.execute();

        events.notify(DELETE_BRAND, this);

        unlockTables();
    }

    @Override
    public void updateBrand (Brand brand, ERole userRole) throws Exception {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE Brand SET name = ? WHERE id = ?"
        );

        statement.setString(1, brand.getName());
        statement.setLong(2, brand.getId());

        statement.execute();

        events.notify(BRAND_CHANGE, this);
    }

    @Override
    public Long createBrand (Brand brand, ERole userRole) throws Exception {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT Brand(name,country) VALUES(?,?)"

        );

        statement.setString(1, brand.getName());
        statement.setString(2, "county");
        statement.execute();

        events.notify(NEW_BRAND, this);

        return getLastInsertID();
    }


    private Long getLastInsertID() throws Exception {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
        resultSet.next();

        return resultSet.getLong(1);
    }

    private void lockTableWrite(String tableName) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("LOCK TABLES " + tableName + " WRITE;");
    }

    private void unlockTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("UNLOCK TABLES;");
    }
    @Override
    public User login(String login, String password) throws Exception {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE login = ? AND password = ?;");

        statement.setString(1, login);
        statement.setString(2, password);


        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            User user = new User();
            user.setUserId(resultSet.getLong("userId"));
            user.setFkRoleId(resultSet.getInt("fkRoleId"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));

            return user;
        }

        throw new Exception("User not found!");
    }

    @Override
    public User register(String login, String password, Role role) throws Exception {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO User (login, password, fkRoleId) VALUE(?, ?, ?);");

        statement.setString(1, login);
        statement.setString(2, password);
        statement.setLong(3, role.getRoleId());

        statement.execute();

        Long id = getLastInsertID();

        User user = new User();
        user.setUserId(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setFkRoleId(role.getRoleId());

        return user;
    }

}
