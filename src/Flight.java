/**
 * Created by woojen on 2017-04-08.
 */
public class Flight {

    private Airport airport;
    private Plane plane;
    private String flightStatus;
    private boolean isBuss;

    public Flight(Airport airport, Plane plane, String flightStatus, boolean isBuss) {
        this.airport = airport;
        this.plane = plane;
        this.flightStatus = flightStatus;
        this.isBuss = isBuss;
    }
}
