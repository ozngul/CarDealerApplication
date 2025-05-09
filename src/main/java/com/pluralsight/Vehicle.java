package com.pluralsight;

import java.util.Objects;

public class Vehicle {
    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    // Constructor
    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    // Boş Constructor
    public Vehicle() {
        // Varsayılan değerler atanabilir
    }

    // Getter ve Setter'lar
    public int getVin() {
        return vin;
    }

    public void setVin(int vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 1886) { // İlk otomobilin icadı
            throw new IllegalArgumentException("Year cannot be less than 1886.");
        }
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }
    public String toStringForDealership() {
        return vin + "|" + year + "|" + make + "|" + model + "|" +
                vehicleType + "|" + color + "|" + odometer + "|" + price;
    }
    // `toString` metodu
    // Phase2: String representation for displaying vehicle info
    @Override
    public String toString() {
        return String.format("VIN: %d | Year: %d | %s %s | Type: %s | Color: %s | Odometer: %,d | $%.2f",
                vin, year, make, model, vehicleType, color, odometer, price);
    }

}




