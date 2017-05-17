package sample.mvc.model;

import java.io.Serializable;

/**
 * Created by woojen on 2017-04-08.
 */
public class Booking implements Serializable {

    private int tripId;
    private String date;
    private double price;
    private int passengers;
    private boolean hasFood;

    public Booking(int tripId, String date, double price, int passengers, boolean hasFood) {
        this.tripId = tripId;
        this.date = date;
        this.price = price;
        this.passengers = passengers;
        this.hasFood = hasFood;
    }

    public Booking(int tripId) {
        this.tripId = tripId;
    }

    public int getTripId() {
        return tripId;
    }

    public String getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }

    public boolean isHasFood() {
        return hasFood;
    }
}