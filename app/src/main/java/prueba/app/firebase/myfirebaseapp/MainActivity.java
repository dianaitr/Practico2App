package prueba.app.firebase.myfirebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import prueba.app.firebase.myfirebaseapp.model.Categoria;
import prueba.app.firebase.myfirebaseapp.model.Persona;
import prueba.app.firebase.myfirebaseapp.model.Superheroe;

public class MainActivity extends AppCompatActivity {

    private List<Superheroe> listSuper =new ArrayList<Superheroe>();
    ArrayAdapter<Superheroe> arrayAdapterSuperheroe;
    EditText edadP;

    private RadioGroup rdg;
    private String escogido;


    ListView listV_superheroes; //SUPERHEROES

    List<Superheroe> superheroes;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private Superheroe personaSelected;
    private TextView txt_superheroeSeleccionado;


    Button btn_votar;

    List<Categoria> categoriaList;

    List<Categoria> categoriaList2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edadP =findViewById(R.id.txt_edadPersona);

        rdg= (RadioGroup) findViewById(R.id.rdg);
        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.txtFemenino){
                    escogido="F";

                }else if(checkedId==R.id.txtMasculino){
                    escogido="M";

                }
            }
        });

        listV_superheroes =findViewById(R.id.lv_datosPeronas);

        superheroes=new ArrayList<Superheroe>();
        superheroes.add(new Superheroe("Spiderman"));
        superheroes.add(new Superheroe("Ironman"));
        superheroes.add(new Superheroe("Capitán América"));
        superheroes.add(new Superheroe("Capitana Marvel"));
        superheroes.add(new Superheroe("Hulk"));
        superheroes.add(new Superheroe("La Viuda Negra"));
        superheroes.add(new Superheroe("Thor"));
        superheroes.add(new Superheroe("Doctor Strange"));


        categoriaList2=new ArrayList<Categoria>();
        Categoria c= new Categoria();
        c.setNombre("Niños");
        c.setId(0);
        c.setSuperheroesList(superheroes);
        categoriaList2.add(c);

        Categoria c1= new Categoria();
        c1.setNombre("Hombres adolescentes");
        c1.setId(1);
        c1.setSuperheroesList(superheroes);
        categoriaList2.add(c1);

        Categoria c2= new Categoria();
        c2.setNombre("Hombres adultos");
        c2.setId(2);
        c2.setSuperheroesList(superheroes);
        categoriaList2.add(c2);

        Categoria c3= new Categoria();
        c3.setNombre("Mujeres adolescentes");
        c3.setId(3);
        c3.setSuperheroesList(superheroes);
        categoriaList2.add(c3);

        Categoria c4= new Categoria();
        c4.setNombre("Mujeres adultas");
        c4.setId(4);
        c4.setSuperheroesList(superheroes);
        categoriaList2.add(c4);



        inicializarFirebase();
        //if(databaseReference.child("Superheroes")== null){
        //for (Categoria s:categoriaList2 ) {
          // databaseReference.child("Categorias").child(s.getId()+"").setValue(s);
        //}
        // }
        listarSuperheroes();

        txt_superheroeSeleccionado=findViewById(R.id.txt_superheroeSeleccionado);

        listV_superheroes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSelected=(Superheroe) parent.getItemAtPosition(position);
                txt_superheroeSeleccionado.setText(personaSelected.getNombre());

            }
        });



        btn_votar=findViewById(R.id.btn_votar);
        btn_votar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Metodo que permite categorizar la persona
                String edad= edadP.getText().toString();
                if(edad.equals("") || escogido.equals("") ){
                    validacion();

                }else{
                    int eda=Integer.parseInt(edad);
                    Persona p=new Persona();
                    p.setUid(UUID.randomUUID().toString());
                    p.setEdad(edad);

                    String categoriaObtenida="";
                    int i=0;
                    if(escogido.equals("M")){
                       // p.setCategoria("M");
                        if(eda>0 & eda<=10){
                            //guardar persona en la categoria Niños
                            categoriaObtenida="Niños";
                            //0
                            i=0;
                        }else if(eda>10 & eda<20){
                            //guardar persona en la categoria Hombres adolescentes
                            categoriaObtenida="Hombres adolescentes";
                            //1
                            i=1;
                        }else if(eda>=20){
                            //guardar persona en la categoria Hombres adultos
                            categoriaObtenida="Hombres adultos";
                            //2
                            i=2;
                        }
                    }else if(escogido.equals("F")){
                        // p.setCategoria("F");
                        if(eda>0 & eda<=10){
                            //guardar persona en la categoria Niños
                            categoriaObtenida="Niños";
                            //0
                            i=0;
                        }else if(eda>10 & eda<20){
                            //guardar persona en la categoria Mujeres adolescentes
                            categoriaObtenida="Mujeres adolescentes";
                            //3
                            i=3;
                        }else if(eda>=20){
                            //guardar persona en la categoria Mujeres adultas
                            categoriaObtenida="Mujeres adultas";
                            //4
                            i=4;
                        }
                    }



                    p.setCategoria(i);
                    Superheroe s=new Superheroe();
                    s.setNombre(escogido);
                    p.setSuperheroe(s);

                    Categoria c= categoriaList.get(i);
                    c.getPersonas().get(i);
                    c.getSuperheroesList();


                    databaseReference.child("Categorias").child(c.getId()+"").setValue(c);


                    //Toast.makeText(this,"Voto agregado",Toast.LENGTH_SHORT).show();
                    //limpiarDatos();

                }

            }
        });

    }



    private void listarSuperheroes() {


        databaseReference.child("Superheroes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSuper.clear();
                for(DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Superheroe p= objSnapshot.getValue(Superheroe.class);
                    listSuper.add(p);
                    arrayAdapterSuperheroe =new ArrayAdapter<Superheroe>(MainActivity.this,
                            android.R.layout.simple_list_item_1, listSuper);
                    listV_superheroes.setAdapter(arrayAdapterSuperheroe);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        

    }

    private void listarCategorias(){
        databaseReference.child("Categorias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listSuper.clear();
                for(DataSnapshot objSnapshot: dataSnapshot.getChildren()){
                    Superheroe p= objSnapshot.getValue(Superheroe.class);
                    listSuper.add(p);
                    arrayAdapterSuperheroe =new ArrayAdapter<Superheroe>(MainActivity.this,
                            android.R.layout.simple_list_item_1, listSuper);
                    listV_superheroes.setAdapter(arrayAdapterSuperheroe);
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



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String edad= edadP.getText().toString();

        //String app= appP.getText().toString();


        switch (item.getItemId()){
            case R.id.icon_add:
                if(edad.equals("") || escogido.equals("") ){
                    validacion();

                }else{
                    Persona p=new Persona();
                    p.setUid(UUID.randomUUID().toString());
                    p.setEdad(edad);


                    databaseReference.child("Persona").child(p.getUid()).setValue(p);
                    Toast.makeText(this,"Agregado",Toast.LENGTH_SHORT).show();
                    limpiarDatos();

                }
                break;

            case R.id.icon_save:
                //ACTUALIZAR
                Persona p= new Persona();
               // p.setUid(personaSelected.getUid());
                p.setEdad(edadP.getText().toString().trim());


                databaseReference.child("Persona").child(p.getUid()).setValue(p);
                Toast.makeText(this,"Actualizado",Toast.LENGTH_SHORT).show();
                limpiarDatos();
                break;

            case R.id.icon_delete:
                Persona p1=new Persona();
              //  p1.setUid(personaSelected.getUid());
                databaseReference.child("Persona").child(p1.getUid()).removeValue();
                limpiarDatos();
                Toast.makeText(this,"Eliminado",Toast.LENGTH_SHORT).show();
                break;
                default: break;
        }
        return true;
    }

    public void validacion(){
        String nombre= edadP.getText().toString();


        if(nombre.equals("")){
            edadP.setError("Required");
        }

    }

    public void limpiarDatos(){
        edadP.setText("");

    }
}
