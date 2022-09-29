package com.example.dushan.crimeandmissingrepoter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class missingpersons extends AppCompatActivity implements AsyncResponse {


    ListView listView;
    ArrayList<missingPerson> arrayList;
    ArrayAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missingpersons);

        listView = findViewById(R.id.listviewmissing);
        arrayList = new ArrayList<missingPerson>();
//        Myadapter myadapter = new Myadapter(this,mtitle,mdescrition,images);
//        listView.setAdapter(myadapter);
        HashMap postdata = new HashMap();
        //Toast.makeText(this, "dis eka"+ManyVariables.copdistricrz, Toast.LENGTH_SHORT).show();
        postdata.put("fff", "ok");


        PostResponseAsyncTask task = new PostResponseAsyncTask(missingpersons.this,postdata);
        task.execute("http://potcantalk.com/mobileapp/loadmissingz.php");


    }

    @Override
    public void processFinish(String s) {



        try {
            //Toast.makeText(this, "--"+s, Toast.LENGTH_SHORT).show();
            JSONArray ja = new JSONArray(s);
            //Toast.makeText(this, ja.toString(), Toast.LENGTH_SHORT).show();
            for (int f = 0;f < ja.length(); f++){

                JSONObject job = ja.getJSONObject(f);

                missingPerson i = new missingPerson();
                i.idcrimedetails =job.get("idmising").toString();
                i.missingname=job.get("namemissing").toString();
                i.lastlocation=job.get("lastlocation").toString();

                arrayList.add(i);

            }
            missingloadAdapter my = new missingloadAdapter(this,arrayList);
            listView.setAdapter(my);


        }catch (Exception e){

            Toast.makeText(this, "error"+e, Toast.LENGTH_LONG).show();

        }

    }

    class missingloadAdapter extends ArrayAdapter{

        Context c;
        ArrayList<missingPerson> list;


        public missingloadAdapter(Context context ,ArrayList<missingPerson> ar) {

            super(context, R.layout.missingadapterz,ar);
            c= context;
            list = ar;
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater li =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View v= li.inflate(R.layout.missingadapterz,null);

            final missingPerson loadTimeTables = list.get(position);

            TextView reportuserz =(TextView) v.findViewById(R.id.reptzuserzremissing);
            reportuserz.setText(loadTimeTables.missingname);

            TextView crimetypez =(TextView) v.findViewById(R.id.crimetypeekaremissing);
            crimetypez.setText(loadTimeTables.lastlocation);

            final TextView crimeideka =(TextView) v.findViewById(R.id.hiddenidzremissing);
            crimeideka.setText(loadTimeTables.idcrimedetails);


            Button viewcrime =(Button) v.findViewById(R.id.crimeviewbtnremissing);


            viewcrime.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Intent i= new Intent(c,singlemissingpersondetail.class);
                    i.putExtra("crimeidmissing", crimeideka.getText().toString());
                    c.startActivity(i);


                }
            });


            return v;
        }
    }
}
