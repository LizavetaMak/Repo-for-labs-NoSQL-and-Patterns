package com.musicalinstrument.demo.nosql.repository;

import com.musicalinstrument.demo.nosql.jpa.sp_instrument_nosql;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Sp_instrumentRepNosql extends MongoRepository<sp_instrument_nosql, ObjectId> {
    @Override
    void deleteAll();
}
