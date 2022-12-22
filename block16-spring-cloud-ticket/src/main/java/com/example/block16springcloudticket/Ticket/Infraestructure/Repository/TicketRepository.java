package com.example.block16springcloudticket.Ticket.Infraestructure.Repository;

import com.example.block16springcloudticket.Ticket.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findByIdPassengerAndIdTrip(Integer id_passenger, Integer id_trip);
}
