package com.musicalinstrument.demo.nosql.repository;

import com.musicalinstrument.demo.nosql.jpa.el_instrument_nosql;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface El_instrumentRepNosql extends MongoRepository<el_instrument_nosql, ObjectId> {

}
