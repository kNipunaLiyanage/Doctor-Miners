package com.example.dushan.crimeandmissingrepoter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class SingleCrimeDetails extends AppCompatActivity implements AsyncResponse {



    TextView textView1,textView2,textView3,textView4,textView5,textView6;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_crime_details);


        textView1 = findViewById(R.id.textviewone);
        textView2 = findViewById(R.id.textviewtwo);
        textView3 = findViewById(R.id.textviewthree);
        textView4 = findViewById(R.id.textviefour);
        textView5 = findViewById(R.id.textviefive);
        textView6 = findViewById(R.id.textviewsix);

        button = (Button) findViewById(R.id.markasreported);
        final String crimeidekaloadz =getIntent().getStringExtra("crimeidz");

        try{
           // Toast.makeText(SingleCrimeDetails.this, "crime id eka"+crimeidekaloadz, Toast.LENGTH_LONG).show();

            HashMap postdata = new HashMap();
            postdata.put("crimeidz", crimeidekaloadz);

            PostResponseAsyncTask task = new PostResponseAsyncTask(SingleCrimeDetails.this, postdata);
            task.execute("http://potcantalk.com/mobileapp/loadsinglecrimedetails.php");




        }catch(Exception e){
            Toast.makeText(SingleCrimeDetails.this, "---"+e, Toast.LENGTH_LONG).show();

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                   // Toast.makeText(SingleCrimeDetails.this, "crime id eka"+crimeidekaloadz, Toast.LENGTH_LONG).show();

                    HashMap postdata = new HashMap();
                    postdata.put("crimeidekareportz", crimeidekaloadz);

                    PostResponseAsyncTask task = new PostResponseAsyncTask(SingleCrimeDetails.this, postdata);
                    task.execute("http://potcantalk.com/mobileapp/makecrimereported.php");




                }catch(Exception e){
                    Toast.makeText(SingleCrimeDetails.this, "---"+e, Toast.LENGTH_LONG).show();

                }
            }
        });



    }

    @Override
    public void processFinish(String s) {

        try {

            String getresponse =s;
            //Toast.makeText(SingleCrimeDetails.this, "---"+s, Toast.LENGTH_LONG).show();
            if(getresponse.equals("error")){

                Toast.makeText(SingleCrimeDetails.this, "Invalid Login Login Details", Toast.LENGTH_LONG).show();
            }else if(getresponse.equals("ok")){

                Toast.makeText(SingleCrimeDetails.this, "Crime Marked As Reported", Toast.LENGTH_LONG).show();


            }else {

                String dataget[] = getresponse.split(",,");
                textView1.setText("Crime Date-"+ dataget[0]);
                textView2.setText("Crime time-"+dataget[1]);
                textView3.setText("Report By-"+dataget[2]);
                textView4.setText("Contact No-"+dataget[3]);
                textView5.setText("NIC-"+dataget[4]);
                textView6.setText("Crime Description-"+dataget[5]);


            }

        }catch (Exception e){

            Toast.makeText(SingleCrimeDetails.this, "error--"+e, Toast.LENGTH_LONG).show();

        }


    }
}
