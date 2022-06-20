package com.example.helloworld;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;
    private String name;

//    @Autowired
//    @Qualifier("bus")
    // private Vehicle vehicle;

//    @Autowired
//    public Student(@Qualifier("motorbike") Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }

//    @Autowired
//    public void setVehicle(@Qualifier("motorbike") Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }
}
