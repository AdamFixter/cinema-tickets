package uk.gov.dwp.uc.pairtest;

import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;
import uk.gov.dwp.uc.pairtest.domain.Ticket;
import uk.gov.dwp.uc.pairtest.domain.TicketType;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

import java.util.*;

public class TicketServiceImpl implements TicketService {
    /**
     * Should only have private methods other than the one below.
     */

    private TicketPaymentServiceImpl ticketPaymentService = new TicketPaymentServiceImpl();
    private SeatReservationServiceImpl seatReservationService = new SeatReservationServiceImpl();

    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        if (getTotalTickets(ticketTypeRequests) > 19) {
            throw new InvalidPurchaseException("Can't purchase more than 20 tickets at a time.");
        }

        int totalPrice = 0;
        int totalReservedSeats = 0;

        for (int i = 0; i < ticketTypeRequests.length; i++) {
            TicketTypeRequest ticketTypeRequest = ticketTypeRequests[i];
            Ticket ticket = ticketTypeRequest.getTicket();

            if (ticket.getType() != TicketType.ADULT && getTicketsByType(TicketType.ADULT, ticketTypeRequests).length == 0) {
                throw new InvalidPurchaseException("A child/infant ticket cannot be purchased without an adult ticket.");
            }

            if (ticket.getType() != TicketType.INFANT) {
                totalReservedSeats += ticketTypeRequest.getNoOfTickets();
            }
            totalPrice += ticket.getPrice() * ticketTypeRequest.getNoOfTickets();
        }

        if (totalReservedSeats > 0) {
            seatReservationService.reserveSeat(accountId, totalReservedSeats);
        }

        if (totalPrice > 0) {
            ticketPaymentService.makePayment(accountId, totalPrice);
        }
    }

    /**
     * Gets a filtered array of types by type
     * @param type - TicketType
     * @param ticketTypeRequests - TicketTypeRequest
     * @return An array of TicketTypeRequest filtered by TicketType
     */
    private TicketTypeRequest[] getTicketsByType(TicketType type, TicketTypeRequest... ticketTypeRequests) {
        return Arrays.stream(ticketTypeRequests)
                .filter(Objects::nonNull)
                .filter(ticketRequest -> Objects.nonNull(ticketRequest) && ticketRequest.getTicket().getType() == type)
                .toArray(TicketTypeRequest[]::new);
    }

    /**
     * Gets the total amount of tickets from all given requests
     * @param ticketTypeRequests - TicketTypeRequest
     * @return The total number of tickets from all requests
     */
    private int getTotalTickets(TicketTypeRequest... ticketTypeRequests) {
        return Arrays.stream(ticketTypeRequests)
                .filter(Objects::nonNull)
                .filter(request -> request.getTicket().getType() != TicketType.INFANT)
                .map(request -> request.getNoOfTickets())
                .mapToInt(Integer::intValue).sum();
    }
}
