package uk.gov.dwp.uc.pairtest.domain;

/**
 * Immutable Object
 */

public class TicketTypeRequest {

    private int noOfTickets;
    private Ticket ticket;

    public TicketTypeRequest(Ticket ticket, int noOfTickets) {
        this.ticket = ticket;
        this.noOfTickets = noOfTickets;
    }

    public int getNoOfTickets() {
        return this.noOfTickets;
    }

    public Ticket getTicket() {
        return this.ticket;
    }

    @Override
    public String toString() {
        return "TicketTypeRequest{" +
                "noOfTickets=" + noOfTickets +
                ", ticket=" + ticket +
                '}';
    }
}
