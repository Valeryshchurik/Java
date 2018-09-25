package com.shchuryk.model;

public class Order {
    private int clientId;
    private int tourId;

    public int getClientId() {
        return clientId;
    }

    public int getTourId() {
        return tourId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return (order.clientId==clientId)&&(order.tourId==tourId);
    }

    @Override
    public int hashCode() {
        int result = clientId + 31+tourId;
        return result;
    }
}
