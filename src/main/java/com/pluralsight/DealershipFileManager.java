package com.pluralsight;

import java.io.*;
import java.util.*;

public class DealershipFileManager {

    private static final String FILE_NAME = "src/main/resources/inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = new Dealership("My Dealership", "123 Main St", "555-1234");

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // Skip empty lines

                // Format: vin,year,make,model,type,color,odometer,price
                String[] data = line.split("\\|");
                if (data.length == 8) {
                    try {
                        int vin = Integer.parseInt(data[0]);
                        int year = Integer.parseInt(data[1]);
                        String make = data[2];
                        String model = data[3];
                        String type = data[4];
                        String color = data[5];
                        int odometer = Integer.parseInt(data[6]);
                        double price = Double.parseDouble(data[7]);

                        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                        dealership.addVehicle(vehicle);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid data found: " + Arrays.toString(data));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Vehicle v : dealership.getAllVehicles()) {
                pw.println(v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel() + "|" +
                        v.getVehicleType() + "|" + v.getColor() + "|" + v.getOdometer() + "|" + v.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

