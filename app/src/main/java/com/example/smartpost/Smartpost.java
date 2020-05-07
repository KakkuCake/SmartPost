package com.example.smartpost;

public class Smartpost {

    String name, city, address, availability;

    public Smartpost(String name, String city, String address, String availability) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return name;
    }
}

