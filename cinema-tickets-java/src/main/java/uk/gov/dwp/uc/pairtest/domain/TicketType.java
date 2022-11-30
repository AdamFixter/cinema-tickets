package uk.gov.dwp.uc.pairtest.domain;

public enum TicketType {
    ADULT(20), CHILD(10), INFANT(0);

    private int price;

    TicketType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "TicketType{" +
                "price=" + price +
                '}';
    }
}