package com.example.block16springcloud.trip.service;

import com.example.block16springcloud.trip.infraestructure.dto.TripDTO_Input;
import com.example.block16springcloud.trip.infraestructure.dto.TripDTO_Output;
import com.example.block16springcloud.trip.model.Trip;

import java.util.List;

public interface TripService {
    Trip addTrip(TripDTO_Input trip);
    Trip updateTrip(TripDTO_Input Trip);
    TripDTO_Output getTrip(Integer id);
    List<TripDTO_Output> getAllTrip();
    String deleteTrip(Integer id);
    TripDTO_Output addPassengerTrip(Integer idTrip, Integer idPassenger);
    Integer countPassengerTrip(Integer idTrip);
    TripDTO_Output changeStatusTrip(Integer idTrip, String status);
    String getStatusTrip(Integer idTrip);
}
