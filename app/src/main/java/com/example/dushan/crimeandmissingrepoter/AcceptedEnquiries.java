package com.example.dushan.crimeandmissingrepoter;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AcceptedEnquiries extends AppCompatActivity {
    ListView listView;
    ArrayList<LoadInquiries> arrayList;
    ArrayAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_accepted_enquiries);

        listView = findViewById(R.id.submittedqntt);
        arrayList = new ArrayList<LoadInquiries>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();

        try {

            String useremptype = DbConnection.emailzloadingz;
            String stat = "Active";

            String sqlload = "SELECT * FROM inquirydetailz where orgemailz='"+useremptype+"' and statusz='Submitted' ORDER BY id DESC";
            ResultSet rs = DbConnection.search(sqlload);
            while (rs.next()){
                LoadInquiries i = new LoadInquiries();
                i.id =rs.getString("id");
                i.doctorname=rs.getString("docnamez");
                i.datez=rs.getString("datez");
                i.timez=rs.getString("timez");

                arrayList.add(i);
            }

           missingloadAdapter my = new missingloadAdapter(this,arrayList);
            listView.setAdapter(my);

        }catch (Exception ee){
            System.out.println("Sql errorz"+ee);
            Toast.makeText(this, "err--"+ee, Toast.LENGTH_SHORT).show();
        }
    }
    class missingloadAdapter extends ArrayAdapter {
        Context c;
        ArrayList<LoadInquiries> list;

        public missingloadAdapter(Context context ,ArrayList<LoadInquiries> ar) {

            super(context, R.layout.doctor_design_load,ar);
            c= context;
            list = ar;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater li =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View v= li.inflate(R.layout.inquiry_design_load,null);

            final LoadInquiries loadTimeTables = list.get(position);



            final TextView orderidhidden =(TextView) v.findViewById(R.id.hiddeniddeninq);
            orderidhidden.setText(loadTimeTables.id);
            // localVari.Preg_topic_Name = loadTimeTables.topicname;


            final TextView electionnamez =(TextView) v.findViewById(R.id.doctor_name_load_inquiry);
            electionnamez.setText("Doctor name : "+loadTimeTables.doctorname);

            final TextView dateheldz =(TextView) v.findViewById(R.id.date_inqui);
            dateheldz.setText("Date : "+loadTimeTables.datez);

            final TextView timeheldz =(TextView) v.findViewById(R.id.timez_inqui);
            timeheldz.setText("Time : "+loadTimeTables.timez);




            Button vieworderdetailsz =(Button) v.findViewById(R.id.viewconsult_singeledetails);






            vieworderdetailsz.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    String election_nameloadz = orderidhidden.getText().toString();
                    DbConnection.single_inquid = election_nameloadz;
                    Intent ii = new Intent(AcceptedEnquiries.this, SingleViewCompletedInq.class);
                    startActivity(ii);

                }
            });




            return v;
        }


    }
}