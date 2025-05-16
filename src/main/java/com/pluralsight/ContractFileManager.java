package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ContractFileManager {
    private static final String CONTRACT_FILE_PATH = "src/main/resources/contracts.csv";
    Vehicle v = new Vehicle();

    // Sözleşmeyi dosyaya kaydet
    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTRACT_FILE_PATH, true))) {
            writer.write(contract.toString());
            writer.newLine();
            System.out.println(" Contract saved successfully: " + contract);
        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }

    // SalesContract kaydetme
    public void saveSalesContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTRACT_FILE_PATH, true))) {
            String line;
            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;
                line = "SALES|" +
                        salesContract.getType() + "|" + salesContract.getDate() + "|" +
                        salesContract.getCustomerName() + "|" +
                        salesContract.getCustomerEmail() + "|" + v.getVin() + "|" + v.getYear() + "|" + v.getMake()
                        + "|" + v.getModel() + "|" + v.getVehicleType() + "|" + v.getColor() + "|" + v.getOdometer()
                        + "|" + v.getPrice() + "|" + salesContract.isVehicleSold() + "|" +
                        salesContract.getTotalPrice() + "|" +
                        salesContract.getMonthlyPayment() + "|" +
                        salesContract.isFinance() + "|" +
                        salesContract.getRecordingFee() + "|" +
                        salesContract.getProcessingFee();
                writer.write(line);
                writer.newLine();
                System.out.println(" Sales contract saved successfully.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // LeaseContract kaydetme
    public void saveLeaseContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONTRACT_FILE_PATH, true))) {
            String line;
            if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;
                Vehicle vehicle = leaseContract.getVehicleSold(); // Doğru araç nesnesi

                line = "LEASE|" +
                        leaseContract.getDate() + "|" +
                        leaseContract.getCustomerName() + "|" +
                        leaseContract.getCustomerEmail() + "|" +
                        vehicle.getVin() + "|" + vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" + vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" + vehicle.getPrice() + "|" +
                        leaseContract.getExpectedEndingValue() + "|" +
                        leaseContract.getLeaseFee() + "|" +
                        leaseContract.getTotalPrice() + "|" +
                        leaseContract.getMonthlyPayment();

                writer.write(line);
                writer.newLine();
                System.out.println("Lease contract saved successfully.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
