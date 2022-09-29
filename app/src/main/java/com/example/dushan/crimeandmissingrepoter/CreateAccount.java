package com.example.dushan.crimeandmissingrepoter;

import android.content.Intent;
import android.opengl.EGLExt;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;


public class CreateAccount extends AppCompatActivity implements AsyncResponse{
    static String[]  districs=new String[]{"Ampara","Anuradhapura","Badulla","Batticaloa","Colombo","Galle","Gampaha","Hambantota","Matara","Nuwara Eliya","Matale"};
    TextView tv;
    EditText fname,lname,emai,password,confirmpw,nic;
    AutoCompleteTextView atv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        StrictMode.ThreadPolicy di = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(di);

        tv = (TextView) findViewById(R.id.textViewlogoinz);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ii = new Intent(CreateAccount.this, Login.class);
                startActivity(ii);
            }
        });


        fname =(EditText) findViewById(R.id.userfname);
        lname =(EditText) findViewById(R.id.userlname);
        emai =(EditText) findViewById(R.id.useremail);
        password =(EditText) findViewById(R.id.pasword);
        confirmpw =(EditText) findViewById(R.id.password2);
        nic =(EditText) findViewById(R.id.nicekacreate);
        atv =(AutoCompleteTextView) findViewById(R.id.districtype);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,districs);

        atv.setAdapter(adapter);







        btn = (Button)findViewById(R.id.createaccount);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String firstname = fname.getText().toString();
                String lastname = lname.getText().toString();
                String email = emai.getText().toString();
                String password1 = password.getText().toString();
                String passwordconfirm = confirmpw.getText().toString();
                String niceka = nic.getText().toString();
                String distrixz = atv.getText().toString();




                if(firstname.equals("") || lastname.equals("") || email.equals("") || password1.equals("") || passwordconfirm.equals("")){

                    Toast.makeText(CreateAccount.this, "Fill All Feilds To Continue..!", Toast.LENGTH_LONG).show();

                }else{
                        try {

                            HashMap postdata = new HashMap();
                            postdata.put("firtname",firstname);
                            postdata.put("lastname",lastname);
                            postdata.put("emailz",email);
                            postdata.put("passwordz",password1);
                            postdata.put("nicz",niceka);
                            postdata.put("districz",distrixz);
                            PostResponseAsyncTask task = new PostResponseAsyncTask(CreateAccount.this,postdata);
                            task.execute("http://potcantalk.com/mobileapp/createaccount.php");

                        }catch (Exception e){
                            Toast.makeText(CreateAccount.this, "---"+e, Toast.LENGTH_LONG).show();
                        }


                }
            }
        });







    }

    @Override
    public void processFinish(String s) {
        String getresponse =s;
        //Toast.makeText(CreateAccount.this, "---"+s, Toast.LENGTH_LONG).show();

        if(getresponse.equals("ok")){

            Toast.makeText(CreateAccount.this, "User Registered Succescfully..", Toast.LENGTH_LONG).show();
        }else if(getresponse.equals("Already")){

            Toast.makeText(CreateAccount.this, "This NIC is Alredy Registered..", Toast.LENGTH_LONG).show();
        }


    }


}
