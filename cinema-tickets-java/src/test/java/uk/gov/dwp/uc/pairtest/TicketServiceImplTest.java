package uk.gov.dwp.uc.pairtest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import uk.gov.dwp.uc.pairtest.domain.Ticket;
import uk.gov.dwp.uc.pairtest.domain.TicketType;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import java.util.ArrayList;
import java.util.List;

public class TicketServiceImplTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testPurchaseTicketForNoAdultWithChild() throws InvalidPurchaseException {
        TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();

        thrown.expect(InvalidPurchaseException.class);

        ticketServiceImpl.purchaseTickets(1L, new TicketTypeRequest(new Ticket(TicketType.CHILD), 1));
    }

    @Test
    public void testPurchaseTicketForNoAdultWithInfant() throws InvalidPurchaseException {
        TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();

        thrown.expect(InvalidPurchaseException.class);

        ticketServiceImpl.purchaseTickets(1L, new TicketTypeRequest(new Ticket(TicketType.INFANT), 1));
    }

    @Test
    public void testPurchaseTicketMaxTickets() throws InvalidPurchaseException {
        TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();
        List<TicketTypeRequest> requests = new ArrayList<TicketTypeRequest>();

        for (int i = 0; i < 20; i++) {
            requests.add(new TicketTypeRequest(new Ticket(TicketType.ADULT), 1));
        }

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[requests.size()];
        ticketTypeRequests = requests.toArray(ticketTypeRequests);

        thrown.expect(InvalidPurchaseException.class);

        ticketServiceImpl.purchaseTickets(1L, ticketTypeRequests);
    }

    @Test
    public void testPurchaseTicketMultipleTickets() throws InvalidPurchaseException {
        TicketServiceImpl ticketServiceImpl = new TicketServiceImpl();

        TicketTypeRequest[] ticketTypeRequests = new TicketTypeRequest[] {
                new TicketTypeRequest(new Ticket(TicketType.ADULT), 1),
                new TicketTypeRequest(new Ticket(TicketType.CHILD), 1)
        };

        ticketServiceImpl.purchaseTickets(1L, ticketTypeRequests);
        ticketServiceImpl.purchaseTickets(1L, ticketTypeRequests);
    }
}

