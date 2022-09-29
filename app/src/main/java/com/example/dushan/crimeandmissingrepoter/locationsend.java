package com.example.dushan.crimeandmissingrepoter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class locationsend extends FragmentActivity implements OnMapReadyCallback , AsyncResponse {

    GoogleMap mapAPI;
    SupportMapFragment mapFragment;
    ArrayList<locationadapter> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationsend);
        arrayList = new ArrayList<locationadapter>();

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapAPIz);

        HashMap postdata = new HashMap();
        Toast.makeText(this, "dis eka "+ManyVariables.copdistricrz, Toast.LENGTH_SHORT).show();

        postdata.put("copdistrictzlocation", ManyVariables.copdistricrz);


        PostResponseAsyncTask task = new PostResponseAsyncTask(locationsend.this,postdata);
        task.execute("http://potcantalk.com/mobileapp/loadmaplocationz.php");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapAPI =googleMap;
        for(int i=0;i< arrayList.size() ;i++ ){

            locationadapter kk = arrayList.get(i);
            //Set Latitute and Longtude to map
            double lat = Double.parseDouble(kk.latitute);
            double lang = Double.parseDouble(kk.longtube);
            LatLng ahangama = new LatLng(lat,lang);
            mapAPI.addMarker(new MarkerOptions().position(ahangama).title(kk.des));
            mapAPI.moveCamera(CameraUpdateFactory.newLatLng(ahangama));

        }



    }

    @Override
    public void processFinish(String s) {
        try {
           // Toast.makeText(this, "--"+s, Toast.LENGTH_SHORT).show();
            JSONArray ja = new JSONArray(s);
          //  Toast.makeText(this, ja.toString(), Toast.LENGTH_SHORT).show();
            for (int f = 0;f < ja.length(); f++){

                JSONObject job = ja.getJSONObject(f);

                locationadapter i = new locationadapter();
                i.idcrimedetails =job.get("idcrimes").toString();
                i.latitute=job.get("latitude").toString();
                i.longtube=job.get("longtude").toString();
                i.des=job.get("crimedes").toString();

                arrayList.add(i);

            }

            mapFragment.getMapAsync(locationsend.this);

        }catch (Exception e){

            Toast.makeText(this, "error"+e, Toast.LENGTH_LONG).show();

        }
    }
}
