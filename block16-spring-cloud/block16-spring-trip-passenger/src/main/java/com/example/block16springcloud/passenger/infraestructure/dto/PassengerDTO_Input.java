package com.example.block16springcloud.passenger.infraestructure.dto;

import com.example.block16springcloud.passenger.model.Passenger;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO_Input {
    Integer id_passenger;

    @NotEmpty
    String name;

    @NotEmpty
    String surname;

    @NotNull
    Integer age;

    @NotEmpty
    String email;

    @NotNull
    Integer phone_number;

    public Passenger toPassenger(){
        return new Passenger(this.id_passenger,
                             this.name,
                             this.surname,
                             this.age,
                             this.email,
                             this.phone_number);
    }
}
