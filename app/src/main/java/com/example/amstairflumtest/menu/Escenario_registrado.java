package com.example.amstairflumtest.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amstairflumtest.R;
import com.example.amstairflumtest.R;
import com.example.amstairflumtest.menu.Clases.Clases;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Escenario_registrado extends AppCompatActivity {
    DatabaseReference db_reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escenario_registrado);
        db_reference = FirebaseDatabase.getInstance().getReference().child("Escenarios");
        leerRegistros();
    }

    public void leerRegistros(){
        db_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mostrarRegistrosPorPantalla(snapshot);
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    System.out.println(error.toException());
                }
            });
        }
        public void mostrarRegistrosPorPantalla(DataSnapshot snapshot){
            LinearLayout contName = (LinearLayout) findViewById(R.id.ContenedorName);
            LinearLayout contAdd = (LinearLayout) findViewById(R.id.ContenedorAdd);
            LinearLayout contFono = (LinearLayout) findViewById(R.id.ContenedorFono);
            LinearLayout contAforo = (LinearLayout) findViewById(R.id.ContenedorAforo);

            String nameVal = String.valueOf(snapshot.child("nombre").getValue());
            String addVal = String.valueOf(snapshot.child("direccion").getValue());
            String fonoVal = String.valueOf(snapshot.child("telefono").getValue());
            String aforoVal = String.valueOf(snapshot.child("capacidad").getValue());

            TextView name = new TextView(getApplicationContext());
            name.setText(nameVal);
            contName.addView(name);

            TextView add = new TextView(getApplicationContext());
            add.setText(addVal);
            contAdd.addView(add);

            TextView fono = new TextView(getApplicationContext());
            fono.setText(fonoVal);
            contFono.addView(fono);

            TextView aforo = new TextView(getApplicationContext());
            aforo.setText(aforoVal);
            contAforo.addView(aforo);
        }
    }
