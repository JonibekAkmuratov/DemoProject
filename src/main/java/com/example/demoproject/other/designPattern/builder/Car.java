package com.example.demoproject.other.designPattern.builder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private String brand;
    private String model;

    private String color;
    private String engine;
    private String engineType;
    private String engineSize;


    public static class CarBuilder {
        private String brand;
        private String model;
        private String color;
        private String engine;
        private String engineType;
        private String engineSize;

        public CarBuilder engineSize(String engineSize) {
            this.engineSize = engineSize;
            return this;
        }

        public CarBuilder engineType(String engineType) {
            this.engineType = engineType;
            return this;
        }

        public CarBuilder engine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder color(String color) {
            this.color = color;
            return this;
        }

        public CarBuilder model(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Car build() {
            return new Car(
                    this.brand,
                    this.model,
                    this.color,
                    this.engine,
                    this.engineType,
                    this.engineSize
            );
        }
    }

}
