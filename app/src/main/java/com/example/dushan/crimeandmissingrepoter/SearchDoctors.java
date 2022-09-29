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

public class SearchDoctors extends AppCompatActivity {
    ListView listView;
    ArrayList<LoadAllDoctos> arrayList;
    ArrayAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search_doctors);

        listView = findViewById(R.id.rvlistz1);
        arrayList = new ArrayList<LoadAllDoctos>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();


        try {
            String tt = DbConnection.search_text;
            String useremptype = DbConnection.load_usertypez;
            String stat = "Active";

            String sqlload = "SELECT * FROM doctordetails where statusz='Active' and docregno like  '%"+tt+"%' ORDER BY id DESC";
            ResultSet rs = DbConnection.search(sqlload);
            while (rs.next()){
                LoadAllDoctos i = new LoadAllDoctos();
                i.id =rs.getString("id");
                i.doctorname=rs.getString("namez");
                i.doctortype=rs.getString("speciaalicedin");
                i.regno=rs.getString("docregno");

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
        ArrayList<LoadAllDoctos> list;

        public missingloadAdapter(Context context ,ArrayList<LoadAllDoctos> ar) {

            super(context, R.layout.doctor_design_load,ar);
            c= context;
            list = ar;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater li =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View v= li.inflate(R.layout.doctor_design_load,null);

            final LoadAllDoctos loadTimeTables = list.get(position);



            final TextView orderidhidden =(TextView) v.findViewById(R.id.hiddeniddocz);
            orderidhidden.setText(loadTimeTables.id);
            // localVari.Preg_topic_Name = loadTimeTables.topicname;


            final TextView electionnamez =(TextView) v.findViewById(R.id.doctor_name_load);
            electionnamez.setText("Doctor name : "+loadTimeTables.doctorname);

            final TextView dateheldz =(TextView) v.findViewById(R.id.doctor_type_load);
            dateheldz.setText("Type : "+loadTimeTables.doctortype);

            final TextView timeheldz =(TextView) v.findViewById(R.id.doctor_regno_load);
            timeheldz.setText("Reg No : "+loadTimeTables.regno);




            Button vieworderdetailsz =(Button) v.findViewById(R.id.viewdoctot_singeledetails);
            Button make_aninq =(Button) v.findViewById(R.id.manageenuire);


            vieworderdetailsz.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    String election_nameloadz = orderidhidden.getText().toString();
                    DbConnection.load_user_single_id = election_nameloadz;
                    Intent ii = new Intent(SearchDoctors.this, SingleDoctorDetails.class);
                    startActivity(ii);

                }
            });

            make_aninq.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String election_nameloadz = orderidhidden.getText().toString();
                    DbConnection.load_user_single_id = election_nameloadz;
                    Intent ii = new Intent(SearchDoctors.this, MakeAnInquireDoctor.class);
                    startActivity(ii);

                }
            });


            return v;
        }}
}