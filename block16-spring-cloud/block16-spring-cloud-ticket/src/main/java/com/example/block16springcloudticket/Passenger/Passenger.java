package com.example.block16springcloudticket.Passenger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passenger {
    Integer id_passenger;

    String name;

    String surname;

    int age;

    String email;

    int phone_number;
}
