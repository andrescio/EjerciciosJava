package com.example.block16springcloud.passenger.model;

import com.example.block16springcloud.passenger.infraestructure.dto.PassengerDTO_Output;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
public class Passenger {
    @Id
    @GeneratedValue
    Integer id_passenger;

    String name;

    String surname;

    int age;

    String email;

    int phone_number;

    public PassengerDTO_Output toDTO(){
        return new PassengerDTO_Output( this.id_passenger,
                                        this.name,
                                        this.surname,
                                        this.email);
    }

}
