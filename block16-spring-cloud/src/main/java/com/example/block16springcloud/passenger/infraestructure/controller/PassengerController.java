package com.example.block16springcloud.passenger.infraestructure.controller;

import com.example.block16springcloud.Utils;
import com.example.block16springcloud.passenger.infraestructure.dto.PassengerDTO_Input;
import com.example.block16springcloud.passenger.infraestructure.dto.PassengerDTO_Output;
import com.example.block16springcloud.passenger.model.Passenger;
import com.example.block16springcloud.passenger.service.PassengerServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Autowired
    PassengerServiceImpl passengerServiceImpl;

    @PostMapping
    public Passenger addPassenger(@Valid @RequestBody PassengerDTO_Input passenger,
                                  BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            Utils.showErrors(bindingResult);

        return passengerServiceImpl.addPassenger(passenger);
    }

    @PutMapping
    public Passenger updatePassenger(@Valid @RequestBody PassengerDTO_Input passenger,
                                          BindingResult bindingResult){
        if(bindingResult.hasErrors())
            Utils.showErrors(bindingResult);

        return passengerServiceImpl.updatePassenger(passenger);
    }

    @GetMapping("/{id}")
    public PassengerDTO_Output getPassenger(@PathVariable Integer id){
        return passengerServiceImpl.getPassenger(id);
    }

    @GetMapping("/all")
    public List<PassengerDTO_Output> getAllPassengers(){
        return passengerServiceImpl.getAllPassenger();
    }

    @DeleteMapping("/{id}")
    public String deletePassenger(@PathVariable Integer id){
        return passengerServiceImpl.deletePassenger(id);
    }
}
