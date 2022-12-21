package com.example.block16springcloudticket.Trip;


import com.example.block16springcloudticket.Passenger.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trip {
    Integer id_trip;

    String origin;

    String destination;

    Date departureDate;

    Date arrivalDate;

    List<Passenger> passengers;

    String status;
}
