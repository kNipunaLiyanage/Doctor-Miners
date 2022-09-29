package com.example.dushan.crimeandmissingrepoter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Testmailz extends AppCompatActivity {
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testmailz);

        b1= findViewById(R.id.sendmailz);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    GMailSender sender = new GMailSender("", "");
                    sender.sendMail("This is Subject",
                            "This is Body",
                            "k.nipunaliyanage@gmail.com",
                            "liyanagenipu4@gmail.com");
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }
        });
    }
}