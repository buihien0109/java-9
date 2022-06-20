package com.example.helloworld;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component(value = "beanUserSecond")
public class User {
    private int id;
    private String name;

    public void sayHello() {
        System.out.println("Xin chào");
    }
}
