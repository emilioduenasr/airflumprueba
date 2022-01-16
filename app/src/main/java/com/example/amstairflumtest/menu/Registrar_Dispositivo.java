package com.example.amstairflumtest.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amstairflumtest.R;
import com.example.amstairflumtest.menu.Clases.Clases;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registrar_Dispositivo extends AppCompatActivity {

    EditText nombreDis, descripcionDis;
    Button guardarDis, RegistEscenario;

    //variable firebase
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_dispositivo);

        //Variable de Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //Variables del Formulario
        nombreDis = (EditText) findViewById(R.id.nameDispositive);
        descripcionDis = (EditText) findViewById(R.id.descriptionDispositive);

        RegistEscenario = (Button) findViewById(R.id.RegistrarEscen);
        RegistEscenario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registrar_Dispositivo.this, Registrar_Escenario.class);
                startActivity(intent);
                finish();
            }
        });
        guardarDis = (Button) findViewById(R.id.btn_RegistrarDispositive);

        guardarDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombreDis.getText().toString().isEmpty()==true) {
                    nombreDis.setError("campo obligatorio");
                }
                if (descripcionDis.getText().toString().isEmpty()==true) {
                    descripcionDis.setError("campo obligatorio");
                }

                if (nombreDis.getText().toString().isEmpty()==false) {
                    if (descripcionDis.getText().toString().isEmpty() == false) {
                                registrarDispositivo();
                                nombreDis.setEnabled(false);
                                descripcionDis.setEnabled(false);

                            }
                            else
                            {
                                Toast.makeText(Registrar_Dispositivo.this,"Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

        });
    }
    public void registrarDispositivo (){
        String nombreD = nombreDis.getText().toString();
        String descripcionD = descripcionDis.getText().toString();
        mDatabase.child("Dispositivos").child(nombreD).child("nombre").setValue(nombreD);
        mDatabase.child("Dispositivos").child(nombreD).child("descripcion").setValue(descripcionD);
        //mDatabase.child("Dispositivos").child(nombreD).child("Escenarios").child(id).setValue(descripcionD);
        Toast.makeText(this,"Dispositivo Registrado",Toast.LENGTH_LONG).show();

        guardarDis.setVisibility(View.INVISIBLE);
        RegistEscenario.setVisibility(View.VISIBLE);

    }

}