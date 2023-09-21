package com.medron.protobuf;


import com.medron.models.Person;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PersonDemo {
    public static void main(String[] args) throws IOException {
        final Person metehan = Person.newBuilder().setName("Metehan").setAge(27).build();
        Path path = Paths.get("sam.yer");
//        Files.write(path,metehan.toByteArray());
        byte[] bytes = Files.readAllBytes(path);

    }
}
