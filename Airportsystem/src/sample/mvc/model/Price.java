package sample.mvc.model;

/**
 * Created by woojen on 2017-05-11.
 */
public class Price {

    private double totalPrice;

    private double childPrice;

    private double adultPrice;

    private int ChildrenAmount;

    private int AdultAmount;


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

        adultPrice = getChildPrice(tripPrice);

        totalPrice = adultPrice * TicketAmount;

        return totalPrice;


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
}
