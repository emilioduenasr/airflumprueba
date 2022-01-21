package com.example.amstairflumtest.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amstairflumtest.R;
import com.example.amstairflumtest.R;
import com.example.amstairflumtest.menu.Clases.Clases;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Escenario_registrado extends AppCompatActivity {
    DatabaseReference db_reference;
    Button btn_WS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escenario_registrado);
        btn_WS = findViewById(R.id.btn_WS);
        db_reference = FirebaseDatabase.getInstance().getReference().child("Dispositivos");
        leerRegistros();
        btn_WS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_VIEW);
                String url = "whatsapp://send?phone=593992235040&text=Un escenario presenta problemas";
                sendIntent.setData(Uri.parse(url));
                startActivity(sendIntent);
            }
        });

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
                LinearLayout contMadre = findViewById(R.id.Madre);

                LinearLayout contDisp = new LinearLayout(getApplicationContext());
                contDisp.setGravity(Gravity.CENTER);
                contDisp.setOrientation(LinearLayout.VERTICAL);
                contDisp.setBackgroundResource(R.drawable.gradient_background);
                //////
                String nameVal = String.valueOf(snapshot.child("nombre").getValue());
                String descripVal = String.valueOf(snapshot.child("descripcion").getValue());


                TextView name = new TextView(getApplicationContext());
                name.setText(nameVal);
                contDisp.addView(name);


                TextView descrip = new TextView(getApplicationContext());
                descrip.setText(descripVal);
                contDisp.addView(descrip);

                for (DataSnapshot snapshot1 : snapshot.child("Escenarios").getChildren()) {
                    LinearLayout contEsc = new LinearLayout(getApplicationContext());
                    contEsc.setGravity(Gravity.CENTER);
                    contEsc.setOrientation(LinearLayout.VERTICAL);

                    String esceVal = String.valueOf(snapshot1.child("nombre").getValue());
                    String aforoVal = String.valueOf(snapshot1.child("capacidad").getValue());
                    String addVal = String.valueOf(snapshot1.child("direccion").getValue());
                    String fonoVal = String.valueOf(snapshot1.child("telefono").getValue());
                    String tempVal = String.valueOf(snapshot1.child("temperatura").getValue());
                    String gasVal = String.valueOf(snapshot1.child("gas").getValue());
                    String humVal = String.valueOf(snapshot1.child("humedad").getValue());

                    TextView datos = new TextView(getApplicationContext());
                    datos.setTextColor(Color.parseColor("#FFFFFF"));
                    datos.append("Lugar: " + esceVal);
                    datos.append("\n" + "Aforo: "  + aforoVal);
                    datos.append("\n" +"Dirección: "  + addVal);
                    datos.append("\n" +"Teléfono: "  + fonoVal);
                    contEsc.addView(datos);

                    TextView temp = new TextView(getApplicationContext());
                    temp.setTextColor(Color.parseColor("#FF0000"));
                    temp.setText("\n" +"Temperatura: "  + tempVal + " °C" );
                    contEsc.addView(temp);

                    TextView gas = new TextView(getApplicationContext());
                    gas.setTextColor(Color.parseColor("#FFFFFF"));
                    gas.setText("\n" +"Nivel de gas: "  + gasVal + " ppm" );
                    contEsc.addView(gas);

                    TextView hum = new TextView(getApplicationContext());
                    hum.setTextColor(Color.parseColor("#FFFFFF"));
                    hum.setText("\n" +"Humedad: "  + humVal + " %" );
                    contEsc.addView(hum);





                    //param.append("\n" +"Temperatura: "  + tempVal + " °C");
                    //param.append("\n" +"Nivel de gas: "  + gasVal + " ppm");
                    //param.append("\n" +"Humedad "  + humVal + " %");




                    contDisp.addView(contEsc);


                }

                contMadre.addView(contDisp);
        }
    }
