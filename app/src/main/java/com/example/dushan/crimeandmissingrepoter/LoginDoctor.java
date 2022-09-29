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

public class LoginDoctor extends AppCompatActivity {
    Button bz;
    TextView tv1;
    EditText username,passw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_doctor2);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);

        username = findViewById(R.id.usernamelogz2);
        passw = findViewById(R.id.passwordlogz2);
        tv1 = findViewById(R.id.gotoregister2);
        bz = findViewById(R.id.logz2);

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(LoginDoctor.this,RegisterDoctor.class);
                startActivity(ii);

            }
        });

        bz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unman = username.getText().toString();
                String pass1 = passw.getText().toString();
                try{
                    if(unman.equals("") || pass1.equals("")){
                        Toast.makeText(LoginDoctor.this, "Please fill all the feilds..", Toast.LENGTH_SHORT).show();
                    }else {
                        String searchquer = "select * from doctordetails where nic='" + unman + "' and passwords='" + pass1 + "'";
                        ResultSet rs = DbConnection.search(searchquer);
                        if (rs.next()) {
                            String userstat = rs.getString("statusz");
                            DbConnection.emailzloadingz = unman;
                            DbConnection.usernameloadingz = rs.getString("namez");
                            DbConnection.load_usertypez = rs.getString("statusz");
                            DbConnection.doctorid_load = rs.getString("id");
                            System.out.println("Sqlzz"+userstat);
                            if (userstat.equals("pending")) {
                                Toast.makeText(LoginDoctor.this, "Account still not accepted by admin", Toast.LENGTH_SHORT).show();
                            } else {
                                DbConnection.load_user_passwordz = pass1;
                                Toast.makeText(LoginDoctor.this, "Login sucess", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginDoctor.this, DoctorHomepage.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(LoginDoctor.this, "Invalid login details", Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch(Exception e){
                    System.out.println("Sql errorz"+e);
                    Toast.makeText(LoginDoctor.this, "---"+e, Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}