package com.example.block16springcloud.trip.infraestructure.repository;

import com.example.block16springcloud.trip.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}
