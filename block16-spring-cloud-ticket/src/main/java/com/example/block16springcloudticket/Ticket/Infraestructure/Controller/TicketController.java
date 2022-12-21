package com.example.block16springcloudticket.Ticket.Infraestructure.Controller;

import com.example.block16springcloudticket.Passenger.Passenger;
import com.example.block16springcloudticket.Ticket.Service.TicketServiceImpl;
import com.example.block16springcloudticket.Trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketServiceImpl ticketServiceImpl;

    @PostMapping
    public void addTicket(@RequestParam String passenger,
                          @RequestParam String trip){
        ticketServiceImpl.generateTicket(passenger,trip);
    }

}
