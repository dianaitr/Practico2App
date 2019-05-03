package prueba.app.firebase.myfirebaseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import prueba.app.firebase.myfirebaseapp.model.Categoria;
import prueba.app.firebase.myfirebaseapp.model.Superheroe;

public class Main2Activity extends AppCompatActivity {


    RadioGroup rdg;
    String escogido;
    TextView txt_capMarvel,txt_capAmerica,txt_drStrange,txt_hulk,txt_ironMan,txt_viudaNegra,txt_thor,txt_spiderman,txt_cantUsuarios;
    Button btn_votar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    List<Categoria> categoriaList;
    Categoria categoriaSeleccionada;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        inicializarFirebase();
        categoriaList=new ArrayList<Categoria>();
        listarCategorias();


        txt_capMarvel=findViewById(R.id.txt_capMarvel);
        txt_capAmerica=findViewById(R.id.txt_capAmerica);
        txt_drStrange= findViewById(R.id.txt_drStrange);
        txt_hulk=findViewById(R.id.txt_hulk);
        txt_ironMan=findViewById(R.id.txt_ironMan);
        txt_viudaNegra=findViewById(R.id.txt_viudaNegra);
        txt_spiderman=findViewById(R.id.txt_spiderman);
        txt_thor=findViewById(R.id.txt_thor);

        txt_cantUsuarios=findViewById(R.id.txt_cantUsuarios);

        rdg= (RadioGroup) findViewById(R.id.rdg);
        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                ArrayList<Double> percent= new ArrayList<Double>();

                if(checkedId==R.id.txtPublico){
                    escogido="publico";

                    categoriaSeleccionada=new Categoria();

                    int cant=0;

                    for (Categoria c:categoriaList) {
                        cant+=c.getCantidadUsuarios();
                    }


                }else if(checkedId==R.id.txtNinos){
                    escogido="ninos";
                    categoriaSeleccionada=categoriaList.get(4);


                }else if(checkedId==R.id.txtMAdultas){
                    escogido="MAdultas";
                    categoriaSeleccionada=categoriaList.get(3);

                }else if(checkedId==R.id.txtMAdolescente){
                    escogido="MAdolescentes";
                    categoriaSeleccionada=categoriaList.get(2);

                }else if(checkedId==R.id.txtHAdulto){
                    escogido="HAdulto";
                    categoriaSeleccionada=categoriaList.get(1);

                }else if(checkedId==R.id.txtHAdolescente){
                    escogido="HAdolescente";
                    categoriaSeleccionada=categoriaList.get(0);

                }

                percent.clear();

                if(escogido!="publico"){
                    for (Superheroe s:categoriaSeleccionada.getSuperheroes()) {

                        double a= (((double)s.getVotos()/(double)categoriaSeleccionada.getCantidadUsuarios())*100);
                        Log.e("HOLAAAAAAAAAAAA",a+"-----"+s.getNombre()+"---"+s.getVotos());

                        percent.add(a);
                    }
                }

                if(percent.size()>0){
                    txt_cantUsuarios.setText(categoriaSeleccionada.getCantidadUsuarios()+"");
                    txt_capMarvel.setText(percent.get(0)+"%");
                    txt_capAmerica.setText(percent.get(1)+"%");
                    txt_drStrange.setText(percent.get(2)+"%");
                    txt_hulk.setText(percent.get(3)+"%");
                    txt_ironMan.setText(percent.get(4)+"%");
                    txt_viudaNegra.setText(percent.get(5)+"%");
                    txt_spiderman.setText(percent.get(6)+"%");
                    txt_thor.setText(percent.get(7)+"%");
                }

            }
        });

        btn_votar= findViewById(R.id.btn_votar);
        btn_votar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Main2Activity.this,MainActivity.class);
                //i.putExtra("firebase",firebaseDatabase);
                startActivity(i);
            }
        });





    }

    private void listarCategorias(){
        databaseReference.child("Categorias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                categoriaList.clear();
                for(DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Categoria p= objSnapshot.getValue(Categoria.class);
                    categoriaList.add(p);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference=firebaseDatabase.getReference();

        ////////////////////////////INICIALIZAR LAS CATEGORIAS LA PRIMERA VEZ EN EL FIREBASE
        //for (Categoria s:categoriaList2) {
        //        databaseReference.child("Categorias").child(s.getNombre()).setValue(s);
        //}



    }
}
