package com.example.block16springcloud.trip.service;

import com.example.block16springcloud.exception.EntityNotFoundException;
import com.example.block16springcloud.exception.UnprocessableEntityException;
import com.example.block16springcloud.passenger.infraestructure.repository.PassengerRepository;
import com.example.block16springcloud.passenger.model.Passenger;
import com.example.block16springcloud.trip.infraestructure.dto.TripDTO_Input;
import com.example.block16springcloud.trip.infraestructure.dto.TripDTO_Output;
import com.example.block16springcloud.trip.infraestructure.repository.TripRepository;
import com.example.block16springcloud.trip.model.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TripServiceImpl implements TripService{

    @Autowired
    TripRepository tripRepository;

    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public Trip addTrip(TripDTO_Input tripDTO_input) {
        if(tripDTO_input.getPassengers() != null){
            if(tripDTO_input.getPassengers().size() > 40)
                throw new UnprocessableEntityException("A trip cannot have more than 40 passengers");

            List<Passenger> actualPassenger = new ArrayList<>();
            for(Passenger passenger: tripDTO_input.getPassengers()){
                Optional<Passenger> verifyPassenger = passengerRepository.findById(passenger.getId_passenger());
                if(verifyPassenger.isEmpty())
                    throw new EntityNotFoundException("One or more passengers inside the list do not exist");
                actualPassenger.add(verifyPassenger.get());
            }
            tripDTO_input.setPassengers(actualPassenger);
        }

        return tripRepository.save(tripDTO_input.toTrip());
    }

    @Override
    public Trip updateTrip(TripDTO_Input tripDTO_input) {
        Optional<Trip> verifyTrip = tripRepository.findById(tripDTO_input.getId_trip());
        if(verifyTrip.isEmpty())
            throw new EntityNotFoundException("Not existent trip, check the id_trip");

        if(tripDTO_input.getPassengers() != null){
            if(tripDTO_input.getPassengers().size() > 40)
                throw new UnprocessableEntityException("A trip cannot have more than 40 passengers");

            List<Passenger> actualPassenger = new ArrayList<>();
            for(Passenger passenger: tripDTO_input.getPassengers()){
                Optional<Passenger> verifyPassenger = passengerRepository.findById(passenger.getId_passenger());
                if(verifyPassenger.isEmpty())
                    throw new EntityNotFoundException("One or more passengers inside the list do not exist");
                actualPassenger.add(verifyPassenger.get());
            }
            tripDTO_input.setPassengers(actualPassenger);
        }

        return tripRepository.save(tripDTO_input.toTrip());
    }

    @Override
    public TripDTO_Output getTrip(Integer id) {
        Optional<Trip> verifyTrip = tripRepository.findById(id);
        if(verifyTrip.isEmpty())
            throw new EntityNotFoundException("Not existent trip, check the id_trip");

        return verifyTrip.get().toDTO();
    }

    @Override
    public List<TripDTO_Output> getAllTrip() {
        List<Trip> trips = tripRepository.findAll();
        List<TripDTO_Output> tripDTO_outputs = new ArrayList<>();
        for(Trip trip: trips){
            tripDTO_outputs.add(trip.toDTO());
        }
        return tripDTO_outputs;
    }

    @Override
    public String deleteTrip(Integer id) {
        Optional<Trip> verifyTrip = tripRepository.findById(id);
        if(verifyTrip.isEmpty())
            throw new EntityNotFoundException("Not existent trip, check the id_trip");

        tripRepository.deleteById(id);
        return "Trip deleted successfully";
    }

    @Override
    public TripDTO_Output addPassengerTrip(Integer idTrip, Integer idPassenger) {
        Optional<Trip> verifyTrip = tripRepository.findById(idTrip);
        Optional<Passenger> verifyPassenger = passengerRepository.findById(idPassenger);
        if(verifyTrip.isEmpty())
            throw new EntityNotFoundException("Not existent trip, check the id");
        if(verifyPassenger.isEmpty())
            throw new EntityNotFoundException("Not existent passenger, check the id");

        List<Passenger> passengers = verifyTrip.get().getPassengers();
        passengers.add(verifyPassenger.get());
        verifyTrip.get().setPassengers(passengers);
        return tripRepository.save(verifyTrip.get()).toDTO();
    }

    @Override
    public Integer countPassengerTrip(Integer idTrip) {
        Optional<Trip> verifyTrip = tripRepository.findById(idTrip);
        if(verifyTrip.isEmpty())
            throw new EntityNotFoundException("Not existent trip, check the id");
        return verifyTrip.get().getPassengers().size();
    }

    @Override
    public TripDTO_Output changeStatusTrip(Integer idTrip, String status) {
        Optional<Trip> verifyTrip = tripRepository.findById(idTrip);
        if(verifyTrip.isEmpty())
            throw new EntityNotFoundException("Not existent trip, check the id");
        verifyTrip.get().setStatus(status);
        return tripRepository.save(verifyTrip.get()).toDTO();
    }

    @Override
    public String getStatusTrip(Integer idTrip) {
        Optional<Trip> verifyTrip = tripRepository.findById(idTrip);
        if(verifyTrip.isEmpty())
            throw new EntityNotFoundException("Not existent trip, check the id");
        return verifyTrip.get().getStatus();
    }
}
