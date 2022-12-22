package com.example.block16springcloud.trip.infraestructure.dto;

import com.example.block16springcloud.passenger.model.Passenger;
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
public class TripDTO_Output {
    Integer id_trip;

    String origin;

    String destination;

    Date departureDate;

    Date arrivalDate;

    List<Passenger> passengers;

    String status;
}
