package prueba.app.firebase.myfirebaseapp.model;

public class Superheroe {

    private String nombre;
    private double porcentaje;

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

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public String toString() {
        return nombre+" ("+porcentaje+"%) ";
    }
}
