package com.musicalinstrument.demo;

import com.github.javafaker.Faker;
import com.musicalinstrument.demo.TTPP.builders.Brand_Build;
import com.musicalinstrument.demo.TTPP.builders.Electro_Instrument_Build;
import com.musicalinstrument.demo.TTPP.builders.Music_Instrument_Build;
import com.musicalinstrument.demo.TTPP.builders.Simple_Instrument_Build;
import com.musicalinstrument.demo.jpa.Brand;
import com.musicalinstrument.demo.jpa.Electro_Instrument;
import com.musicalinstrument.demo.jpa.Music_Instrument;
import com.musicalinstrument.demo.jpa.Simple_Instrument;
import com.musicalinstrument.demo.nosql.jpa.brand_nosql;
import com.musicalinstrument.demo.nosql.jpa.sp_instrument_nosql;
import com.musicalinstrument.demo.nosql.repository.BrandRepNosql;
import com.musicalinstrument.demo.nosql.repository.El_instrumentRepNosql;
import com.musicalinstrument.demo.nosql.repository.Sp_instrumentRepNosql;
import com.musicalinstrument.demo.repository.*;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) throws Exception {
       SpringApplication.run(DemoApplication.class, args);
    }

   @Bean("lab3 nosql")
   CommandLineRunner init(SimpleInstrumentRepository simpleInstrumentRepository,Sp_instrumentRepNosql sl_instrumentRepNosql, BrandRepNosql brandRepNosql) {
        Faker faker = new Faker();

        return args -> {
            Simple_Instrument sp_ins = new Simple_Instrument();
            sp_ins.setMaterial(faker.commerce().material());
            sp_ins.setType("type");
            sp_ins.setRezonator((int) (Math.random() * 12));
            sp_ins.setNum_srtings_stringed(1);
            sp_ins.setLenght_string_stringed(90.5);

          // simpleInstrumentRepository.findAll().forEach(System.out::println);
          // simpleInstrumentRepository.save(sp_ins);

            sp_instrument_nosql s= new sp_instrument_nosql();
            s.setName("tessst");
            s.setMaterial(faker.commerce().material());
            s.setType("type");
            s.setRezonator((int) (Math.random() * 12));
            Map<String, String> map = new HashMap<>();
            map.put("NumOfsrting_stringed", "1");
            map.put("Lenght_string_stringed", "90.5");
            s.setCharacteristics(map);

            s.setBrand_id(new ObjectId("5fda78935736c02b315ee2f1"));
            s.setBrandName("brand_name");
          // sl_instrumentRepNosql.save(s);
          //   brandRepNosql.findAll().forEach(System.out::println);
            brand_nosql brand_nosql = new brand_nosql();
            brand_nosql.setName("test2");
            ///brandRepNosql.insert(brand_nosql);
           // sl_instrumentRepNosql.findAll().forEach(System.out::println);
        };


    }

    @Bean("lab4 nosql")
    CommandLineRunner init(BrandRepNosql brandRepNosql, Sp_instrumentRepNosql sl_instrumentRepNosql, BrandRepository brandRepository, SimpleInstrumentRepository simpleInstrumentRepository, El_instrumentRepNosql el_instrumentRepNosql, ElectroInstrumentRepository electroInstrumentRepository, MusicInstrumentRepository musicInstrumentRepository, RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            MigrationService m= new MigrationService();
          // m.migrateDataFromMongodbToMysql(brandRepNosql,sl_instrumentRepNosql,brandRepository,  simpleInstrumentRepository, el_instrumentRepNosql, electroInstrumentRepository, musicInstrumentRepository);
          // m.migrateDataFromMysqlToMongodb(brandRepNosql,sl_instrumentRepNosql,brandRepository,  simpleInstrumentRepository, el_instrumentRepNosql, electroInstrumentRepository, musicInstrumentRepository);


        };
    }

    @Bean("lab1-2 ttpp")
    CommandLineRunner init( BrandRepository brandRepository, SimpleInstrumentRepository simpleInstrumentRepository,  ElectroInstrumentRepository electroInstrumentRepository, MusicInstrumentRepository musicInstrumentRepository) {
        return args -> {

            Simple_Instrument_Build spBuilder = new Simple_Instrument_Build();
            Simple_Instrument sp = spBuilder
                    .withMaterial("metall")
                    .withRezonator(5)
                    .withLenght_string_stringed(95)
                    .withNum_srtings_stringed(5)
                    .build();

           // System.out.println(sp);
           //simpleInstrumentRepository.findAll().forEach(System.out::println);
            Electro_Instrument_Build elBuilder = new Electro_Instrument_Build();
            Electro_Instrument el  = elBuilder
                    .withFrequency(110)
                    .withPover(50)
                    .withNum_srtings_eviolin(3)
                    .build();
          //  System.out.println(el);

        Brand_Build brand_build = new Brand_Build();
        Brand br =  brand_build
                .withCounty("Kharkiv")
                .withName("gusi")
                .build();
        //System.out.println(br);
            Music_Instrument_Build msBuild = new Music_Instrument_Build ();
            Music_Instrument  ms= msBuild
                    .withBrand(br)
                    .withElectroInstrument(el)
                    .withPrice(1000.00)
                    .withSize(110.00)
                    .withName_type("electro")
                    .withName("скрипка")
                    .build();
           // System.out.println(ms);
        };
    }

   /*@Bean("lab5 nosql")
    CommandLineRunner init( BrandRepNosql brandRepNosql) {
        Faker faker = new Faker();


        return args -> {

            long insertTimeStart = System.nanoTime();
            long sleepTime = 0;
            int numberOfDocumentsToGenerate = 10000;
            List<brand_nosql> brands = new ArrayList<>();

          while (numberOfDocumentsToGenerate > 0 ) {
                if (numberOfDocumentsToGenerate == 5000) {
                    sleepTime = System.nanoTime();
                    System.out.println("Kill the mongod intance, I will wait 20 seconds");
                    Thread.sleep(20000);
                    sleepTime = System.nanoTime() - sleepTime;
                }



                int numberOfRetriesBeforeQuit = 3;

                while (numberOfRetriesBeforeQuit > 0) {
                    try {
                        brand_nosql brand_nosql = new brand_nosql();
                        brand_nosql.setName("test2"+ (int) (Math.random() * 100));
                        brand_nosql.setCountry(faker.address().country());
                       // brandRepNosql.insert(brand_nosql);
                        brands.add(brand_nosql);
                        numberOfRetriesBeforeQuit = 0;
                    } catch (Exception error) {
                        System.out.println(error);

                        if (numberOfRetriesBeforeQuit - 1 == 0) {
                            System.out.println("Failed to write a document. Exiting from the program.");
                            return;
                        }

                        Thread.sleep(1000);

                        numberOfRetriesBeforeQuit--;
                    }
                }

                numberOfDocumentsToGenerate--;
            }
            long insertEndTime = System.nanoTime();

            System.out.println("Insert took " + ((insertEndTime - insertTimeStart) - sleepTime ));
            long selectStartTime = System.nanoTime();
            List <brand_nosql> DbBrands = brandRepNosql.findAll();
            long selectEndTime = System.nanoTime();

            System.out.println("Select took " + (selectEndTime - selectStartTime));
            System.out.println("Received: "  + DbBrands.size() + ". Inserted: 10000");

            brands.forEach((brand_nosql brand) -> {
                boolean foundTheSameBrand = false;
                for (brand_nosql DbBrand: DbBrands) {
                    if (DbBrand.getId().equals(brand.getId())) {
                        foundTheSameBrand = true;

                        if (!DbBrand.getName().equals(brand.getName())) {
                            System.out.println("Brand with id = " + brand.getId() + " has incorrect saved brand name");

                            if (!DbBrand.getCountry().equals(brand.getCountry())) {
                                System.out.println("Brand with id = " + brand.getId() + " has incorrect saved brand country");
                            }
                        }

                        break;
                    }
                }

                if (!foundTheSameBrand) {
                    System.out.println("Can not find the brand with id = " + brand.getId());
                }
            });
        };

    }
    */

}
