package com.example.block16springcloudticket.Ticket.Service;

import com.example.block16springcloudticket.Passenger.Passenger;
import com.example.block16springcloudticket.Ticket.Model.Ticket;
import com.example.block16springcloudticket.Trip.Trip;

public interface TicketService {
    void generateTicket(String passenger, String trip);
}
