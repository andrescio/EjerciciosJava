package com.example.block7crudvalidation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="feign", url="localhost:8081")
public interface Feign {

    @GetMapping("/profesor/{id}")
    public String getProfesorById(@PathVariable int id);
}
