package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    // Constructor with parameters
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    // Default constructor
    public Dealership() {
        this.inventory = new ArrayList<>();
    }

    // Method to add a vehicle to the inventory
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }


    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    // Method to get all vehicles in the inventory
    public List<Vehicle> getAllVehicles() {
        return inventory;
    }


    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                result.add(vehicle);
            }
        }
        return result;
    }


    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    // Method to get vehicles by year
    public List<Vehicle> getVehiclesByYear(int min) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= min) {
                result.add(vehicle);
            }
        }
        return result;
    }

    // Method to get vehicles by color
    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    // Method to get vehicles by mileage range
    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                result.add(vehicle);
            }
        }
        return result;
    }

    // Method to get vehicles by type
    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    // Method to return dealership info along with all vehicles in a specific format
    public String dealershipToString() {
        StringBuilder dealershipInfo = new StringBuilder();
        dealershipInfo.append(name).append("|").append(address).append("|").append(phone).append("\n");
        for (Vehicle vehicle : inventory) {
            dealershipInfo.append(vehicle.toStringForDealership()).append("\n");
        }
        return dealershipInfo.toString();
    }

    // Getter methods for dealership details
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    // Method to get a vehicle by VIN
    public Vehicle getVehicleByVin(int vin) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVin() == vin) {
                return vehicle;
            }
        }
        return null;
    }
}
