package com.example.block16springcloud;

import com.example.block16springcloud.passenger.infraestructure.dto.PassengerDTO_Output;
import com.example.block16springcloud.passenger.model.Passenger;
import com.example.block16springcloud.trip.model.Trip;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="feign", url="localhost:8081")
public interface Feign {
    /*@PostMapping("/ticket")
    public String addTicket(@RequestParam Passenger passenger,
                            @RequestParam Trip trip);

     */
    @PostMapping(value="/ticket")
    public void addTicket(@RequestParam String passenger,
                          @RequestParam String trip);
}
