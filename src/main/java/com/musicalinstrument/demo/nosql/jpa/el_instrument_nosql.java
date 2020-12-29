package com.musicalinstrument.demo.nosql.jpa;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;


@Document(collection = "electro_instrument ")
public class el_instrument_nosql {
    @Id
    private ObjectId id;
    private String type;
    private int frequensy;
    private String nameOfType;
    private int power;
    private double   size;
    private double price;
    private String   name;
    private ObjectId brand_id;
    private String   brandName;
    private String   brand_country;

    public String getBrand_country() {
        return brand_country;
    }

    public void setBrand_country(String brand_country) {
        this.brand_country = brand_country;
    }

    private Map<String, String> characteristics = new HashMap<>();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFrequensy() {
        return frequensy;
    }

    public void setFrequensy(int frequensy) {
        this.frequensy = frequensy;
    }

    public String getNameOfType() {
        return nameOfType;
    }

    public void setNameOfType(String nameOfType) {
        this.nameOfType = nameOfType;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectId getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(ObjectId brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Map<String, String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Map<String, String> characteristics) {
        this.characteristics = characteristics;
    }
}
