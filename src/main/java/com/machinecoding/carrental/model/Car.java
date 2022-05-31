package com.machinecoding.carrental.model;

public class Car {
    String name;
    String type;
    String prevStationName;

    public String getPrevStationName() {
        return prevStationName;
    }

    public void setPrevStationName(String prevStationName) {
        this.prevStationName = prevStationName;
    }

    public Car(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
