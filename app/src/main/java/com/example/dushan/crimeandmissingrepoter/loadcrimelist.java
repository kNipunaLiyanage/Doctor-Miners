package com.example.dushan.crimeandmissingrepoter;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class loadcrimelist extends AppCompatActivity implements AsyncResponse {

    ListView listloadlistz;
    ArrayList<locrimedetails> arrayList;
    ArrayAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadcrimelist);

        setTitle("");
        listloadlistz =(ListView) findViewById(R.id.timetablez1);
        arrayList = new ArrayList<locrimedetails>();

        HashMap postdata = new HashMap();
       // Toast.makeText(this, "dis eka"+ManyVariables.copdistricrz, Toast.LENGTH_SHORT).show();
        postdata.put("copdistrictz", ManyVariables.copdistricrz);




        PostResponseAsyncTask task = new PostResponseAsyncTask(loadcrimelist.this,postdata);
        task.execute("http://potcantalk.com/mobileapp/loadcrimezcopz.php");



    }

    @Override
    public void processFinish(String s) {

        try {
             // Toast.makeText(this, "--"+s, Toast.LENGTH_SHORT).show();
            JSONArray ja = new JSONArray(s);
             //Toast.makeText(this, ja.toString(), Toast.LENGTH_SHORT).show();
            for (int f = 0;f < ja.length(); f++){

                JSONObject job = ja.getJSONObject(f);

                locrimedetails i = new locrimedetails();
                i.idcrimedetails =job.get("idcrimes").toString();
                i.reportby=job.get("reportedby").toString();
                i.crimetype=job.get("crimedes").toString();

                arrayList.add(i);

            }
            loadcrimesadapter my = new loadcrimesadapter(this,arrayList);
            listloadlistz.setAdapter(my);


        }catch (Exception e){

             Toast.makeText(this, "error"+e, Toast.LENGTH_LONG).show();

        }

    }
}
