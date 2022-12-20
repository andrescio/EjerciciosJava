package com.example.block16springcloud.trip.infraestructure.controller;

import com.example.block16springcloud.Utils;
import com.example.block16springcloud.trip.infraestructure.dto.TripDTO_Input;
import com.example.block16springcloud.trip.infraestructure.dto.TripDTO_Output;
import com.example.block16springcloud.trip.model.Trip;
import com.example.block16springcloud.trip.service.TripServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    TripServiceImpl tripServiceImpl;

    @PostMapping
    public Trip addTrip(@Valid @RequestBody TripDTO_Input trip,
                        BindingResult bindingResult){
        if(bindingResult.hasErrors())
            Utils.showErrors(bindingResult);

        return tripServiceImpl.addTrip(trip);
    }

    @PutMapping
    public Trip updateTrip(@Valid @RequestBody TripDTO_Input trip,
                           BindingResult bindingResult){
        if(bindingResult.hasErrors())
            Utils.showErrors(bindingResult);

        return tripServiceImpl.updateTrip(trip);
    }

    @GetMapping("/{id}")
    public TripDTO_Output getTrip(@PathVariable Integer id){
        return tripServiceImpl.getTrip(id);
    }

    @GetMapping("/all")
    public List<TripDTO_Output> getAllTrip(){
        return tripServiceImpl.getAllTrip();
    }

    @DeleteMapping("/{id}")
    public String deleteTrip(@PathVariable Integer id){
        return tripServiceImpl.deleteTrip(id);
    }

    @PostMapping("/addPassenger/{idTrip}/{idPassenger}")
    public TripDTO_Output addPassengerTrip(@PathVariable Integer idTrip,
                                           @PathVariable Integer idPassenger){
        return tripServiceImpl.addPassengerTrip(idTrip,idPassenger);
    }

    @GetMapping("/count/{id}")
    public Integer countPassengerTrip(@PathVariable Integer id){
        return tripServiceImpl.countPassengerTrip(id);
    }

    @PutMapping("/{idTrip}/{status}")
    public TripDTO_Output changeStatusTrip(@PathVariable Integer idTrip,
                                           @PathVariable String status){
        return tripServiceImpl.changeStatusTrip(idTrip,status);
    }

    @GetMapping("/verify/{idTrip}")
    public String getStatusTrip(@PathVariable Integer idTrip){
        return tripServiceImpl.getStatusTrip(idTrip);
    }
}
