package com.journaldev.spring.mongodb.main;
 
import java.util.List;
import java.net.UnknownHostException;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.journaldev.spring.mongodb.model.Person;
import com.mongodb.MongoClient;
 
public class SpringDataMongoDBMain {
 
    public static final String DB_NAME = "journaldev";
    public static final String PERSON_COLLECTION = "Person";
    public static final String MONGO_HOST = "localhost";
    public static final int MONGO_PORT = 27017;
 
    public static void main(String[] args) throws RuntimeException {
        try {
            MongoClient mongo = new MongoClient(
                    MONGO_HOST, MONGO_PORT);
            MongoOperations mongoOps = new MongoTemplate(mongo, DB_NAME);
            int id=1000;
            Person p = new Person(String.valueOf(++id), "PankajKr", "Bangalore, India");
            mongoOps.insert(p, PERSON_COLLECTION);
            
            
            mongoOps.insert(new Person(String.valueOf(++id), "Amit", "Pune, India"), PERSON_COLLECTION);
            mongoOps.insert(new Person(String.valueOf(++id), "Suraj", "Chennai, India"), PERSON_COLLECTION);
            mongoOps.insert(new Person(String.valueOf(++id), "Suman", "Hydrabad, India"), PERSON_COLLECTION);
            mongoOps.insert(new Person(String.valueOf(++id), "Kavya", "Jaipur, India"), PERSON_COLLECTION);

          
 
// 
//            System.out.println(p1);
            
            List<Person> persons= mongoOps.findAll(Person.class, PERSON_COLLECTION);
            for (Person person : persons) {
				System.out.println(person);
			} 
            
            Person p1 = mongoOps.findOne(
            		new Query(Criteria.where("name").is("PankajKr")),
            		Person.class, PERSON_COLLECTION);
            p1.setName("Sachine");
            mongoOps.save(p1,PERSON_COLLECTION);
            
            System.out.println("--------------");
            persons= mongoOps.findAll(Person.class, PERSON_COLLECTION);
            for (Person person : persons) {
				System.out.println(person);
			} 
            mongoOps.dropCollection(PERSON_COLLECTION);
            mongo.close();
             
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
 
}
