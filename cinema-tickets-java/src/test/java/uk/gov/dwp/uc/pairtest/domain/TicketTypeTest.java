package uk.gov.dwp.uc.pairtest.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TicketTypeTest {
    @Test
    public void testTicketTypePrice() {
        assertEquals(20, TicketType.valueOf("ADULT").getPrice());
    }
}

