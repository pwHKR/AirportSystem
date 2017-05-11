package sample.mvc.model;

/**
 * Created by woojen on 2017-05-11.
 */
public class Billing {

    private double totalPrice;

    private double childPrice;

    private double adultPrice;

    private int childrenAmount;

    private int adultAmount;

    private double luggagePrice;


    public double getChildPrice(Double tripPrice) {

        Double childPrice;

        childPrice = tripPrice * 0.5;

        return childPrice;


    }

    public double getChildTotalPrice(Double tripPrice, int TicketAmount) {

        double childPrice;
        double totalPrice;

        childPrice = getChildPrice(tripPrice);

        totalPrice = childPrice * TicketAmount;

        return totalPrice;


    }


    public double getAdultTotalPrice(Double tripPrice, int TicketAmount) {

        double adultPrice;
        double totalPrice;

        adultPrice = 2 * getChildPrice(tripPrice); // dum lösning men funkar

        totalPrice = adultPrice * TicketAmount;

        return totalPrice;


    }

    public double calculateLuggagePrice(Double kg) {

        double price = 0;

        if (kg > 1) {

            price = kg * 20;
        }

        return price;

    }







    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(double childPrice) {
        this.childPrice = childPrice;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(double adultPrice) {
        this.adultPrice = adultPrice;
    }


    public double getLuggagePrice() {
        return luggagePrice;
    }

    public void setLuggagePrice(Double price) {

        this.luggagePrice = luggagePrice;

    }
}
