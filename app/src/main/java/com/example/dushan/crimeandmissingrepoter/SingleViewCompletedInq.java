package com.example.dushan.crimeandmissingrepoter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;

public class SingleViewCompletedInq extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_single_view_completed_inq);

        tv1 = findViewById(R.id.doctornamez23);
        tv2 = findViewById(R.id.loaddoctortypez23);
        tv3 = findViewById(R.id.datezinz3);
        tv4 = findViewById(R.id.timezinz3);
        tv5 = findViewById(R.id.descriptioninquz3);
        tv6= findViewById(R.id.commentzz3);
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
                tv5.setText("Comment :  "+rs.getString("comment"));
                // Glide.with(SingleDoctorDetails.this).load(rs.getString("imgurlz")).into(imv);


            }



        }catch (Exception ee){
            System.out.println("Sql errorz"+ee);
            Toast.makeText(this, "err--"+ee, Toast.LENGTH_SHORT).show();
        }
    }
}