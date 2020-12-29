package com.musicalinstrument.demo.nosql.jpa;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;
@Document(collection = "simple_instrument")
public class sp_instrument_nosql {
    @Id
    private ObjectId id;
    private String type;
    private String   material;
    private String nameOfType;
    private double   size;
    private double price;
    private int rezonator;
    private String   name;
    private ObjectId brand_id;
    private String   brandName;
    private String   brand_country;
    private Map<String, String> characteristics = new HashMap<>();

    public String getBrand_country() {
        return brand_country;
    }

    public void setBrand_country(String brand_country) {
        this.brand_country = brand_country;
    }

    public int getRezonator() {
        return rezonator;
    }

    public void setRezonator(int rezonator) {
        this.rezonator = rezonator;
    }

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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getNameOfType() {
        return nameOfType;
    }

    public void setNameOfType(String nameOfType) {
        this.nameOfType = nameOfType;
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



    public Map<String, String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Map<String, String> characteristics) {
        this.characteristics = characteristics;
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

    @Override
    public String toString() {
        return "sp_instrument_nosql{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", material='" + material + '\'' +
                ", nameOfType='" + nameOfType + '\'' +
                ", size=" + size +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", brand_name='" + brandName + '\'' +
                ", characteristics=" + characteristics +
                '}';
    }
}
