package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

import static java.lang.Integer.parseInt;

// Clase con diferentes métodos relacionados con archivos CSV
public class LeerCSV {
    // Método que recibe la ruta de un CSV y devuelve su contenido como una lista de Person
    public static List<Person> LeerCSV(String ruta) throws FileNotFoundException {
        List<Person> listaPersonas = new ArrayList<>();
        Scanner sc = new Scanner(new File(ruta));
        int i = 1;
        while (sc.hasNext()){
            try{
                String fila = sc.nextLine();
                String[] datosPerson = fila.split(":",-1);
                if(datosPerson.length != 3){
                    throw new InvalidLineFormatException("La línea "+i+" no tiene el formato adecuado");
                }
                if(datosPerson[0].trim() == ""){
                    throw new InvalidLineFormatException("La línea "+i+" no tiene puesto un nombre");
                }
                if(datosPerson[2] == ""){
                    datosPerson[2] = "0";
                }
                Person persona = new Person(datosPerson[0],datosPerson[1],parseInt(datosPerson[2]));
                listaPersonas.add(persona);
                i++;
            }
            catch(InvalidLineFormatException e){
                e.printStackTrace();
                i++;
            }
            continue;
        }
        sc.close();
        return listaPersonas;
    }

    // Método que filtra la lista de personas de un CSV según el filtro que se indique.
    public static List<Person> FiltrarCSV(String ruta, Predicate filtro) throws FileNotFoundException, InvalidLineFormatException {
        List<Person> personas = LeerCSV(ruta);
        List<Person> personasFiltradas = personas.stream()
                                        .filter(filtro)
                                        .toList();
        return personasFiltradas;
    }
}
