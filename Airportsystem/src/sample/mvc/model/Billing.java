package sample.mvc.model;

/**
 * Created by woojen on 2017-05-11.
 */

// Use this class with the SetBilling interface

public class Billing {

    private double totalPrice;

    private double tripPrice;

    private double luggagePrice;

    private int childAmount;

    private int adultAmount;

    private double discount;

    private int pointMultiplier;

    public Billing() {

        discount = 1;
        pointMultiplier = 1;

    }


    public void setDiscount(Double discount) {

        if (discount > 0 && discount < 1)
            this.discount = discount;
    }

    public double getDiscount() {

        return discount;
    }

    

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

        adultPrice = tripPrice; // dum lösning men funkar

        totalPrice = adultPrice * TicketAmount;

        return totalPrice;


    }

    public double calcLuggagePrice(Double kg) {

        double price;

        if (kg <= 1) {
            price = 0;
        } else

            price = (kg * 20) - 20;

        return price;

    }


    public int getChildAmount() {
        return childAmount;
    }

    public void setChildAmount(int childAmount) {
        this.childAmount = childAmount;
    }

    public int getAdultAmount() {
        return adultAmount;
    }

    public void setAdultAmount(int adultAmount) {
        this.adultAmount = adultAmount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(double tripPrice) {
        this.tripPrice = tripPrice;
    }




    public double getLuggagePrice() {
        return luggagePrice;
    }

    public void setLuggagePrice(Double luggagePrice) {

        this.luggagePrice = luggagePrice;

    }


    // Call this method only if you have already set the needed instance variables
    public void calcAndSetTotalPrice() {


        setTotalPrice((getChildTotalPrice(getTripPrice(), getChildAmount()) +
                getAdultTotalPrice(getTripPrice(), getAdultAmount()) + getLuggagePrice()) * discount);



    }

    // Call this method only if you have already set the needed instance variables
    public int getTicketAmount() {

        int totalAmount;

        totalAmount = childAmount + adultAmount;

        return totalAmount;


    }

    public double getPriceNoDiscount() {

        double noDiscountPrice;
        noDiscountPrice = (getChildTotalPrice(getTripPrice(), getChildAmount()) +
                getAdultTotalPrice(getTripPrice(), getAdultAmount()) + getLuggagePrice());

        return noDiscountPrice;


    }

    public double getDiscountSum() {

        double discountSum;

        discountSum = getTotalPrice() - getPriceNoDiscount();

        return discountSum;

    }

    public int getPointMultiplier() {
        return pointMultiplier;
    }

    public void setPointMultiplier(int pointMultiplier) {
        this.pointMultiplier = pointMultiplier;
    }
}
