package com.machinecoding.carrental.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    String name;
    List<Car> bookedCar;

    public User(String name) {
        this.name = name;
        this.bookedCar = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Car> getBookedCar() {
        return bookedCar;
    }
}
