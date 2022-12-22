package com.example.block16springcloudticket.Ticket.Infraestructure.Controller;

import com.example.block16springcloudticket.Ticket.Service.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketServiceImpl ticketServiceImpl;

    @PostMapping
    public void addTicket(@RequestParam String passenger,
                          @RequestParam String trip) throws SQLDataException {
        ticketServiceImpl.generateTicket(passenger,trip);
    }

}
