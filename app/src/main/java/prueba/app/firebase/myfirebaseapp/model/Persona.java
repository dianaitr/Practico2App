package prueba.app.firebase.myfirebaseapp.model;

public class Persona {

    private String uid;
    private String edad;
    private String sexo;
    private int categoria;

    public int getIdSuperheroe() {
        return idSuperheroe;
    }

    public void setIdSuperheroe(int idSuperheroe) {
        this.idSuperheroe = idSuperheroe;
    }

    private int idSuperheroe;


    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }



    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }


    public Persona() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }



    @Override
    public String toString() {
        return categoria+"";
    }
}
