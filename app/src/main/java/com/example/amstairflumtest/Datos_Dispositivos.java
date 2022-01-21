package com.example.amstairflumtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Datos_Dispositivos extends AppCompatActivity {
    Button btn_WS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_dispositivos);
        btn_WS = findViewById(R.id.btn_WS);
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

}