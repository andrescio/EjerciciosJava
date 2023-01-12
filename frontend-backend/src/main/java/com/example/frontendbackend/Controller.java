package com.example.frontendbackend;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("name")
public class Controller {

    @CrossOrigin(origins = "http://localhost:63342")
    @PostMapping
    public String saveName(@RequestParam String name){
        return name + " has been saved";
    }
}
