package com.medron.protobuf;


import com.medron.models.Person;

public class PersonDemo {
    public static void main(String[] args) {
        Person.newBuilder().setName("Metehan").setAge(27).build();

    }
}
