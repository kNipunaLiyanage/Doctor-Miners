package com.example.dushan.crimeandmissingrepoter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class SingleReportDetais extends AppCompatActivity implements AsyncResponse {


    TextView textView1,textView2,textView3,textView4,textView5,textView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_report_detais);


        textView1 = findViewById(R.id.textviewonezz);
        textView2 = findViewById(R.id.textviewtwozz);
        textView3 = findViewById(R.id.textviewthreezz);
        textView4 = findViewById(R.id.textviefourzz);
        textView5 = findViewById(R.id.textviefivezz);
        textView6 = findViewById(R.id.textviewsixzz);

        String crimeidekaloadz =getIntent().getStringExtra("crimeidreport");



        try{
            //Toast.makeText(SingleReportDetais.this, "crime id eka"+crimeidekaloadz, Toast.LENGTH_LONG).show();

            HashMap postdata = new HashMap();
            postdata.put("crimeidz", crimeidekaloadz);

            PostResponseAsyncTask task = new PostResponseAsyncTask(SingleReportDetais.this, postdata);
            task.execute("http://potcantalk.com/mobileapp/loadsinglecrimereportdetails.php");




        }catch(Exception e){
            Toast.makeText(SingleReportDetais.this, "---"+e, Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void processFinish(String s) {

        try {

            String getresponse =s;
           // Toast.makeText(SingleReportDetais.this, "---"+s, Toast.LENGTH_LONG).show();
            if(getresponse.equals("error")){

                Toast.makeText(SingleReportDetais.this, "Invalid Login Login Details", Toast.LENGTH_LONG).show();
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

            Toast.makeText(SingleReportDetais.this, "error--"+e, Toast.LENGTH_LONG).show();

        }

    }
}
