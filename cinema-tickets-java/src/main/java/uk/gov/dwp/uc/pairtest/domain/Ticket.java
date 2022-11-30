package uk.gov.dwp.uc.pairtest.domain;

public class Ticket {
    private TicketType type;

    public Ticket(TicketType type) {
        this.type = type;
    }

    public TicketType getType() {
        return this.type;
    }

    public double getPrice() {
        return this.type.getPrice();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "type=" + type +
                '}';
    }
}
