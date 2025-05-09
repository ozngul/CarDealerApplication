package com.pluralsight;
import java.util.*;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
        dealership = new Dealership(); // dealership nesnesi başlatılıyor.
    }
    public void printDealershipDetails() {
        System.out.println(dealership.dealershipToString());
    }
    public void display() {
        init(); // Başlatma işlemi
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter command: ");
            int command = scanner.nextInt();
            scanner.nextLine(); // dummy read

            switch (command) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 0 -> running = false;
                default -> System.out.println("Invalid command. Try again.");
            }
        }
    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership(); // dealership yükleniyor
    }

    private void displayMenu() {
        System.out.println("\n===== Vehicle Dealership Menu =====");
        System.out.println("1. Search by Price");
        System.out.println("2. Search by Make & Model");
        System.out.println("3. Search by Year");
        System.out.println("4. Search by Color");
        System.out.println("5. Search by Mileage");
        System.out.println("6. Search by Type");
        System.out.println("7. Show All Vehicles");
        System.out.println("8. Add a Vehicle");
        System.out.println("9. Remove a Vehicle");
        System.out.println("0. Exit");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles to display.");
            return;
        }
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    public void processGetByPriceRequest() {
        System.out.print("Enter min price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter max price: ");
        double max = scanner.nextDouble();
        scanner.nextLine(); // dummy read
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest() {
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // dummy read
        displayVehicles(dealership.getVehiclesByYear(year));
    }

    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest() {
        System.out.print("Enter min mileage: ");
        int min = scanner.nextInt();
        System.out.print("Enter max mileage: ");
        int max = scanner.nextInt();
        scanner.nextLine(); // dummy read
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByType(type));
    }

    public void processAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest() {
        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Enter odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(vehicle);
        new DealershipFileManager().saveDealership(dealership); // Veriyi kaydet
    }

    public void processRemoveVehicleRequest() {
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        Vehicle toRemove = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                toRemove = v;
                break;
            }
        }

        if (toRemove != null) {
            dealership.removeVehicle(toRemove);
            new DealershipFileManager().saveDealership(dealership); // Veriyi kaydet
        } else {
            System.out.println("Vehicle not found.");
        }
    }
}

