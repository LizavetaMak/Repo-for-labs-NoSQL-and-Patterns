package com.musicalinstrument.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.eq;
public class lab6_nosql_mainfunc {


    public static void main(String[] args) {
        aggregateNumberOfBrandPerSimpleInstrument();
        aggregateNumberOfBrandPerElInstrument();
        aggregateTheMostPopularKindsOfPets();
        aggregateTheMostPopularBrandsOfElIns();
        aggregateSortTypePerSpIns();
    }

    public static void aggregateNumberOfBrandPerSimpleInstrument() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("instrument");
        MongoCollection<Document> collection = database.getCollection("simple_instrument");
        MongoCollection<Document> collection2 = database.getCollection("electro_instrument ");

        System.out.println("----------------------------- aggregateNumberOfBrandPerSimpleInstrument ------------------------------");

        long startTime = System.nanoTime();
        Bson group = group("_id", sum("brand_id", 1));
        Bson lookup = lookup("brand", "brand_id", "_id", "brand");
        Bson unwind = unwind("$brand");
        Bson project = project(new Document().append("brand", 1).append("brand_id", 1).append("bra", 1));
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                group,
                lookup,
                unwind,
                project
        ));
        for (Object dbObject : result) {
            System.out.println(dbObject);
        }
        long endTime = System.nanoTime();
        System.out.println("Aggregation of brand per simple instrument took - " + (endTime - startTime));
    }

    public static void aggregateNumberOfBrandPerElInstrument() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("instrument");
        MongoCollection<Document> collection2 = database.getCollection("electro_instrument ");

        System.out.println("----------------------------- aggregateNumberOfBrandPerElmpleInstrument ------------------------------");

        long startTime = System.nanoTime();
        Bson group = group("_id", sum("bra", 1));
        Bson lookup = lookup("brand", "brand_id", "_id", "brand");
        Bson unwind = unwind("$brand");
        Bson project = project(new Document().append("brand", 1).append("brand_id", 1).append("bra", 1));
        AggregateIterable<Document> result = collection2.aggregate(Arrays.asList(
                group,
                lookup,
                unwind,
                project
        ));
        for (Object dbObject : result) {
            System.out.println(dbObject);
        }
        long endTime = System.nanoTime();
        System.out.println("Aggregation of brand per electro instrument took - " + (endTime - startTime));
    }

    public static void aggregateTheMostPopularKindsOfPets() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("instrument");
        MongoCollection<Document> collection = database.getCollection("simple_instrument");
        MongoCollection<Document> collection2 = database.getCollection("electro_instrument ");

        System.out.println("----------------------------- aggregateTheMostPopularBrandsOfIns ------------------------------");

        long startTime = System.nanoTime();
        Bson group = group("brand_id", sum("numberOfIns", 1));
        Bson sort = sort(eq("numberOfIns", -1));
        Bson limit = limit(1);
        Bson lookup = lookup("brand", "brand_id", "_id", "bran");
        Bson unwind = unwind("$bran");
        Bson replaceWith = replaceWith("$bran");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                group, sort, limit, lookup, unwind, replaceWith
        ));
        for (Object dbObject : result) {
            System.out.println(dbObject);
        }
        long endTime = System.nanoTime();
        System.out.println("aggregateTheMostPopularBrandsOfIns - " + (endTime - startTime));
    }

    public static void aggregateTheMostPopularBrandsOfElIns() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("instrument");
        MongoCollection<Document> collection = database.getCollection("electro_instrument ");

        System.out.println("----------------------------- aggregateTheMostPopularBrandsOfElIns ------------------------------");

        long startTime = System.nanoTime();
        Bson group = group("brand_id", sum("numberOfIns", 1));
        Bson sort = sort(eq("numberOfIns", -1));
        Bson limit = limit(1);
        Bson lookup = lookup("brand", "brand_id", "_id", "bran");
        Bson unwind = unwind("$bran");
        Bson replaceWith = replaceWith("$bran");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                group, sort, limit, lookup, unwind, replaceWith
        ));
        for (Object dbObject : result) {
            System.out.println(dbObject);
        }
        long endTime = System.nanoTime();
        System.out.println("aggregateTheMostPopularBrandsOfElIns - " + (endTime - startTime));
    }
    public static void aggregateSortTypePerSpIns() {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase database = mongoClient.getDatabase("instrument");
        MongoCollection<Document> collection = database.getCollection("simple_instrument ");

        System.out.println("----------------------------- aggregateSortTypeOfSpIns ------------------------------");

        long startTime = System.nanoTime();
        Bson group = group("type", sum("numberOftype", 1));
        Bson sort = sort(eq("numberOftype", -1));
        Bson unwind = unwind("$characteristics");
        Bson project = project(new Document().append("brand", 1).append("type", 1).append("numberOftype", 1).append("characteristics", 1));
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                group, sort, unwind, project
        ));
        for (Object dbObject : result) {
            System.out.println(dbObject);
        }
        long endTime = System.nanoTime();
        System.out.println("aggregateSortTypeOfSpIns " + (endTime - startTime));
    }


}


