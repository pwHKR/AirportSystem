package sample.mvc.model;

/**
 * Created by woojen on 2017-04-08.
 */
public class Flight {

    private String flightStatus;
    private String gate;
    private Airplane airplane;


    public Flight(String flightStatus, String gate, Airplane airplane) {
        this.flightStatus = flightStatus;
        this.gate = gate;
        this.airplane = airplane;
    }

    public String getGate() {
        // TODO implement here
        return "";
    }

    public void setGate(String gate) {
        // TODO implement here
    }

}

