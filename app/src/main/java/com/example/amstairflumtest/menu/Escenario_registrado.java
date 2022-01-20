package com.example.amstairflumtest.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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
        db_reference = FirebaseDatabase.getInstance().getReference().child("Dispositivos");
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
            LinearLayout contDisp = (LinearLayout) findViewById(R.id.Disp);

            String nameVal = String.valueOf(snapshot.child("nombre").getValue());
            String descripVal = String.valueOf(snapshot.child("descripcion").getValue());


            TextView name = new TextView(getApplicationContext());
            name.setText(nameVal);
            contDisp.addView(name);
            

            TextView descrip = new TextView(getApplicationContext());
            descrip.setText(descripVal);
            contDisp.addView(descrip);

            for (DataSnapshot snapshot1 : snapshot.child("Escenarios").getChildren()) {
                LinearLayout contEsc = (LinearLayout) findViewById(R.id.escenarios);

                String esceVal = String.valueOf(snapshot1.child("nombre").getValue());

                TextView escenario = new TextView(getApplicationContext());
                escenario.setText(esceVal);
                contEsc.addView(escenario);

                ///////
                String aforoVal = String.valueOf(snapshot1.child("capacidad").getValue());

                TextView aforo = new TextView(getApplicationContext());
                aforo.setText(aforoVal);
                contEsc.addView(aforo);

                /////
                String addVal = String.valueOf(snapshot1.child("direccion").getValue());

                TextView add = new TextView(getApplicationContext());
                add.setText(addVal);
                contEsc.addView(add);

                /////
                String fonoVal = String.valueOf(snapshot1.child("telefono").getValue());

                TextView fono = new TextView(getApplicationContext());
                fono.setText(fonoVal);
                contEsc.addView(fono);

                /////
                String tempVal = String.valueOf(snapshot1.child("temperatura").getValue());

                TextView temp = new TextView(getApplicationContext());
                temp.setText(tempVal+" °C");
                contEsc.addView(temp);

                /////
                String gasVal = String.valueOf(snapshot1.child("gas").getValue());

                TextView gas = new TextView(getApplicationContext());
                gas.setText(gasVal+" ppm");
                contEsc.addView(gas);

                /////
                String humVal = String.valueOf(snapshot1.child("humedad").getValue());

                TextView hum = new TextView(getApplicationContext());
                hum.setText(humVal+" %");
                contEsc.addView(hum);
            }

        }
    }
