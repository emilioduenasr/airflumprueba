package com.example.amstairflumtest.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.amstairflumtest.MainActivity;
import com.example.amstairflumtest.R;
import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {

    Button btn_EscenariosReg, btn_RegistrarEscen,btn_RegistrarDispos, btn_cerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_EscenariosReg= findViewById(R.id.btn_EscenariosReg);
        btn_RegistrarDispos= findViewById(R.id.btn_RegistrarDispo);
        btn_RegistrarEscen = findViewById(R.id.btn_RegistrarNEscenario);
        btn_cerrarSesion= findViewById(R.id.btn_cerrarSesion);


        //Funcion para visualizar los escenarios registrados
        btn_EscenariosReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this,Escenario_registrado.class);
                startActivity(intent);
            }
        });
        //Funcion para registrar nuevos Dispositivos
        btn_RegistrarDispos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Menu.this,Registrar_Dispositivo.class);
                startActivity(intent2);
            }
        });
        //Funion para cambiar al formulario Registrar Escenario
        btn_RegistrarEscen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Menu.this,Registrar_Escenario.class);
                startActivity(intent3);
            }
        });
        //Funcion para Cerrar Sesion
        btn_cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(Menu.this, MainActivity.class);
                intent.putExtra("msg", "cerrarSesion");
                startActivity(intent);
                Toast toast=Toast.makeText(getApplicationContext(),"Sesión cerrada",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}