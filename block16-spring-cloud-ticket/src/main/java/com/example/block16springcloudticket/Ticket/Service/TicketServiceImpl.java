package com.example.block16springcloudticket.Ticket.Service;

import com.example.block16springcloudticket.Passenger.Passenger;
import com.example.block16springcloudticket.Ticket.Infraestructure.Repository.TicketRepository;
import com.example.block16springcloudticket.Ticket.Model.Ticket;
import com.example.block16springcloudticket.Trip.Trip;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    TicketRepository ticketRepository;

    Gson gson = new Gson();

    @Override
    public void generateTicket(String passengerString, String tripString) {

        Passenger passenger = gson.fromJson(passengerString, Passenger.class);

        Trip trip = gson.fromJson(tripString, Trip.class);

        Ticket ticket = new Ticket(passenger.getId_passenger(),
                                    passenger.getName(),
                                    passenger.getSurname(),
                                    passenger.getEmail(),
                                    trip.getId_trip(),
                                    trip.getOrigin(),
                                    trip.getDestination(),
                                    trip.getDepartureDate(),
                                    trip.getArrivalDate());
        ticketRepository.save(ticket);
        System.out.println("Ticket created successfully");
    }
}
