package prueba.app.firebase.myfirebaseapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import prueba.app.firebase.myfirebaseapp.model.Categoria;
import prueba.app.firebase.myfirebaseapp.model.Superheroe;

public class Main4Activity extends AppCompatActivity {

    Button btn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        inicializarFirebase();
        btn= findViewById(R.id.btn_iniciar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Superheroe>   superheroes=new ArrayList<Superheroe>();
                Superheroe s0=new Superheroe();
                s0.setId(0);
                s0.setVotos(0);
                s0.setNombre("Capitana Marvel");
                superheroes.add(s0);


                Superheroe s1=new Superheroe();
                s1.setId(1);
                s1.setVotos(0);
                s1.setNombre("Capitan América");
                superheroes.add(s1);

                Superheroe s2=new Superheroe();
                s2.setId(2);
                s2.setVotos(0);
                s2.setNombre("Doctor Strange");
                superheroes.add(s2);


                Superheroe s3=new Superheroe();
                s3.setId(3);
                s3.setVotos(0);
                s3.setNombre("Hulk");
                superheroes.add(s3);

                Superheroe s4=new Superheroe();
                s4.setId(4);
                s4.setVotos(0);
                s4.setNombre("Ironman");
                superheroes.add(s4);


                Superheroe s5=new Superheroe();
                s5.setId(5);
                s5.setVotos(0);
                s5.setNombre("La Viuda Negra");
                superheroes.add(s5);

                Superheroe s6=new Superheroe();
                s6.setId(6);
                s6.setVotos(0);
                s6.setNombre("Spiderman");
                superheroes.add(s6);

                Superheroe s7=new Superheroe();
                s7.setId(7);
                s7.setVotos(0);
                s7.setNombre("Thor");
                superheroes.add(s7);


//categorias se inicializan por primera vez
                List<Categoria> categoriaList2=new ArrayList<Categoria>();
                Categoria c= new Categoria();
                c.setNombre("Niños");
                c.setId(0);
                c.setSuperheroes(superheroes);
                categoriaList2.add(c);

                Categoria c1= new Categoria();
                c1.setNombre("Hombres adolescentes");
                c1.setId(1);
                c1.setSuperheroes(superheroes);
                categoriaList2.add(c1);

                Categoria c2= new Categoria();
                c2.setNombre("Hombres adultos");
                c2.setId(2);
                c2.setSuperheroes(superheroes);
                categoriaList2.add(c2);

                Categoria c3= new Categoria();
                c3.setNombre("Mujeres adolescentes");
                c3.setId(3);
                c3.setSuperheroes(superheroes);
                categoriaList2.add(c3);

                Categoria c4= new Categoria();
                c4.setNombre("Mujeres adultas");
                c4.setId(4);
                c4.setSuperheroes(superheroes);
                categoriaList2.add(c4);


                for (Categoria s:categoriaList2 ) {
                    databaseReference.child("Categorias").child(s.getNombre()).setValue(s);
                }

                Intent i= new Intent(Main4Activity.this,Main2Activity.class);
                //i.putExtra("firebase",firebaseDatabase);
                startActivity(i);
            }
        });
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference=firebaseDatabase.getReference();




    }
}
