package prueba.app.firebase.myfirebaseapp.model;

public class Superheroe {

    private int id;
    private String nombre;
    private int votos;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public Superheroe(String nombre) {
        this.nombre = nombre;

    }

    public Superheroe() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    @Override
    public String toString() {
        return nombre;
    }
}
