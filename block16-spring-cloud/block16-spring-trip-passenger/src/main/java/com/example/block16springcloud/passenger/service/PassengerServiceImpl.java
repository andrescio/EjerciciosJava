package com.example.block16springcloud.passenger.service;

import com.example.block16springcloud.exception.EntityNotFoundException;
import com.example.block16springcloud.passenger.infraestructure.dto.PassengerDTO_Input;
import com.example.block16springcloud.passenger.infraestructure.dto.PassengerDTO_Output;
import com.example.block16springcloud.passenger.infraestructure.repository.PassengerRepository;
import com.example.block16springcloud.passenger.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService{

    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public Passenger addPassenger(PassengerDTO_Input passenger) {
        return passengerRepository.save(passenger.toPassenger());
    }

    @Override
    public Passenger updatePassenger(PassengerDTO_Input passenger) {
        Optional<Passenger> verifyPassenger = passengerRepository.findById(passenger.getId_passenger());
        if(verifyPassenger.isEmpty())
            throw new EntityNotFoundException("Not existent passenger, check the id_passenger");

        return passengerRepository.save(passenger.toPassenger());
    }

    @Override
    public PassengerDTO_Output getPassenger(Integer id) {
        Optional<Passenger> verifyPassenger = passengerRepository.findById(id);
        if(verifyPassenger.isEmpty())
            throw new EntityNotFoundException("Not existent passenger, check the id_passenger");

        return verifyPassenger.get().toDTO();
    }

    @Override
    public List<PassengerDTO_Output> getAllPassenger() {
        List<Passenger> passengers = passengerRepository.findAll();
        List<PassengerDTO_Output> passengerDTO_outputs = new ArrayList<>();
        for(Passenger passenger: passengers){
            passengerDTO_outputs.add(passenger.toDTO());
        }
        return passengerDTO_outputs;
    }

    @Override
    public String deletePassenger(Integer id) {
        Optional<Passenger> verifyPassenger = passengerRepository.findById(id);
        if(verifyPassenger.isEmpty())
            throw new EntityNotFoundException("Not existent passenger, check the id_passenger");

        passengerRepository.deleteById(id);
        return "Passenger deleted successfully";
    }
}
