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

public class MinerLogin extends AppCompatActivity {
    Button bz;
    TextView tv1;
    EditText username,passw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_miner_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);

        username = findViewById(R.id.usernamelogz);
        passw = findViewById(R.id.passwordlogz);
        tv1 = findViewById(R.id.gotoregister);
        bz = findViewById(R.id.logz);


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(MinerLogin.this,MinersRegister.class);
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
                        Toast.makeText(MinerLogin.this, "Please fill all the feilds..", Toast.LENGTH_SHORT).show();
                    }else {
                        String searchquer = "select * from minersregister where orgemailz='" + unman + "' and pwz='" + pass1 + "'";
                        ResultSet rs = DbConnection.search(searchquer);
                        if (rs.next()) {
                            String userstat = rs.getString("statusz");
                            DbConnection.emailzloadingz = unman;
                            DbConnection.usernameloadingz = rs.getString("orgname");
                            DbConnection.load_usertypez = rs.getString("statusz");
                            if (userstat.equals("Pending")) {
                                Toast.makeText(MinerLogin.this, "Account still not accepted by admin", Toast.LENGTH_SHORT).show();
                            } else {
                                DbConnection.load_user_passwordz = pass1;
                                Toast.makeText(MinerLogin.this, "Login sucess", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MinerLogin.this, MinerHomePage.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(MinerLogin.this, "Invalid login details", Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch(Exception e){
                    System.out.println("Sql errorz"+e);
                    Toast.makeText(MinerLogin.this, "---"+e, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}