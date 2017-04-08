/**
 * Created by woojen on 2017-04-08.
 */
public class Booking {

    private Customer customer;
    private String date;
    private double price;

    private Flight flight;

    public Booking(Customer customer, String date, double price, Flight flight) {
        this.customer = customer;
        this.date = date;
        this.price = price;
        this.flight = flight;
    }

    public void createBooking() {
    } //

    public void editBooking() {
    }

    public void RemoveBooking() {
    }


}