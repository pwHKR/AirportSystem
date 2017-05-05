package sample.mvc.model;

import java.io.Serializable;

/**
 * Created by woojen on 2017-04-08.
 */
public class Flight implements Serializable {

    private String flightStatus;
    private String gate;
    private String regNumber;


    public Flight(String flightStatus, String gate, String regNumber) {
        this.flightStatus = flightStatus;
        this.gate = gate;
        this.regNumber = regNumber;
    }

    public String getGate() {
        return gate;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }
}

