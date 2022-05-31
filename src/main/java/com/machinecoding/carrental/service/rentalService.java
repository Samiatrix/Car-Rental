package com.machinecoding.carrental.service;

import com.machinecoding.carrental.model.Car;
import com.machinecoding.carrental.model.Station;
import com.machinecoding.carrental.model.User;

import java.util.*;

public class rentalService {
    List<Station> stations;
    List<User> users;

    public rentalService(){
        this.stations = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addStation(String name, List<Car> cars, Map<String, Integer> prices) {
        Station station = new Station(name);
        Map<String, List<Car>> carWithType = new HashMap<>();
        for(Car car : cars){
            addCarWithType(carWithType, car);
        }
        station.setCars(carWithType);
        station.setPrices(prices);
        stations.add(station);
    }

    public Car createCar(String name, String type) {
        Car car = new Car(name, type);
        return car;
    }

    public void addCarWithType(Map<String, List<Car>> carWithType, Car car){
        List<Car> cars = carWithType.get(car.getType());
        if(cars == null) {
            cars = new ArrayList<Car>();
            cars.add(car);
            carWithType.put(car.getType(), cars);
        } else {
            if(!cars.contains(car)) cars.add(car);
        }
    }

    public void bookVehicle(String userName, String name, String type, String stationName) {
        Optional<User> optionalUser = users.stream().filter(us -> us.getName().equalsIgnoreCase(userName)).findAny();
        User user = optionalUser.get();
        Optional<Station> optionalStation = stations.stream().filter(st -> st.getStationName().equalsIgnoreCase(stationName)).findAny();
        if(optionalStation.isPresent()){
            Station station = optionalStation.get();
            Map<String, List<Car>> carsWithType = station.getCars();
            Car car = null;
            if(carsWithType.containsKey(type)){
                List<Car> cars = carsWithType.get(type);
                car = cars.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findAny().orElseThrow();
                if(car != null){
                    cars.remove(car);
                    carsWithType.put(type, cars);
                }
            }
            if(car != null){
                user.getBookedCar().add(car);
                addCarWithType(station.getBookedCars(), car);
                car.setPrevStationName(stationName);
            }
        }
    }

    public void addUser(String name) {
        User user = new User(name);
        users.add(user);
    }

    public void dropVehicle(String userName, String stationName, String carName, String carType) throws Exception {
        User user = getUser(userName);
        Station station = getStation(stationName);
        List<Car> userBookedCars = user.getBookedCar();
        Optional<Car> bookedCar = userBookedCars.stream().filter(car -> car.getName().equalsIgnoreCase(carName) && car.getType().equalsIgnoreCase(carType)).findAny();
        if(bookedCar.isPresent()){
            Car car = bookedCar.get();
            addCarWithType(station.getCars(), car);
            userBookedCars.remove(car);
            Station prevStation = getStation(car.getPrevStationName());
            removeBookedCars(prevStation, carType, carName);

        }
        else{
            System.out.println("No Booked Car with Name: " + carName + "of Type: " + carType + " on user: "+userName);
        }
    }

    private void removeBookedCars(Station prevStation, String carType, String carName) throws Exception {
        try {
            if (prevStation.getBookedCars().containsKey(carType)) {
                List<Car> cars = prevStation.getBookedCars().get(carType);
                Car car = cars.stream().filter(c -> c.getName().equalsIgnoreCase(carName)).findAny().orElseThrow();
                cars.remove(car);
                if (cars.isEmpty()) {
                    prevStation.getBookedCars().remove(carType);
                } else prevStation.getBookedCars().put(carType, cars);
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private Station getStation(String stationName) {
        Optional<Station> station = stations.stream().filter(st -> st.getStationName().equalsIgnoreCase(stationName)).findAny();
        return station.orElse(null);
    }

    private User getUser(String userName) {
        Optional<User> user = users.stream().filter(us -> us.getName().equalsIgnoreCase(userName)).findAny();
        return user.orElse(null);
    }

    public void generateStationReport(String stationName) {
        Station station = getStation(stationName);
        System.out.println("Available Cars with Type: ");
        showCars(station.getCars());
        System.out.println("Booked Cars with Type: ");
        showCars(station.getBookedCars());
        System.out.println();
    }

    private void showCars(Map<String, List<Car>> cars) {
        for(String carType : cars.keySet()){
            if(!cars.get(carType).isEmpty()) {
                String carsOfType = carType + " : ";
                for (Car car : cars.get(carType)) {
                    carsOfType += car.getName() + ", ";
                }
                System.out.println(carsOfType.substring(0, carsOfType.length() - 2));
            }
        }
    }
}
