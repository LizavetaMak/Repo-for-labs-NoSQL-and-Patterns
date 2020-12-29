package com.musicalinstrument.demo.nosql.repository;

import com.musicalinstrument.demo.nosql.jpa.brand_nosql;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


    public interface BrandRepNosql extends MongoRepository<brand_nosql, ObjectId> {
        List <brand_nosql> findAll();
        void deleteById (ObjectId id);
        brand_nosql findTopByOrderByIdDesc();
    }

