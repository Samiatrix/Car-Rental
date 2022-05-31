package com.machinecoding.carrental.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Station {
    String stationName;
    Map<String, List<Car>> cars;
    Map<String, Integer> prices;
    Map<String, List<Car>> bookedCars;

    public Station(String name) {
        this.stationName = name;
        this.cars = new HashMap<>();
        this.prices = new HashMap<>();
        this.bookedCars = new HashMap<>();
    }

    public String getStationName() {
        return stationName;
    }

    public Map<String, List<Car>> getBookedCars() {
        return bookedCars;
    }

    public Map<String, List<Car>> getCars() {
        return cars;
    }

    public void setCars(Map<String, List<Car>> cars) {
        this.cars = cars;
    }

    public void setPrices(Map<String, Integer> prices) {
        this.prices = prices;
    }
}
