package com.example.block16springcloud.passenger.service;

import com.example.block16springcloud.passenger.infraestructure.dto.PassengerDTO_Input;
import com.example.block16springcloud.passenger.infraestructure.dto.PassengerDTO_Output;
import com.example.block16springcloud.passenger.model.Passenger;

import java.util.List;

public interface PassengerService {
    Passenger addPassenger(PassengerDTO_Input passenger);
    Passenger updatePassenger(PassengerDTO_Input passenger);
    PassengerDTO_Output getPassenger(Integer id);
    List<PassengerDTO_Output> getAllPassenger();
    String deletePassenger(Integer id);
}
