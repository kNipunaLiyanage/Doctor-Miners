package com.example.dushan.crimeandmissingrepoter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;


public class Login extends AppCompatActivity implements AsyncResponse{
    private EditText emailEditText;
    private EditText passEditText;
    private TextView createaccount;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = (EditText) findViewById(R.id.username);
        passEditText = (EditText) findViewById(R.id.password);
        createaccount = (TextView) findViewById(R.id.textView3);


        createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Login.this,CreateAccount.class);
                startActivity(i);

            }
        });




    }


    public void checkLogin(View arg0) {
        
        
        String uname = emailEditText.getText().toString();
        String paasswd = passEditText.getText().toString();
        if(uname.equals("") || paasswd.equals("")){
            Toast.makeText(this, "Please Fill All Feilds..", Toast.LENGTH_SHORT).show();
            
        }else {

            try{
            HashMap postdata = new HashMap();
            postdata.put("useremail", uname);
            postdata.put("uerpassword", paasswd);
            PostResponseAsyncTask task = new PostResponseAsyncTask(Login.this, postdata);
            task.execute("http://potcantalk.com/mobileapp/loginuser.php");
        }catch(Exception e){
                Toast.makeText(Login.this, "---"+e, Toast.LENGTH_LONG).show();

        }



        }

    }



//    private boolean isValidEmail(String email) {
//        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }

    @Override
    public void processFinish(String s) {


        String getresponse =s;
        //Toast.makeText(Login.this, "---"+s, Toast.LENGTH_LONG).show();
        if(getresponse.equals("error")){

            Toast.makeText(Login.this, "Invalid Login Login Details", Toast.LENGTH_LONG).show();
        }else {

            String dataget[] = getresponse.split(",,");
            ManyVariables.fname =dataget[0];
            ManyVariables.lname =dataget[1];
            ManyVariables.district =dataget[2];
            ManyVariables.nicz =dataget[3];

            Intent jj = new Intent(Login.this, UserHome.class);
            startActivity(jj);


        }



    }

    // validating password





}
