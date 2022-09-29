package com.example.dushan.crimeandmissingrepoter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class loadcrimesadapter extends ArrayAdapter {
    Context c;
    ArrayList<locrimedetails> list;

    public loadcrimesadapter(Context context ,ArrayList<locrimedetails> ar) {

        super(context, R.layout.loadcrimelistadapter,ar);
        c= context;
        list = ar;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View v= li.inflate(R.layout.loadcrimelistadapter,null);

        final locrimedetails loadTimeTables = list.get(position);


//        TextView loadgrade =(TextView) v.findViewById(R.id.loadgradez);
//        loadgrade.setText(loadTimeTables.gradez);

        TextView reportuserz =(TextView) v.findViewById(R.id.reptzuserz);
        reportuserz.setText(loadTimeTables.reportby);

        TextView crimetypez =(TextView) v.findViewById(R.id.crimetypeeka);
        crimetypez.setText(loadTimeTables.crimetype);

        final TextView crimeideka =(TextView) v.findViewById(R.id.hiddenidz);
        crimeideka.setText(loadTimeTables.idcrimedetails);



        Button viewcrime =(Button) v.findViewById(R.id.crimeviewbtn);
        viewcrime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i= new Intent(c,SingleCrimeDetails.class);
                i.putExtra("crimeidz", crimeideka.getText().toString());
                c.startActivity(i);


            }
        });













        return v;
    }

}
