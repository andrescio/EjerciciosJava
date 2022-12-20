package com.example.block16springcloud.passenger.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO_Output {
    Integer id_passenger;

    String name;

    String surname;

    int age;

}
