package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    // Método principal. Da la ruta del CSV y llama a los métodos anteriores para evaluar las expresiones
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        String ruta = "block1-expression-evaluator/src/main/resources/expresiones.csv";
        Scanner sc = new Scanner(new File(ruta));
        int i = 1;
        while (sc.hasNext()) {
            String fila = sc.nextLine();
            System.out.println(comprobarExpresion(fila));
        }
        sc.close();
    }

    // Método que verifica de qué tipo es la expresión. Devuelve un String con la línea original y su expresión ejecutada.
    public static String comprobarExpresion(String linea) throws ParseException {
        String[] partes=linea.split(" ");
        String tipoExpresion = "";
        for(String parte:partes){
            if(parte.contains("\"")){
                tipoExpresion = "cadena";
                break;
            }
            else if(parte.matches("\\d{4}/\\d{2}/\\d{2}")){
                tipoExpresion = "fecha";
                break;
            }
            else{
                tipoExpresion = "numero";
                break;
            }
        }
        return linea + ": " + ejecutarExpresion(partes, tipoExpresion);
    }

    // Método que ejecuta una expresión. Recibe por parámetros una línea partida y el tipo de expresión que es.
    // Devuelve la expresion ejecutada.
    public static String ejecutarExpresion(String[] linea, String expresion) throws ParseException {
        String resultado = linea[0].replace("\"","");
        int i = 1;
        // Ejecuta la expresión en caso de ser de cadena.
        if(expresion.equals("cadena")){
            for (i=0;i<linea.length;i++){
                if(i%2 != 0){
                    switch(linea[i]){
                        case "+":   resultado = resultado + " " + linea[i+1].replace("\"","");
                                    break;
                        case "*":   resultado = resultado + resultado;
                                    break;
                    }
                }
            }
        }
        // Ejecuta la expresión en caso de ser de fecha
        if(expresion.equals("fecha")) {
            DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            for (i=0;i<linea.length;i++){
                if(i%2 != 0){
                    switch(linea[i]){
                        case ">":   resultado = String.valueOf(formatter.parse(resultado).compareTo(formatter.parse(linea[i+1])) > 0);
                                    break;
                        case "<":   resultado = String.valueOf(formatter.parse(resultado).compareTo(formatter.parse(linea[i+1])) < 0);
                                    break;

                        case "=":   resultado = String.valueOf(formatter.parse(resultado).compareTo(formatter.parse(linea[i+1])) == 0);
                                    break;
                    }
                }
            }
        }
        // Ejecuta la expresión en caso de ser numérica
        if(expresion.equals("numero")){
            for (i=1;i<linea.length;i++){
                if(i%2 != 0){
                    switch(linea[i]){
                        case "+":   resultado = String.valueOf(parseInt(resultado) + parseInt(linea[i+1]));
                                    break;
                        case "-":   resultado =  String.valueOf(parseInt(resultado) - parseInt(linea[i+1]));
                                    break;
                        case "*":   resultado =  String.valueOf(parseInt(resultado) * parseInt(linea[i+1]));
                                    break;
                        case "/":   resultado =  String.valueOf(parseInt(resultado) / parseInt(linea[i+1]));
                                    break;
                    }
                }
            }
        }
        return resultado;
    }
}