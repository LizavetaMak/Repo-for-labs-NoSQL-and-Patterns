package com.musicalinstrument.demo;

import com.musicalinstrument.demo.jpa.Brand;
import com.musicalinstrument.demo.jpa.Electro_Instrument;
import com.musicalinstrument.demo.jpa.Music_Instrument;
import com.musicalinstrument.demo.jpa.Simple_Instrument;
import com.musicalinstrument.demo.nosql.jpa.brand_nosql;
import com.musicalinstrument.demo.nosql.jpa.el_instrument_nosql;
import com.musicalinstrument.demo.nosql.jpa.sp_instrument_nosql;
import com.musicalinstrument.demo.nosql.repository.BrandRepNosql;
import com.musicalinstrument.demo.nosql.repository.El_instrumentRepNosql;
import com.musicalinstrument.demo.nosql.repository.Sp_instrumentRepNosql;
import com.musicalinstrument.demo.repository.BrandRepository;
import com.musicalinstrument.demo.repository.ElectroInstrumentRepository;
import com.musicalinstrument.demo.repository.MusicInstrumentRepository;
import com.musicalinstrument.demo.repository.SimpleInstrumentRepository;
import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MigrationService {
    public void migrateDataFromMongodbToMysql(
            BrandRepNosql brandRepNosql,
            Sp_instrumentRepNosql sl_instrumentRepNosql,
            BrandRepository brandRepository,
            SimpleInstrumentRepository simpleInstrumentRepository,
            El_instrumentRepNosql el_instrumentRepNosql,
            ElectroInstrumentRepository electroInstrumentRepository,
            MusicInstrumentRepository musicInstrumentRepository)

    {
        // миграция брендов
       List<brand_nosql> brands = brandRepNosql.findAll();
       HashMap<ObjectId, Long> brandsIdsMap = new HashMap<>();
        for (brand_nosql brand : brands) {
            Brand brand_mysql= new Brand();
            brand_mysql.setName(brand.getName());
            brand_mysql.setCountry("gfvjgf"); // потом поменять "gfvjgf" на brand.getCountry()
             brandRepository.save(brand_mysql);
            Long newId =brandRepository.lastID();
            brandsIdsMap.put(brand.getId(), newId);
        }

          // миграция простых инструментов
        List<sp_instrument_nosql> sp_ins = sl_instrumentRepNosql.findAll();
        HashMap<ObjectId, Long> sp_insIdsMap = new HashMap<>();

        for (sp_instrument_nosql sp : sp_ins) {
            Simple_Instrument sp_mysql= new Simple_Instrument();
            sp_mysql.setMaterial(sp.getMaterial());
            sp_mysql.setType(sp.getType());
            Music_Instrument music_mysql= new Music_Instrument();
            music_mysql.setName_type("simple");
            music_mysql.setName(sp.getName());
            music_mysql.setPrice(sp.getPrice());
            music_mysql.setSize(sp.getSize());
            Long id=brandsIdsMap.get(sp.getBrand_id());
            Brand d=brandRepository.findAllById(id);
            music_mysql.setBrand(d);
            for(Map.Entry<String, String> characteristic: sp.getCharacteristics().entrySet()) {
                String key = characteristic.getKey();
                switch (key) {
                    case "numOfString":
                        sp_mysql.setNum_srtings_stringed(Integer.valueOf(characteristic.getValue()));
                        break;
                    case "lenght_string":
                        sp_mysql.setLenght_string_stringed(Double.valueOf(characteristic.getValue()));
                        break;
                    case "raspro":
                        sp_mysql.setRaspro_keyboard(characteristic.getValue());
                        break;
                    case "numOfPedal":
                        sp_mysql.setNum_pedal_keyboard(Integer.valueOf(characteristic.getValue()));
                        break;
                    case "sours":
                        sp_mysql.setSours_wind(characteristic.getValue());
                        break;
                }
            }
            //simpleInstrumentRepository.save(sp_mysql);
            Long newId =simpleInstrumentRepository.lastID();
            sp_insIdsMap.put(sp.getId(), newId);
            Simple_Instrument s;
            s=simpleInstrumentRepository.findAllByIdSimpleInsrtument(newId);
            music_mysql.setSimpleInstrument(s);
          //  musicInstrumentRepository.save(music_mysql);
        }

        // миграция электроинструментов
        List<el_instrument_nosql> el_ins = el_instrumentRepNosql.findAll();
        HashMap<ObjectId, Long> el_insIdsMap = new HashMap<>();

        for (el_instrument_nosql el : el_ins) {
            Electro_Instrument el_mysql= new Electro_Instrument();
            Music_Instrument music_mysql= new Music_Instrument();
            music_mysql.setName_type("electro");
            music_mysql.setName(el.getName());
            music_mysql.setPrice(el.getPrice());
            music_mysql.setSize(el.getSize());
            Long id=brandsIdsMap.get(el.getBrand_id());
            Brand d=brandRepository.findAllById(id);
            music_mysql.setBrand(d);

            el_mysql.setPower(el.getPower());
            el_mysql.setFrequency(el.getFrequensy());

            for(Map.Entry<String, String> characteristic: el.getCharacteristics().entrySet()) {
                String key = characteristic.getKey();
                switch (key) {
                    case "form":
                        el_mysql.setForm_eguitar(characteristic.getValue());
                        break;
                    case "fret_board":
                        el_mysql.setFret_board_eviolin(characteristic.getValue());
                        break;
                    case "motot_tecnic":
                        el_mysql.setMolot_technic_dpiano(characteristic.getValue());
                        break;
                    case "numOfString_eguitar":
                        el_mysql.setNum_srtings_eguitar(Integer.valueOf(characteristic.getValue()));
                        break;
                    case "numOfString_eviolin":
                        el_mysql.setNum_srtings_eviolin(Integer.valueOf(characteristic.getValue()));
                        break;
                    case "polifonia":
                        el_mysql.setPolifonia_dpiano(characteristic.getValue());
                        break;
                    case "type_egitar":
                        el_mysql.setType_eguitar(characteristic.getValue());
                        break;
                }
            }
            electroInstrumentRepository.save(el_mysql);
            Long newId =simpleInstrumentRepository.lastID();
            sp_insIdsMap.put(el.getId(), newId);


            Electro_Instrument e;
            e=electroInstrumentRepository.findAllByIdElectroInsrtument(newId);
            music_mysql.setElectroInstrument(e);
            musicInstrumentRepository.save(music_mysql);
        }


    }

    public void migrateDataFromMysqlToMongodb(
            BrandRepNosql brandRepNosql,
            Sp_instrumentRepNosql sl_instrumentRepNosql,
            BrandRepository brandRepository,
            SimpleInstrumentRepository simpleInstrumentRepository,
            El_instrumentRepNosql el_instrumentRepNosql,
            ElectroInstrumentRepository electroInstrumentRepository,
            MusicInstrumentRepository musicInstrumentRepository) {

        // миграция брендов
        List<Brand> brands = brandRepository.findAll();
        HashMap<Long,ObjectId> brandsIdsMap = new HashMap<>();
        for (Brand brand : brands) {
            brand_nosql brand_nosql= new brand_nosql();
            brand_nosql.setName(brand.getName());
            brand_nosql.setCountry(brand.getCountry());
            brandRepNosql.save(brand_nosql);
            ObjectId newId =brandRepNosql.findTopByOrderByIdDesc().getId();
            brandsIdsMap.put(brand.getId(), newId);
        }




        // миграция  инструментов
        List< Music_Instrument> music_mysql = musicInstrumentRepository.findAll();
        for (Music_Instrument mu : music_mysql) {
            if(mu.getSimpleInstrument()!=null) {
                sp_instrument_nosql sp_nosql= new sp_instrument_nosql();
                sp_nosql.setNameOfType("simple");
                sp_nosql.setName(mu.getName());
                sp_nosql.setPrice(mu.getPrice());
                sp_nosql.setSize(mu.getSize());
                Simple_Instrument sp = mu.getSimpleInstrument();
                sp_nosql.setMaterial(sp.getMaterial());
                sp_nosql.setType(sp.getType());
                HashMap<String, String> characteristics = new HashMap<>();
                if(sp.getSours_wind()!=null)
                    characteristics.put("sours",sp.getSours_wind() );
                if(sp.getLenght_string_stringed()!=null)
                    characteristics.put("lenght_string",sp.getLenght_string_stringed().toString() );
                if(sp.getNum_srtings_stringed()!=null)
                    characteristics.put("numOfString",sp.getNum_srtings_stringed().toString());
                if(sp.getRaspro_keyboard()!=null)
                    characteristics.put("paspo",sp.getRaspro_keyboard().toString());
                if(sp.getNum_pedal_keyboard()!=null)
                    characteristics.put("numOfPedal",sp.getNum_pedal_keyboard().toString());
                sp_nosql.setCharacteristics(characteristics);
                ObjectId id=brandsIdsMap.get(mu.getBrand().getId());
                sp_nosql.setBrand_id(id);
                sp_nosql.setBrandName(mu.getBrand().getName());
                sp_nosql.setBrand_country(mu.getBrand().getCountry());
                sl_instrumentRepNosql.save(sp_nosql);
            }

            if(mu.getElectroInstrument()!=null) {
                el_instrument_nosql el_nosql= new el_instrument_nosql();
                el_nosql.setNameOfType("electro");
                el_nosql.setName(mu.getName());
                el_nosql.setPrice(mu.getPrice());
                el_nosql.setSize(mu.getSize());
                Electro_Instrument el = mu.getElectroInstrument();
                el_nosql.setFrequensy(el.getFrequency());
                el_nosql.setPower(el.getPower());
                HashMap<String, String> characteristics = new HashMap<>();
                if(el.getForm_eguitar()!=null)
                    characteristics.put("form",el.getForm_eguitar() );
                if(el.getFret_board_eviolin()!=null)
                    characteristics.put("fret_board",el.getFret_board_eviolin() );
                if(el.getNum_srtings_eguitar()!=null)
                    characteristics.put("numOfString_eguitar",el.getNum_srtings_eguitar().toString());
                if(el.getNum_srtings_eviolin()!=null)
                    characteristics.put("numOfString_eviolin",el.getNum_srtings_eviolin().toString());
                if(el.getMolot_technic_dpiano()!=null)
                    characteristics.put("motot_tecnic",el.getMolot_technic_dpiano());
                if(el.getPolifonia_dpiano()!=null)
                    characteristics.put("polifonia",el.getPolifonia_dpiano());
                el_nosql.setCharacteristics(characteristics);
                ObjectId id=brandsIdsMap.get(mu.getBrand().getId());
                el_nosql.setBrand_id(id);
                el_nosql.setBrandName(mu.getBrand().getName());
                el_nosql.setBrand_country(mu.getBrand().getCountry());
                el_instrumentRepNosql.save(el_nosql);
            }




        }

    }





}
