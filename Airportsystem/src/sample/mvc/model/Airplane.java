package sample.mvc.model;

import java.io.Serializable;

/**
 * Created by woojen on 2017-04-12.
 */
public class Airplane implements Serializable {

    private int passengerCapacity;
    private int maxLuggageWeight;
    private String maxSpeed;
    private String regNumber;


    private String model;

    public Airplane(int passengerCapacity, int maxLuggageWeight, String maxSpeed, String regNumber, String model) {
        this.passengerCapacity = passengerCapacity;
        this.maxLuggageWeight = maxLuggageWeight;
        this.maxSpeed = maxSpeed;
        this.regNumber = regNumber;
        this.model = model;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public int getMaxLuggageWeight() {
        return maxLuggageWeight;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getModel() {
        return model;
    }


    public void setMaxLuggageWeight(int maxLuggageWeight) {
        this.maxLuggageWeight = maxLuggageWeight;
    }
}



