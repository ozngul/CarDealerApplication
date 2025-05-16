package com.pluralsight;

import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private DealershipFileManager dealershipFileManager;
    private ContractFileManager contractFileManager;

    public UserInterface() {
        this.dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.getDealership();
        this.contractFileManager = new ContractFileManager();
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n== Dealership Menu ==");
            System.out.println("1. View All Vehicles");
            System.out.println("2. Search by Price");
            System.out.println("3. Search by Make/Model");
            System.out.println("4. Sell/Lease a Vehicle");
            System.out.println("5. Add a Vehicle");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Dummy read

            switch (choice) {
                case 1 -> displayAllVehicles();
                case 2 -> searchByPrice(scanner);
                case 3 -> searchByMakeModel(scanner);
                case 4 -> handleContract(scanner);
                case 5 -> addVehicle(scanner);  // Yeni eklenen seçenek
                case 6 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void displayAllVehicles() {
        if (dealership.getAllVehicles().isEmpty()) {
            System.out.println("No vehicles available in the inventory.");
        } else {
            for (Vehicle v : dealership.getAllVehicles()) {
                System.out.println(v.toStringForDealership());
            }
        }
    }

    private void addVehicle(Scanner scanner) {
        try {
            System.out.print("Enter VIN: ");
            int vin = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Year: ");
            int year = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Make: ");
            String make = scanner.nextLine().trim();

            System.out.print("Enter Model: ");
            String model = scanner.nextLine().trim();

            System.out.print("Enter Vehicle Type: ");
            String vehicleType = scanner.nextLine().trim();

            System.out.print("Enter Color: ");
            String color = scanner.nextLine().trim();

            System.out.print("Enter Odometer: ");
            int odometer = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Price: ");
            double price = Double.parseDouble(scanner.nextLine().trim());

            Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            dealership.addVehicle(newVehicle);
            dealershipFileManager.saveDealership(dealership);
            System.out.println(" Vehicle added successfully.");

        } catch (NumberFormatException e) {
            System.out.println(" Invalid input. Please enter valid numbers.");
        }
    }

    private void searchByPrice(Scanner scanner) {
        try {
            System.out.print("Enter min price: ");
            double min = scanner.nextDouble();
            System.out.print("Enter max price: ");
            double max = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            for (Vehicle v : dealership.getVehiclesByPrice(min, max)) {
                System.out.println(v.toStringForDealership());
            }
        } catch (Exception e) {
            System.out.println("Invalid price input. Please enter valid numbers.");
            scanner.nextLine(); // Clear the invalid input
        }
    }

    private void searchByMakeModel(Scanner scanner) {
        System.out.print("Enter make: ");
        String make = scanner.nextLine().trim();
        System.out.print("Enter model: ");
        String model = scanner.nextLine().trim();

        for (Vehicle v : dealership.getVehiclesByMakeModel(make, model)) {
            System.out.println(v.toStringForDealership());
        }
    }

    private void handleContract(Scanner scanner) {
        try {
            System.out.print("Enter VIN of vehicle to sell/lease: ");
            int vin = Integer.parseInt(scanner.nextLine().trim());

            Vehicle selected = dealership.getVehicleByVin(vin);
            if (selected == null) {
                System.out.println("Vehicle not found.");
                return;
            }

            System.out.print("Enter customer name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter customer email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Enter date (YYYYMMDD): ");
            String date = scanner.nextLine().trim();

            System.out.print("Is this a sale or lease? (1.Sale 2.Lease): ");
            int contractType = scanner.nextInt();
            scanner.nextLine();

            if (contractType == 1) {
                System.out.print("Do they want financing? 1.Yes 2.No: ");
                int financeOption = scanner.nextInt();
                scanner.nextLine();
                boolean finance = SalesContract.isFinanceneeded(financeOption);


                SalesContract sale = new SalesContract(date, name, email, selected, finance);
                contractFileManager.saveSalesContract(sale);
                dealershipFileManager.saveDealership(dealership);
                System.out.println(" Sales contract saved.");

            } else if (contractType == 2) {
                int currentYear = 2025; // Bu statik olabilir veya dinamik yapılabilir
                if (currentYear - selected.getYear() > 3) {
                    System.out.println("This vehicle is too old to lease (older than 3 years).");
                    return;
                }

                LeaseContract lease = new LeaseContract(date, name, email, selected);
                contractFileManager.saveLeaseContract(lease);
                dealership.removeVehicle(selected);
                dealershipFileManager.saveDealership(dealership);
                System.out.println(" Lease contract saved.");

            } else {
                System.out.println("Invalid option. Please choose either 'sale' or 'lease'.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid VIN. Please enter a numeric VIN.");
        } catch (Exception e) {
            System.out.println("An error occurred while processing the contract: " + e.getMessage());
        }
    }
}
