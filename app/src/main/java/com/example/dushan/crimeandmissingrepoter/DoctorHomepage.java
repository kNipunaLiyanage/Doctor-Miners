package com.example.dushan.crimeandmissingrepoter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class DoctorHomepage extends AppCompatActivity {
    Button b1,b2,b3;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_doctor_homepage);

        b1 = findViewById(R.id.pendingreqz2);
        b2 = findViewById(R.id.acceptrequesz2);
        b3 = findViewById(R.id.logoutdocz);


        tv1 = findViewById(R.id.loadnamedoca);
        tv1.setText("Hello "+DbConnection.usernameloadingz);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoctorHomepage.this,DoctorsPendingreqs.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoctorHomepage.this,DoctorsSubmittedz.class);
                startActivity(i);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DoctorHomepage.this,HomeBothmain.class);
                startActivity(i);
            }
        });

    }
}