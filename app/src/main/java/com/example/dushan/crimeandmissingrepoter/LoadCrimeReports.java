package com.example.dushan.crimeandmissingrepoter;

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

public class LoadCrimeReports extends AppCompatActivity implements AsyncResponse{
    ListView listloadlistz;
    ArrayList<LoadCrimeReport> arrayList;
    ArrayAdapter adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_crime_reports);
        setTitle("");
        listloadlistz =(ListView) findViewById(R.id.timetablez1zz);
        arrayList = new ArrayList<LoadCrimeReport>();

        HashMap postdata = new HashMap();
       // Toast.makeText(this, "dis eka"+ManyVariables.copdistricrz, Toast.LENGTH_SHORT).show();
        postdata.put("copdistrictz", ManyVariables.copdistricrz);


        PostResponseAsyncTask task = new PostResponseAsyncTask(LoadCrimeReports.this,postdata);
        task.execute("http://potcantalk.com/mobileapp/loadreportcrimez.php");

    }

    @Override
    public void processFinish(String s) {
        try {
            //Toast.makeText(this, "--"+s, Toast.LENGTH_SHORT).show();
            JSONArray ja = new JSONArray(s);
            //Toast.makeText(this, ja.toString(), Toast.LENGTH_SHORT).show();
            for (int f = 0;f < ja.length(); f++){

                JSONObject job = ja.getJSONObject(f);

                LoadCrimeReport i = new LoadCrimeReport();
                i.idcrimedetails =job.get("idcrimes").toString();
                i.reportby=job.get("reportedby").toString();
                i.crimetype=job.get("crimedes").toString();

                arrayList.add(i);

            }
            reportcrimeadapter my = new reportcrimeadapter(this,arrayList);
            listloadlistz.setAdapter(my);


        }catch (Exception e){

            Toast.makeText(this, "error"+e, Toast.LENGTH_LONG).show();

        }
    }
}
