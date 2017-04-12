/**
 * Created by woojen on 2017-04-08.
 */
public class Booking {

    private int bookingId;
    private Trip trip;
    private String date;
    private double price;
    private int passengers;

    public Booking(int bookingId, Trip trip, String date, double price, int passengers) {
        this.bookingId = bookingId;
        this.trip = trip;
        this.date = date;
        this.price = price;
        this.passengers = passengers;
    }

    public void createBooking() {
    } //

    public void editBooking() {
    }

    public void RemoveBooking() {
    }


}