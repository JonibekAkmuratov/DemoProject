package com.example.demoproject.other.designPattern.builder;

import com.example.demoproject.entity.main.Category;

public class Main {

    public static void main(String[] args) {

        Car car = new Car.CarBuilder()
                .brand("Hyundai")
                .model("sonata")
                .color("black")
                .build();

        System.out.println(car);
    }
}
