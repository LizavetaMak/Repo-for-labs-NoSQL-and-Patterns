package com.musicalinstrument.demo.nosql.jpa;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "brand")
public class brand_nosql {
    @Id
    private ObjectId id;

    private String name;
    private String country;

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "brand_nosql{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
