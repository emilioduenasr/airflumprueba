package com.example.amstairflumtest.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.amstairflumtest.R;
import com.example.amstairflumtest.menu.Clases.Clases;
import com.example.amstairflumtest.menu.Clases.Dispositivos;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Registrar_Escenario extends AppCompatActivity {

    EditText nombre, direccion, telefono, capacidad;
    Spinner spinnerDispositivos;
    Button guardar;

    //variable firebase
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_escenario);

        //Variable de Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //Variables del Formulario
        spinnerDispositivos =(Spinner)findViewById(R.id.spinnerDispositivos);
        nombre = (EditText) findViewById(R.id.nameScenario);
        direccion = (EditText) findViewById(R.id.addresScenario);
        telefono = (EditText) findViewById(R.id.emergencyPhone);
        capacidad = (EditText) findViewById(R.id.capacityScenario);

        guardar = (Button) findViewById(R.id.btn_RegistrarE);

        loadDispositivos();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nombre.getText().toString().isEmpty()==true) {
                    nombre.setError("campo obligatorio");
                }
                if (direccion.getText().toString().isEmpty()==true) {
                    direccion.setError("campo obligatorio");
                }
                if (telefono.getText().toString().isEmpty()==true) {
                    telefono.setError("campo obligatorio");
                }
                if (capacidad.getText().toString().isEmpty()==true) {
                    capacidad.setError("campo obligatorio");
                }

                if (nombre.getText().toString().isEmpty()==false) {
                    if (direccion.getText().toString().isEmpty() == false) {
                        if (telefono.getText().toString().isEmpty() == false) {
                            if (capacidad.getText().toString().isEmpty() == false) {
                                registrarEscenario();
                                nombre.setText("");
                                direccion.setText("");
                                telefono.setText("");
                                capacidad.setText("");
                            }
                            else
                            {
                                Toast.makeText(Registrar_Escenario.this,"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    }
            }
        });
    }

    //Cargamos los dispositivos ingresados en firebasse a un Spinner
    public void loadDispositivos () {
        final List<Dispositivos> dispositivos = new ArrayList<>();
        mDatabase.child("Dispositivos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String id = ds.getKey();
                        String nombre = ds.child("nombre").getValue().toString();
                        dispositivos.add(new Dispositivos(id, nombre));
                    }

                    ArrayAdapter<Dispositivos> arrayAdapter = new ArrayAdapter<>(Registrar_Escenario.this, android.R.layout.simple_dropdown_item_1line, dispositivos);
                    spinnerDispositivos.setAdapter(arrayAdapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Funcion para guardar los datos ingresados en el formulario registro de Escenarios
    public void registrarEscenario (){

        //Variables para guardar en firebase
        String nombreDispo = spinnerDispositivos.getSelectedItem().toString();
        String nombreE = nombre.getText().toString();
        String direccionE = direccion.getText().toString();
        String telefonoE = telefono.getText().toString();
        String capacidadE = capacidad.getText().toString();

        //obtención del identificador para guardar con los datos del formulario registrar Escenario
        String id = mDatabase.push().getKey();
        Clases escenario = new Clases(id,nombreDispo ,nombreE, direccionE, telefonoE,capacidadE);

        //envio de los datos del formulario a firebase
        mDatabase.child("Dispositivos").child(nombreDispo).child("Escenarios").child(id).setValue(escenario);
        Toast.makeText(this,"Escenario Registrado",Toast.LENGTH_LONG).show();

    }
}