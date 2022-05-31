package com.machinecoding.carrental;

import com.machinecoding.carrental.model.Car;
import com.machinecoding.carrental.service.rentalService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Driver {
    public static void main(String[] args) throws Exception {
        rentalService rentalService = new rentalService();
        Car car1 = rentalService.createCar("alto", "hatchback");
        Car car2 = rentalService.createCar("civic", "sedan");
        Car car3 = rentalService.createCar("seltos", "suv");
        Car car4 = rentalService.createCar("swift", "hatchback");
        Car car5 = rentalService.createCar("accent", "sedan");
        Car car6 = rentalService.createCar("hector", "suv");
        Car car7 = rentalService.createCar("tiago", "hatchback");
        Car car8 = rentalService.createCar("bmw", "sedan");
        Car car9 = rentalService.createCar("safari", "suv");
        List<Car> cars1 = new ArrayList<>();
        cars1.add(car1);
        cars1.add(car2);
        cars1.add(car3);
        Map<String, Integer> prices1 = new HashMap<>();
        prices1.put("hatchback", 10);
        prices1.put("sedan", 12);
        prices1.put("suv", 8);
        List<Car> cars2 = new ArrayList<>();
        cars2.add(car4);
        cars2.add(car5);
        cars2.add(car6);
        Map<String, Integer> prices2 = new HashMap<>();
        prices2.put("hatchback", 20);
        prices2.put("sedan", 32);
        prices2.put("suv", 15);
        List<Car> cars3 = new ArrayList<>();
        cars3.add(car7);
        cars3.add(car8);
        cars3.add(car9);
        Map<String, Integer> prices3 = new HashMap<>();
        prices3.put("hatchback", 30);
        prices3.put("sedan", 10);
        prices3.put("suv", 15);

        rentalService.addStation("station1", cars1, prices1);
        rentalService.addStation("station2", cars2, prices2);
        rentalService.addStation("station3", cars3, prices3);

        rentalService.addUser("hari");

        rentalService.bookVehicle("hari", "alto", "hatchback", "station1");

        rentalService.dropVehicle("hari", "station2", "alto", "hatchback");

        rentalService.generateStationReport("station1");
        rentalService.generateStationReport("station2");
        rentalService.generateStationReport("station3");
    }
}

/*
Requirement -

This rental system will have some stations where car will be parked.
Every station will have some type of cars (example - SUV, Hatchback, Bike, Sedan etc) and will have some fixed price for each type of car ($11/hour for SUV, 12$/per hour for Sedan etc.)
User can book car from any station and drop at same or any other station
When user searches for a car - list all stations with that type of available car + station with cheapest price should be on top followed by second cheapest and so on..(order by increasing per hour price)
When two stations have same price, list station nearest to the customer
Supported operations

Onboard station - (station_name, List
Search Vehicle
Book vehicle
Drop vehicle
Station Report - available car of each type, booked car of each type etc.
 */