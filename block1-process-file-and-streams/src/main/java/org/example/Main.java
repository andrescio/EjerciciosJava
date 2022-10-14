package org.example;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Main {

    //Método que devuelve los datos de una lista de personas por pantalla
    public static void datosPersonas(List<Person> personas){
        personas.stream().forEach((p)-> {
            if(p.getTown() == ""){
                p.setTown("Unknown");
            }
            String edad = "";
            edad = (p.getAge() == 0)?"Unknown":Integer.toString(p.getAge());
            System.out.println("Name: "+p.getName()+". Town: "+p.getTown()+". Age: "+edad);
        });
    }

    // Método principal. Encargado de ejecutarse para obtener las respuestas a los ejercicios haciendo uso
    // del resto de clases, métodos y funciones
    public static void main(String[] args) throws FileNotFoundException, InvalidLineFormatException {
        String ruta = "block1-process-file-and-streams/src/main/resources/people.csv";
        LeerCSV csv = new LeerCSV();

        // Apartado A
        Predicate<Person> FiltroA = u->u.getAge()<=25 && u.getAge()!=0; // Filtro que se pasa a FiltrarCSV
        List<Person> contenidoCSV_A = csv.FiltrarCSV(ruta, FiltroA);
        System.out.println("\nApartado A");
        datosPersonas(contenidoCSV_A);

        // Apartado B
        Predicate<Person> FiltroB = u->!u.getName().substring(0,1).equals("A"); //Filtro que se pasa a FiltrarCSV
        List<Person> contenidoCSV_B = csv.FiltrarCSV(ruta, FiltroB);
        System.out.println("\nApartado B");
        datosPersonas(contenidoCSV_B);

        // Apartado C
        Optional<Person> contenidoCSV_C = contenidoCSV_A.stream()
                                                        .filter(u->u.getTown().equals("Madrid"))
                                                        .findFirst();
        System.out.println("\nApartado C");
        datosPersonas(contenidoCSV_C.stream().toList());

        // Apartado D
        Optional<Person> contenidoCSV_D = contenidoCSV_A.stream()
                                                        .filter(u->u.getTown().equals("Barcelona"))
                                                        .findFirst();
        System.out.println("\nApartado D");
        datosPersonas(contenidoCSV_D.stream().toList());
    }
}