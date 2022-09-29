package com.example.dushan.crimeandmissingrepoter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class singlemissingpersondetail extends AppCompatActivity implements AsyncResponse {
    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlemissingpersondetail);

        textView1 = findViewById(R.id.textviewonemissng);
        textView2 = findViewById(R.id.textviewtwomissng);
        textView3 = findViewById(R.id.textviewthreemissng);
        textView4 = findViewById(R.id.textviefourmissng);
        textView5 = findViewById(R.id.textviefivemissng);
        textView6 = findViewById(R.id.textviewsixmissng);
        textView7 = findViewById(R.id.textviesevenmissng);
        textView8 = findViewById(R.id.textvieeightmissng);
        textView9 = findViewById(R.id.textvieninemissng);


        final String crimeidekaloadz =getIntent().getStringExtra("crimeidmissing");
        try{
            Toast.makeText(singlemissingpersondetail.this, "crime id eka"+crimeidekaloadz, Toast.LENGTH_LONG).show();

            HashMap postdata = new HashMap();
            postdata.put("crimeidmissing", crimeidekaloadz);

            PostResponseAsyncTask task = new PostResponseAsyncTask(singlemissingpersondetail.this, postdata);
            task.execute("http://potcantalk.com/mobileapp/singleloadmissingdetails.php");




        }catch(Exception e){
            Toast.makeText(singlemissingpersondetail.this, "---"+e, Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void processFinish(String s) {

        try {

            JSONArray ja = new JSONArray(s);
            Toast.makeText(this, ja.toString(), Toast.LENGTH_SHORT).show();

            for (int f = 0;f < ja.length(); f++){

                JSONObject job = ja.getJSONObject(f);


                textView1.setText("Missing Name -"+ job.get("namemissing").toString());
                textView2.setText("Address -"+job.get("missingaddress").toString());
                textView3.setText("Last Seen Location -"+job.get("lastlocation").toString());
                textView4.setText("Crime Discription -"+job.get("description").toString());
                textView5.setText("Crime Date -"+job.get("datez").toString());
                textView6.setText("Crime Time -"+job.get("time").toString());
                textView7.setText("Report User Name "+job.get("reportedusername").toString());
                textView8.setText("Report NIC -"+job.get("reportedusernic").toString());
                textView9.setText("USer Contact -"+job.get("reportedcontact").toString());



            }
        }catch (Exception e){

            Toast.makeText(this, "error"+e, Toast.LENGTH_LONG).show();

        }


    }
}
