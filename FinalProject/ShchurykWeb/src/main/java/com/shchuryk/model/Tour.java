package com.shchuryk.model;


import java.sql.Date;

public class Tour {
    private int tourId;
    private String name;
    private String country;
    private Date startDate;
    private Date endDate;
    private Type type;
    private int price;
    private boolean burning;
    private double discounts;

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public Tour() {
    }

    public Tour(String name, String country, Date startDate, Date endDate, Type type, int price, boolean burning, double discounts) {
        this.name = name;
        this.country = country;

        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.price = price;
        this.burning = burning;
        this.discounts = discounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBurning() {
        return burning;
    }

    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    public double getDiscounts() {
        return discounts;
    }

    public void setDiscounts(double discounts) {
        this.discounts = discounts;
    }

    @Override
    public String toString() {
        return "Tour{" +
                "tourId=" + tourId +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", type=" + type +
                ", price=" + price +
                ", burning=" + burning +
                ", discounts=" + discounts +
                '}';
    }
}
