package com.example.block16springcloud.passenger.infraestructure.repository;

import com.example.block16springcloud.passenger.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
}
