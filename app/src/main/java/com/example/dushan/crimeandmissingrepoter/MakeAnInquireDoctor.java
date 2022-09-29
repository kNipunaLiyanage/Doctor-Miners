package com.example.dushan.crimeandmissingrepoter;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MakeAnInquireDoctor extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    EditText ed1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_make_an_inquire_doctor);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();


        tv1 = findViewById(R.id.doctornamezloading);
        tv2 = findViewById(R.id.loaddoctortypezloadingtype);
        tv3 = findViewById(R.id.doctorcontactszloadingz);

        ed1 = findViewById(R.id.descriptiondoczll);
        b1 = findViewById(R.id.makeenquiryz);
        try {
            String canid = DbConnection.load_user_single_id;
            String sqlload = "SELECT * FROM doctordetails where id='"+canid+"'";
            ResultSet rs = DbConnection.search(sqlload);
            if (rs.next()){



                tv1.setText(rs.getString("namez"));
                tv2.setText(rs.getString("speciaalicedin"));
                tv3.setText(rs.getString("contactinemergancy"));


            }

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String desc = ed1.getText().toString();
                    if(desc.equals("")){
                        Toast.makeText(MakeAnInquireDoctor.this, "Please enter your description", Toast.LENGTH_SHORT).show();

                    }else{

                        String user_emailz = DbConnection.emailzloadingz;
                        String orgnamez = "";
                        String doc_id = DbConnection.load_user_single_id;
                        String doc_name = tv1.getText().toString();
                        String doc_type = tv2.getText().toString();
                        String doc_contactz = tv3.getText().toString();

                        Date dd = new Date();
                        Date dd2 = new Date();

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss a");

                        String datez = sdf.format(dd);
                        String timez = sdf2.format(dd2);

                        String statuz = "pending";

                        try {

                            String sqlload2 = "SELECT * FROM minersregister where orgemailz='"+user_emailz+"'";
                            ResultSet rs2 = DbConnection.search(sqlload2);
                            if (rs2.next()){
                                orgnamez = rs2.getString("orgname");

                            }

                            String que = "insert into inquirydetailz\n" +
                                    "            (orgemailz,\n" +
                                    "             organizationname,\n" +
                                    "             docnamez,\n" +
                                    "             doctortypez,\n" +
                                    "             doctorcontactz,\n" +
                                    "             doctoridz,\n" +
                                    "             descriptz,\n" +
                                    "             datez,\n" +
                                    "             timez,\n" +
                                    "             statusz)\n" +
                                    "values ('"+user_emailz+"',\n" +
                                    "        '"+orgnamez+"',\n" +
                                    "        '"+doc_name+"',\n" +
                                    "        '"+doc_type+"',\n" +
                                    "        '"+doc_contactz+"',\n" +
                                    "        '"+doc_id+"',\n" +
                                    "        '"+desc+"',\n" +
                                    "        '"+datez+"',\n" +
                                    "        '"+timez+"',\n" +
                                    "        '"+statuz+"');";

                            DbConnection.iud(que);
                            Toast.makeText(MakeAnInquireDoctor.this, "Your inquiry has being sent..", Toast.LENGTH_LONG).show();
                            Intent intent =new Intent(MakeAnInquireDoctor.this,MinerHomePage.class);
                            startActivity(intent);

                        }catch (Exception ez){
                            Toast.makeText(MakeAnInquireDoctor.this, "Sql errorz"+ez, Toast.LENGTH_SHORT).show();

                        }

                    }
                }
            });



        }catch (Exception ee){
            System.out.println("Sql errorz"+ee);
            Toast.makeText(this, "Sql errorz"+ee, Toast.LENGTH_SHORT).show();
        }







    }
}