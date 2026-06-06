package com.java17.interview.prepartion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@SpringBootApplication
public class StreamAPILearning {

    public static void main(String[] args) {
        SpringApplication.run(StreamAPILearning.class, args);


        List<DTO> listOfDTO = new ArrayList<>();
        DTO d1 = new DTO(1, "abhimanyu");
        DTO d2 = new DTO(2, "Java");
        listOfDTO.add(d1);
        listOfDTO.add(d2);
      //  List<String> listOfName = listOfDTO.stream().collect(Collectors.groupingBy(DTO::getName), Collectors.counting()));

        List<String> list = listOfDTO.stream()    .map(DTO::getName)    .collect(Collectors.toList());
        System.out.println(list);

    }
}

class DTO{

   private Integer id;
   private String name;

   public DTO(){}

    public DTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}