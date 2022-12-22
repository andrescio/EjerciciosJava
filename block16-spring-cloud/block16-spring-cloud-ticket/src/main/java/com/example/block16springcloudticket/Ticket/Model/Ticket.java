package com.example.block16springcloudticket.Ticket.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints=
        @UniqueConstraint(columnNames = {"id_passenger", "id_trip"}))
public class Ticket {
    @Id
    @GeneratedValue
    Integer id_ticket;

    @Column(name="id_passenger")
    Integer idPassenger;

    String passenger_name;

    String passenger_surname;

    String email;

    @Column(name="id_trip")
    Integer idTrip;

    String tripOrigin;

    String tripDestination;

    Date departureDate;

    Date arrivalDate;

    public Ticket(Integer idPassenger,
                  String passenger_name,
                  String passenger_surname,
                  String email,
                  Integer idTrip,
                  String tripOrigin,
                  String tripDestination,
                  Date departureDate,
                  Date arrivalDate) {
        this.idPassenger = idPassenger;
        this.passenger_name = passenger_name;
        this.passenger_surname = passenger_surname;
        this.email = email;
        this.idTrip = idTrip;
        this.tripOrigin = tripOrigin;
        this.tripDestination = tripDestination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }
}
