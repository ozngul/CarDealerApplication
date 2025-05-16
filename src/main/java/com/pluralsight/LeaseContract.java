package com.pluralsight;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;
    private double totalPrice;
    private double monthlyPayment;
    Vehicle v = new Vehicle();

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleLeased) {
        super(date, customerName, customerEmail, vehicleLeased );


        this.expectedEndingValue = vehicleLeased.getPrice() * 0.50;
        this.leaseFee = vehicleLeased.getPrice() * 0.07;
        this.totalPrice = expectedEndingValue + leaseFee;
        this.monthlyPayment = calculateMonthlyPayment();
    }


    private double calculateMonthlyPayment() {
        double leaseRate = 0.04 / 12.0; // %4 yıllık faiz, aylık oran
        int months = 36;
        return (totalPrice * leaseRate) / (1 - Math.pow(1 + leaseRate, -months));
    }

    @Override
    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    @Override
    public String getType() {
        return "LEASE";
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f",
                getType(),
                getDate(),
                getCustomerName(),
                getCustomerEmail(),
                v.getVin(),
                v.getYear(),
                v.getMake(),
                v.getModel(),
                v.getVehicleType(),
                v.getColor(),
                v.getOdometer(),
                v.getPrice(),
                getExpectedEndingValue(),
                getLeaseFee(),
                getTotalPrice(),
                getMonthlyPayment()
        );
    }
}
