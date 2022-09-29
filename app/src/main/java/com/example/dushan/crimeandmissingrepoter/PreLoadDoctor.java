package com.example.dushan.crimeandmissingrepoter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class PreLoadDoctor extends AppCompatActivity {
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_pre_load_doctor);

        b1 = findViewById(R.id.btnlog_doc);
        b2 = findViewById(R.id.btnreg_doc);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(PreLoadDoctor.this,LoginDoctor.class);
                startActivity(ii);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(PreLoadDoctor.this,RegisterDoctor.class);
                startActivity(ii);
            }
        });
    }
}