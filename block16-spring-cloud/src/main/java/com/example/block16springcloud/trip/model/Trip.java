package com.example.block16springcloud.trip.model;

import com.example.block16springcloud.passenger.model.Passenger;
import com.example.block16springcloud.trip.infraestructure.dto.TripDTO_Output;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Trip {
    @Id
    @GeneratedValue
    Integer id_trip;

    String origin;

    String destination;

    Date departureDate;

    Date arrivalDate;

    @ManyToMany
    @JoinColumn(name = "passenger")
    List<Passenger> passengers;

    String status;

    public TripDTO_Output toDTO(){
        return new TripDTO_Output(this.id_trip,
                                  this.origin,
                                  this.destination,
                                  this.departureDate,
                                  this.arrivalDate,
                                  this.passengers,
                                  this.status);
    }
}
