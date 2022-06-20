package com.example.helloworld;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("motorbike")
public class Motorbike implements Vehicle{
    @Override
    public void move() {
        System.out.println("Di chuyển bằng xe máy");
    }
}
