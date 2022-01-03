package com.example.amstairflumtest.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amstairflumtest.R;
import com.example.amstairflumtest.menu.Clases.Clases;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registrar_Escenario extends AppCompatActivity {

    EditText nombre, direccion, telefono, capacidad;
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
        nombre = (EditText) findViewById(R.id.nameScenario);
        direccion = (EditText) findViewById(R.id.addresScenario);
        telefono = (EditText) findViewById(R.id.emergencyPhone);
        capacidad = (EditText) findViewById(R.id.capacityScenario);

        guardar = (Button) findViewById(R.id.btn_RegistrarE);

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

    public void registrarEscenario (){
        String nombreE = nombre.getText().toString();
        String direccionE = direccion.getText().toString();
        String telefonoE = telefono.getText().toString();
        String capacidadE = capacidad.getText().toString();


        String id = mDatabase.push().getKey();
        Clases escenario = new Clases(id, nombreE, direccionE, telefonoE,capacidadE);
        mDatabase.child("Escenarios").child(id).setValue(escenario);
        Toast.makeText(this,"Escenario Registrado",Toast.LENGTH_LONG).show();

    }
}