package sample.mvc.model;

/**
 * Created by woojen on 2017-04-12.
 */
public class Airplane {

    private int passengerCapacity;
    private int maxLuggageWeight;
    private String maxSpeed;
    private String regNumber;

    public Airplane(int passengerCapacity, int maxLuggageWeight, String maxSpeed, String regNumber) {
        this.passengerCapacity = passengerCapacity;
        this.maxLuggageWeight = maxLuggageWeight;
        this.maxSpeed = maxSpeed;
        this.regNumber = regNumber;
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
}

