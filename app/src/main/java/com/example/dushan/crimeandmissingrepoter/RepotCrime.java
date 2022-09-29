package com.example.dushan.crimeandmissingrepoter;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class RepotCrime extends AppCompatActivity implements AsyncResponse{

    private static  final int REQUEST_LOCATION=1;

    LocationManager locationManager;
    String latitude,longitude;


    EditText teleno,crimedescription;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repot_crime);

                ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        teleno = (EditText) findViewById(R.id.telephoneno);
        crimedescription = (EditText) findViewById(R.id.crimedes);

        btn = (Button) findViewById(R.id.reportcrimezzz);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String tele = teleno.getText().toString();
                String des = crimedescription.getText().toString();

                Date dd = new Date();
                Date dd2 = new Date();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM");
                SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss a");

                String datez = sdf.format(dd);
                String timez = sdf2.format(dd2);


                locationManager=(LocationManager) getSystemService(RepotCrime.this.LOCATION_SERVICE);

                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
                {
                    //Write Function To enable gps

                    OnGPS();
                }
                else
                {
                    //GPS is already On then

                    getLocation();
                }
                try {
                    String userfullname = ManyVariables.fname+" "+ManyVariables.lname;

                    HashMap postdata = new HashMap();
                    postdata.put("nameinfull",userfullname);
                    postdata.put("datez",datez);
                    postdata.put("timez",timez);
                    postdata.put("contactz",tele);
                    postdata.put("district",ManyVariables.district);
                    postdata.put("Nic",ManyVariables.nicz);
                    postdata.put("lateka",latitude);
                    postdata.put("longeka",longitude);
                    postdata.put("crimede",des);

                    PostResponseAsyncTask task = new PostResponseAsyncTask(RepotCrime.this,postdata);
                    task.execute("http://potcantalk.com/mobileapp/reportcrimeuser.php");

                }catch (Exception e){
                    Toast.makeText(RepotCrime.this, "---"+e, Toast.LENGTH_LONG).show();
                }



            }
        });


    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(RepotCrime.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(RepotCrime.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }

        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);


            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);


            }
            else if (LocationPassive !=null)
            {
                double lat=LocationPassive.getLatitude();
                double longi=LocationPassive.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);


            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }

            //Thats All Run Your App
        }



    }
    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));




            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    @Override
    public void processFinish(String s) {
        String getresponse =s;

        if(getresponse.equals("ok")){

            Toast.makeText(RepotCrime.this, "Crime Reported..", Toast.LENGTH_LONG).show();
        }else{

            Toast.makeText(RepotCrime.this, "Something Went Wrong..", Toast.LENGTH_LONG).show();
        }
    }
}
