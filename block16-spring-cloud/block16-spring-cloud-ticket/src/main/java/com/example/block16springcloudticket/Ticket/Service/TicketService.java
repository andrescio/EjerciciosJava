package com.example.block16springcloudticket.Ticket.Service;

import java.sql.SQLDataException;

public interface TicketService {
    void generateTicket(String passenger, String trip) throws SQLDataException;
}
