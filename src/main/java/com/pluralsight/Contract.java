package com.pluralsight;

public abstract class Contract {
    protected String date;
    protected String customerName;
    protected String customerEmail;
    protected Vehicle vehicleSold;
    protected boolean isVehicleSold;  // Bu alanı ekledim

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
        this.isVehicleSold = true;  // Varsayılan olarak true
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    // Eksik olan isVehicleSold metodu
    public boolean isVehicleSold() {
        return isVehicleSold;
    }

    public void setVehicleSold(boolean isVehicleSold) {
        this.isVehicleSold = isVehicleSold;
    }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

    public abstract String getType(); // Eksik olan abstract metod
}
