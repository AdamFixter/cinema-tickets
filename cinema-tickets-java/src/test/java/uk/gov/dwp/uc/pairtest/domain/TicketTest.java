package uk.gov.dwp.uc.pairtest.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TicketTest {
    @Test
    public void testConstructor() {
        assertEquals(TicketType.ADULT, (new Ticket(TicketType.ADULT)).getType());
    }

    @Test
    public void testGetPrice() {
        assertEquals(20.0d, (new Ticket(TicketType.ADULT)).getPrice(), 0.0);
    }
}

