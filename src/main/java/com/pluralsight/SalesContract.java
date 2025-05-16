package com.pluralsight;

import java.util.ArrayList;

public class SalesContract extends Contract {
    private double salesTaxAmount;
    private double recordingFee = 100.00;
    private double processingFee;
    private boolean finance;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.finance = finance;

        // Satış vergisi hesaplanıyor (%5)
        this.salesTaxAmount = vehicleSold.getPrice() * 0.05;

        // İşlem ücreti fiyat aralığına göre belirleniyor
        if (vehicleSold.getPrice() < 10000) {
            this.processingFee = 295.00;
        } else {
            this.processingFee = 495.00;
        }

        // Vehicle bilgilerini doğru şekilde set ediyoruz
        this.vehicleSold = vehicleSold;
    }

    @Override
    public double getTotalPrice() {
        return vehicleSold.getPrice() + salesTaxAmount + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!finance) return 0.0;

        double price = getTotalPrice();
        double interestRate;
        int months;

        // Fiyat aralığına göre faiz oranı ve vade ayarlanıyor
        if (vehicleSold.getPrice() >= 10000) {
            interestRate = 0.0425;
            months = 48;
        } else {
            interestRate = 0.0525;
            months = 24;
        }

        double monthlyRate = interestRate / 12.0;
        return Math.round((price * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months)) * 100.0) / 100.0;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public boolean isFinance() {
        return finance;
    }

    public static boolean isFinanceneeded(int option) {
        if (option == 1) {
            return true;
        } else if (option == 2) {
            return false;
        } else {
            System.out.println(" ! Invalid option! Please choose 1 (Yes) or 2 (No).");
            return false;
        }
    }
    @Override
    public String getType() {
        return "SALE";
    }

    @Override
    public String toString() {
        Vehicle vehicle = this.getVehicleSold(); // Doğru araç bilgisini al

        return String.format(
                "%s|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                getType(),                             // SALE
                getDate(),                            // Contract date
                getCustomerName(),                    // Customer name
                getCustomerEmail(),                   // Customer email
                vehicle.getVin(),                     // VIN
                vehicle.getYear(),                    // Year
                vehicle.getMake(),                    // Make
                vehicle.getModel(),                   // Model
                vehicle.getVehicleType(),             // Vehicle type
                vehicle.getColor(),                   // Color
                vehicle.getOdometer(),                // Odometer
                vehicle.getPrice(),                   // Vehicle price
                getSalesTaxAmount(),                  // Sales tax
                getRecordingFee(),                    // Recording fee
                getProcessingFee(),                   // Processing fee
                getTotalPrice(),                      // Total price
                isFinance() ? "YES" : "NO",           // Financing option
                getMonthlyPayment()                   // Monthly payment
        );
    }

    }

