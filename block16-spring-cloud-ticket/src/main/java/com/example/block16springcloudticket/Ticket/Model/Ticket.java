package com.example.block16springcloudticket.Ticket.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Ticket {
    @Id
    @GeneratedValue
    Integer id_ticket;

    Integer id_passenger;

    String passenger_name;

    String passenger_surname;

    String email;

    Integer id_trip;

    String tripOrigin;

    String tripDestination;

    Date departureDate;

    Date arrivalDate;

    public Ticket(Integer id_passenger,
                  String passenger_name,
                  String passenger_surname,
                  String email,
                  Integer id_trip,
                  String tripOrigin,
                  String tripDestination,
                  Date departureDate,
                  Date arrivalDate) {
        this.id_passenger = id_passenger;
        this.passenger_name = passenger_name;
        this.passenger_surname = passenger_surname;
        this.email = email;
        this.id_trip = id_trip;
        this.tripOrigin = tripOrigin;
        this.tripDestination = tripDestination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }
}
