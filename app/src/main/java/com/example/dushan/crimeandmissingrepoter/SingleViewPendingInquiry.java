package com.example.dushan.crimeandmissingrepoter;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;

public class SingleViewPendingInquiry extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_single_view_pending_inquiry);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();

        tv1 = findViewById(R.id.doctornamez2);
        tv2 = findViewById(R.id.loaddoctortypez2);
        tv3 = findViewById(R.id.datezinz);
        tv4 = findViewById(R.id.timezinz);
        tv5 = findViewById(R.id.descriptioninquz);
        try {
            String canid = DbConnection.single_inquid;
            String sqlload = "SELECT * FROM inquirydetailz where id='"+canid+"'";
            ResultSet rs = DbConnection.search(sqlload);
            if (rs.next()){



                tv1.setText("Doctor Name  :  "+rs.getString("docnamez"));
                tv2.setText("Type  :  "+rs.getString("doctortypez"));
                tv3.setText("Date  :  "+rs.getString("datez"));
                tv4.setText("Time  :  "+rs.getString("timez"));
                tv5.setText("Description :  "+rs.getString("descriptz"));
                // Glide.with(SingleDoctorDetails.this).load(rs.getString("imgurlz")).into(imv);


            }



        }catch (Exception ee){
            System.out.println("Sql errorz"+ee);
            Toast.makeText(this, "err--"+ee, Toast.LENGTH_SHORT).show();
        }
    }
}