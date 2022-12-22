package com.example.block16springcloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="feign", url="localhost:8081")
public interface Feign {
    @PostMapping(value="/ticket")
    void addTicket(@RequestParam String passenger,
                          @RequestParam String trip);
}
