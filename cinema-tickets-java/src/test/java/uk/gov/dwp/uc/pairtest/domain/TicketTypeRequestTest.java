package uk.gov.dwp.uc.pairtest.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class TicketTypeRequestTest {
    @Test
    public void testConstructor() {
        Ticket ticket = new Ticket(TicketType.ADULT);
        TicketTypeRequest ticketTypeRequest = new TicketTypeRequest(ticket, 1);

        assertEquals(1, ticketTypeRequest.getNoOfTickets());
        assertSame(ticket, ticketTypeRequest.getTicket());
    }
}

