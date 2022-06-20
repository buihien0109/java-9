package com.example.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class HelloWorldApplication {

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean(name = "beanUserFirst")
    public User user() {
        return new User(2, "Trần Văn B");
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HelloWorldApplication.class, args);

        // Lấy bean
        Random rd = context.getBean(Random.class);
        System.out.println(rd.nextInt(1000));

        // Lấy bean student
        User user = context.getBean("beanUserFirst", User.class);
        System.out.println(user.getName());

        User user1 = context.getBean("beanUserSecond", User.class);
        System.out.println(user1.getName());

//        System.out.println(user);
//        user.setId(1);
//        user.setName("Nguyễn Văn A");
//        user.sayHello();

//        Student student = context.getBean(Student.class);
//        student.getVehicle().move();

//        Vehicle vehicle = context.getBean("bus", Vehicle.class);
//        vehicle.move();
    }
}
