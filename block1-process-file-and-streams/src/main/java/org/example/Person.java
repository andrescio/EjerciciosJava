package org.example;

// Clase que define a una persona
public class Person {
    //Propiedades
    String name;
    String town;
    int age;

    // Constructores
    public Person(String name,String town,int age){
        this.name = name;
        this.town = town;
        this.age = age;
    }

    //Getters y setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
