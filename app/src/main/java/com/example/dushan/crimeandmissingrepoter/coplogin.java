package com.example.dushan.crimeandmissingrepoter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class coplogin extends AppCompatActivity implements AsyncResponse{


    private EditText emailEditText;
    private EditText passEditText;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coplogin);

        emailEditText = (EditText) findViewById(R.id.copnic);
        passEditText = (EditText) findViewById(R.id.coppassword);

        btn = (Button)findViewById(R.id.buttoncoplog);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String serial = passEditText.getText().toString();
                String nic = emailEditText.getText().toString();

                if(serial.equals("") || nic.equals("")){
                    Toast.makeText(coplogin.this, "Fill All Feilds To Continue..!", Toast.LENGTH_LONG).show();


                }else{



                    try{
                        HashMap postdata = new HashMap();
                        postdata.put("serialz", serial);
                        postdata.put("nicz", nic);
                        PostResponseAsyncTask task = new PostResponseAsyncTask(coplogin.this, postdata);
                       task.execute("http://potcantalk.com/HealthApp/logincop.php");
                      // task.execute("http://192.168.1.100/crimenew/mobilefiles/logincop.php");
                    }catch(Exception e){
                        Toast.makeText(coplogin.this, "---"+e, Toast.LENGTH_LONG).show();

                    }



                }

            }
        });



    }

    @Override
    public void processFinish(String s) {


       try {
           String getresponse =s;
           //Toast.makeText(coplogin.this, "---"+s, Toast.LENGTH_LONG).show();
           if(getresponse.equals("error")){

               Toast.makeText(coplogin.this, "Invalid Login Login Details", Toast.LENGTH_LONG).show();
           }else {

               String dataget[] = getresponse.split(",,");
               ManyVariables.copfname =dataget[0];
               ManyVariables.coplname =dataget[1];
               ManyVariables.copdistricrz =dataget[2];


               Intent jj = new Intent(coplogin.this, HomePagePolice.class);
               startActivity(jj);



           }
       }catch (Exception e){
           Toast.makeText(coplogin.this, "error"+e, Toast.LENGTH_LONG).show();

       }


    }
}
