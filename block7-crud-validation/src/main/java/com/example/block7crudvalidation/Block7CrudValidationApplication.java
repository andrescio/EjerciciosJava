package com.example.block7crudvalidation;

import com.example.block7crudvalidation.Persona.Model.Persona;
import com.example.block7crudvalidation.Persona.Service.PersonaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.PostConstruct;
import java.util.*;

@SpringBootApplication
@EnableFeignClients
public class Block7CrudValidationApplication {

	@Autowired
	PersonaServiceImpl personaServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(Block7CrudValidationApplication.class, args);
	}

	// Datos a insertar en la base de datos de forma automática al ejecutar la aplicación
	@PostConstruct
	public void addPersonas(){

		//Crea la lista de personas
		List<Persona> personas = new ArrayList<>(){{
			add(new Persona(null,"acm000","contra","Andres","CM",
					"companyEmail","personalEmail","Madrid",false,
					new GregorianCalendar(2058, Calendar.DECEMBER, 12).getTime(),
					null,null));
			add(new Persona(null,"barba0","contra","barba","Lopez",
					"companyEmail","personalEmail","Madrid",false,
					new GregorianCalendar(2058, Calendar.DECEMBER, 12).getTime(),
					null,null));
			add(new Persona(null,"cactus","contra","Cactus","Gonzalez",
					"companyEmail","personalEmail","Madrid",false,
					new GregorianCalendar(2058, Calendar.DECEMBER, 12).getTime(),
					null,null));
			add(new Persona(null,"dieta0","contra","Helado","Derretido",
					"companyEmail","personalEmail","Madrid",false,
					new GregorianCalendar(2058, Calendar.DECEMBER, 12).getTime(),
					null,null));
			add(new Persona(null,"foca00","contra","hueso","duro",
					"companyEmail","personalEmail","Madrid",false,
					new GregorianCalendar(2058, Calendar.DECEMBER, 12).getTime(),
					null,null));
			add(new Persona(null,"popeye","contra","Rafa","Nadal",
					"companyEmail","personalEmail","Madrid",false,
					new GregorianCalendar(2012, Calendar.APRIL, 23).getTime(),
					null,null));
			add(new Persona(null,"lms000","contra","Leo","Messi",
					"companyEmail","personalEmail","Madrid",false,
					new GregorianCalendar(2012, Calendar.APRIL, 23).getTime(),
					null,null));
			add(new Persona(null,"serre7","contra","Crishtiano","Runaldo",
					"companyEmail","personalEmail","Madrid",false,
					new GregorianCalendar(2012, Calendar.APRIL, 23).getTime(),
					null,null));
			add(new Persona(null,"kk0000","contra","Kevin","Bruyne",
					"companyEmail","personalEmail","Madrid",false,
					new GregorianCalendar(2012, Calendar.APRIL, 23).getTime(),
					null,null));
			add(new Persona(null,"acm001","contra","Abajo","Medio",
					"companyEmail","personalEmail","Madrid",false,
					new GregorianCalendar(2012, Calendar.APRIL, 23).getTime(),
					null,null));
		}};

		// Recorre la lista y añade cada una a la BBDD mediante el método addPersona de la clase PersonaServiceImpl
		for(Persona persona: personas){
			personaServiceImpl.addPersona(persona);
		}
	}
}
