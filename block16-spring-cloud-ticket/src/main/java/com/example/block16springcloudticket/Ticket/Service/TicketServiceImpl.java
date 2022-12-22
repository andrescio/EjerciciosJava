package com.example.block16springcloudticket.Ticket.Service;

import com.example.block16springcloudticket.Passenger.Passenger;
import com.example.block16springcloudticket.Ticket.Infraestructure.Repository.TicketRepository;
import com.example.block16springcloudticket.Ticket.Model.Ticket;
import com.example.block16springcloudticket.Trip.Trip;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{

    @Autowired
    TicketRepository ticketRepository;

    Gson gson = new Gson();

    @Override
    public void generateTicket(String passengerString, String tripString) throws SQLDataException {

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

        Optional<Ticket> verifyTicket = ticketRepository.findByIdPassengerAndIdTrip(ticket.getIdPassenger(),
                                                                                      ticket.getIdTrip());
        if(!verifyTicket.isEmpty()){
            throw new SQLDataException("Passenger already has a ticket for this trip");
        }
        ticketRepository.save(ticket);
        System.out.println("Ticket created successfully");
    }
}
