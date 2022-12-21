package com.example.block16springcloudticket.Ticket.Infraestructure.Repository;

import com.example.block16springcloudticket.Ticket.Model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
