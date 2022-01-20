package com.example.amstairflumtest.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.amstairflumtest.R;
import com.example.amstairflumtest.menu.Clases.Dispositivos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Eliminar_Dispositivo extends AppCompatActivity {

    EditText nombreD;
    Button eliminarD;

    ListView listV_Dispos;
    private List<Dispositivos> listDispo = new ArrayList<Dispositivos>();
    //variable firebase
    private DatabaseReference mDatabase;

    Dispositivos disposelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_dispositivo);

        nombreD = findViewById(R.id.nombre_DispositiveDeleted);
        eliminarD = findViewById(R.id.Eliminar_Dispositivo);
        //Variable de Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        listV_Dispos = findViewById(R.id.listade_Dispositivos);
        listarDatos();

        listV_Dispos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                disposelected =(Dispositivos) parent.getItemAtPosition(position);
                nombreD.setText(disposelected.getNombre());
            }
        });

        eliminarD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombreD.getText().toString().isEmpty()==true) {
                    nombreD.setError("campo obligatorio");
                }
                if (nombreD.getText().toString().isEmpty()==false) {
                        eliminarDispositivo();
                        nombreD.setText("");
                    }
                    else
                    {
                        Toast.makeText(Eliminar_Dispositivo.this,"Error",Toast.LENGTH_LONG).show();
                    }
                }
        });
    }

    public void eliminarDispositivo() {
        String nombreDis = nombreD.getText().toString();
        mDatabase.child("Dispositivos").child(nombreDis).removeValue();
        Toast.makeText(Eliminar_Dispositivo.this,"Dispositivo ELiminado",Toast.LENGTH_LONG).show();
        Intent intent3 = new Intent(Eliminar_Dispositivo.this,Registrar_Dispositivo.class);
        startActivity(intent3);
        finish();
    }
    private void listarDatos() {
        final List<Dispositivos> dispositivos = new ArrayList<>();
        mDatabase.child("Dispositivos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listDispo.clear();
                if(snapshot.exists()){
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String id = ds.getKey();
                        String nombre = ds.child("nombre").getValue().toString();
                        dispositivos.add(new Dispositivos(id, nombre));
                    }

                    ArrayAdapter<Dispositivos> arrayAdapter = new ArrayAdapter<>(Eliminar_Dispositivo.this, android.R.layout.simple_dropdown_item_1line, dispositivos);
                    listV_Dispos.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}