package prueba.app.firebase.myfirebaseapp.model;

import android.app.Person;

import java.util.List;

public class Categoria {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private List<Superheroe> superheroesList;
    private String nombre;
    private List<Persona> personas;

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public int getTotalUsuarios() {
        return totalUsuarios;
    }

    public void setTotalUsuarios(int totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }

    private int totalUsuarios;

    public Categoria() {


    }

    public List<Superheroe> getSuperheroesList() {
        return superheroesList;
    }

    public void setSuperheroesList(List<Superheroe> superheroesList) {
        this.superheroesList = superheroesList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
