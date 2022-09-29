package com.example.dushan.crimeandmissingrepoter;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.sql.ResultSet;

public class SingleDoctorDetails extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_single_doctor_details);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();

        tv1 = findViewById(R.id.doctornamez);
        tv2 = findViewById(R.id.loaddoctortypez);
        tv3 = findViewById(R.id.doccitiz);
        tv4 = findViewById(R.id.doctorcontactsz);
        tv5 = findViewById(R.id.descriptiondocz);
        try {
            String canid = DbConnection.load_user_single_id;
            String sqlload = "SELECT * FROM doctordetails where id='"+canid+"'";
            ResultSet rs = DbConnection.search(sqlload);
            if (rs.next()){



                tv1.setText("Doctor Name  :"+rs.getString("namez"));
                tv2.setText("Type  :"+rs.getString("speciaalicedin"));
                tv3.setText("City  :"+rs.getString("city"));
                tv4.setText("Contact  :"+rs.getString("contactinemergancy"));
                ImageView imv = findViewById(R.id.loaddoczimage);
                tv5.setText("Description :"+rs.getString("description"));
               // Glide.with(SingleDoctorDetails.this).load(rs.getString("imgurlz")).into(imv);


            }



        }catch (Exception ee){
            System.out.println("Sql errorz"+ee);
            Toast.makeText(this, "err--"+ee, Toast.LENGTH_SHORT).show();
        }

    }
}