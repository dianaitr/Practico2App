package prueba.app.firebase.myfirebaseapp.model;

import android.app.Person;

import java.util.List;

public class Categoria {

    private int id;

    public Categoria() {
    }

    private String nombre;
   private int cantidadUsuarios;
   private List<Superheroe> superheroes;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadUsuarios() {
        return cantidadUsuarios;
    }

    public void setCantidadUsuarios(int cantidadUsuarios) {
        this.cantidadUsuarios = cantidadUsuarios;
    }

    public List<Superheroe> getSuperheroes() {
        return superheroes;
    }

    public void setSuperheroes(List<Superheroe> superheroes) {
        this.superheroes = superheroes;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
