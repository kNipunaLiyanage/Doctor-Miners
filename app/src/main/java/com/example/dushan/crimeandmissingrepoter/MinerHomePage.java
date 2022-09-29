package com.example.dushan.crimeandmissingrepoter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;

public class MinerHomePage extends AppCompatActivity {
    RelativeLayout relativeLayout1,relativeLayout2;
    TextView textView,textView2;
    Button btn,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_miner_home_page);

        relativeLayout1 = findViewById(R.id.electionhomeload2);
        relativeLayout2 = findViewById(R.id.feedbackhomeload);
        textView = findViewById(R.id.loadinghomenamez);
        textView.setText(""+DbConnection.usernameloadingz);
        btn = findViewById(R.id.logoutzmin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(MinerHomePage.this,HomeBothmain.class);
                startActivity(ii);
            }
        });


        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(MinerHomePage.this,SubMinerDoctorHome.class);
                startActivity(ii);
            }
        });
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(MinerHomePage.this,SelectEnquiryHome.class);
                startActivity(ii);
            }
        });

    }
}