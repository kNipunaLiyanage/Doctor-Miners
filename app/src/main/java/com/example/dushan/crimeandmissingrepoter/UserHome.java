package com.example.dushan.crimeandmissingrepoter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UserHome extends AppCompatActivity {
    Button btn1,btn2,btn3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);







        btn1 =(Button) findViewById(R.id.rptcrime);
        btn2 = (Button) findViewById(R.id.rptmissing);
        btn3 = (Button) findViewById(R.id.logouruser);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jj = new Intent(UserHome.this, RepotCrime.class);
                startActivity(jj);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jj = new Intent(UserHome.this, MissingpersonReport.class);
                startActivity(jj);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent jj = new Intent(UserHome.this, mainHome.class);
                startActivity(jj);
            }
        });


    }
}
