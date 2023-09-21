package com.medron.protobuf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.medron.models.Person;

public class PerformanceTest {
    public static void main(String[] args) {

        //json serialize-deserialize
        Runnable json =() ->{
            JPerson jPerson = new JPerson();
            jPerson.setName("Metehan");
            jPerson.setAge(27);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                final byte[] bytes = objectMapper.writeValueAsBytes(jPerson);
                final JPerson jPerson1 = objectMapper.readValue(bytes, JPerson.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };





        Runnable proto = () -> {
            Person person = Person.newBuilder().setName("Metehan").setAge(27).build();
            final byte[] bytes = person.toByteArray();
            try {
                Person person1 = Person.parseFrom(bytes);
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException(e);
            }
        };



        runPerformanceTest(json,"JSON");
        runPerformanceTest(proto,"PROTO");
    }


    private static void runPerformanceTest(Runnable runnable, String method){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            runnable.run();
        }
        long finish = System.currentTimeMillis();
        System.out.println("Method: "+ method+ " "+ (finish-start) +"ms");
    }
}
