package com.example.dushan.crimeandmissingrepoter;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;

public class MinersRegister extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8;
    Button b1;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_miners_register);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        b1= findViewById(R.id.btnregisterminer);

        ed1 = findViewById(R.id.minername);
        ed2 = findViewById(R.id.minernic);
        ed3 = findViewById(R.id.minregno);
        ed4 = findViewById(R.id.mincontact);
        ed5 = findViewById(R.id.descmin);
        ed6 = findViewById(R.id.emailz);
        ed7 = findViewById(R.id.pw1min);
        ed8 = findViewById(R.id.pw2min);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String organizationname = ed1.getText().toString();
                    String organizationaddress = ed2.getText().toString();
                    String organizatioregno = ed3.getText().toString();
                    String organizationcontact = ed4.getText().toString();
                    String organizationdesc = ed5.getText().toString();
                    String organizatioemailz = ed6.getText().toString();
                    String organizationpw1 = ed7.getText().toString();
                    String organizationpw2 = ed8.getText().toString();

                    if(organizationname.equals("") || organizationaddress.equals("") || organizatioregno.equals("") || organizationcontact.equals("")
                            || organizationdesc.equals("")|| organizatioemailz.equals("") || organizationpw1.equals("")|| organizationpw2.equals("")){

                        Toast.makeText(MinersRegister.this, "Please Fill all feilds..!", Toast.LENGTH_LONG).show();

                    }else if(!(organizationpw1.equals(organizationpw2))){
                        Toast.makeText(MinersRegister.this, "Passwords Are not Matched..!", Toast.LENGTH_LONG).show();


                    }else{
                        String stat = "Pending";
                        String ss = "select * from minersregister where orgemailz='"+organizatioemailz+"'";
                        ResultSet rs = DbConnection.search(ss);
                        if(rs.next()){

                            Toast.makeText(MinersRegister.this, "This Email address is already registered...!", Toast.LENGTH_SHORT).show();
                        }else{

                            String que = "insert into minersregister\n" +
                                    "            (orgname,\n" +
                                    "             orgaddress,\n" +
                                    "             orgregisternumber,\n" +
                                    "             orgcontact,\n" +
                                    "             orgdesc,\n" +
                                    "             orgemailz,\n" +
                                    "             pwz,\n" +
                                    "             statusz)\n" +
                                    "values ('"+organizationname+"',\n" +
                                    "        '"+organizationaddress+"',\n" +
                                    "        '"+organizatioregno+"',\n" +
                                    "        '"+organizationcontact+"',\n" +
                                    "        '"+organizationdesc+"',\n" +
                                    "        '"+organizatioemailz+"',\n" +
                                    "        '"+organizationpw1+"',\n" +
                                    "        '"+stat+"');";
                            DbConnection.iud(que);
                            Toast.makeText(MinersRegister.this, "Your account has been sent to approval..", Toast.LENGTH_LONG).show();
                            Intent intent =new Intent(MinersRegister.this,PreLoadMiner.class);
                            startActivity(intent);

                        }

                    }




                }catch (Exception ez){
                    System.out.println("Sql errorz"+ez);
                    Toast.makeText(MinersRegister.this, "er"+ez, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}