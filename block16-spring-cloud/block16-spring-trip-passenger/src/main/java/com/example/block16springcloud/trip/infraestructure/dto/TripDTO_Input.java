package com.example.block16springcloud.trip.infraestructure.dto;

import com.example.block16springcloud.passenger.model.Passenger;
import com.example.block16springcloud.trip.model.Trip;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TripDTO_Input {
    Integer id_trip;

    @NotEmpty
    String origin;

    @NotEmpty
    String destination;

    @NotNull
    Date departureDate;

    @NotNull
    Date arrivalDate;

    List<Passenger> passengers;

    @NotEmpty
    String status;

    public Trip toTrip() {
        return new Trip(this.id_trip,
                        this.origin,
                        this.destination,
                        this.departureDate,
                        this.arrivalDate,
                        this.passengers,
                        this.status);
    }
}
