package com.example.dushan.crimeandmissingrepoter;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;
import net.gotev.uploadservice.UploadService;

public class MissingpersonReport extends AppCompatActivity{


    EditText editText1,editText2,editText3,editText4;
    Button btn,btn2;
    ImageView img;
    private  static  final int STORAGE_PERMISSION_CODE = 4655;
    private  static  final String upload_uri = "http://potcantalk.com/mobileapp/uploadimagez.php";
    private int PICK_IMAGE_RESULT = 1;
    private Uri filepath;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missingperson_report);

        UploadService.NAMESPACE = BuildConfig.APPLICATION_ID;

        storagePermission();
        editText1 =(EditText) findViewById(R.id.namemissing);
        editText2 =(EditText) findViewById(R.id.address);
        editText3 =(EditText) findViewById(R.id.loctionz);
        editText4 =(EditText) findViewById(R.id.decription);

        btn =(Button) findViewById(R.id.reportmissing);
        btn2 =(Button) findViewById(R.id.imageselect);

        img = (ImageView) findViewById(R.id.selectedimage);






        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            String name_missing = editText1.getText().toString();
            String missingaddress = editText2.getText().toString();
            String missinglocation = editText3.getText().toString();
            String missingdescription = editText4.getText().toString();

            UploadData(name_missing,missingaddress,missinglocation,missingdescription);


            }
        });









    }


    private  void storagePermission(){

        if(ContextCompat.checkSelfPermission(MissingpersonReport.this, Manifest.permission.READ_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED ) {

            return;
        }
        ActivityCompat.requestPermissions(MissingpersonReport.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();

            }else{

                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();

            }

        }
    }

    public void selectImage(View view){


        showFileChooser();


    }

    private void showFileChooser(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image"),PICK_IMAGE_RESULT);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_RESULT && data != null && data.getData() != null){

            filepath = data.getData();
            try{

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filepath);
                img.setImageBitmap(bitmap);



            }catch (Exception e){

                Toast.makeText(this, "error----"+e, Toast.LENGTH_SHORT).show();
            }


        }
    }

    private String getPath(Uri uri){


        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,MediaStore.Images.Media._ID+"=?",new String[]{document_id},null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;


    }

    public void UploadData(String name,String address,String location,String description){
        try {


            Date dd = new Date();
            Date dd2 = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/MM");
            SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss a");

            String datez = sdf.format(dd);
            String timez = sdf2.format(dd2);

            String path = getPath(filepath);

            String upload_Id = UUID.randomUUID().toString();

            MultipartUploadRequest uploadRequest=  new MultipartUploadRequest(this,upload_Id,upload_uri)
                    .addFileToUpload(path,"image")
                    .addParameter("missingname",name)
                    .addParameter("name",ManyVariables.nicz)
                    .addParameter("reportusername", ManyVariables.fname)
                    .addParameter("missingaddress",address)
                    .addParameter("missinglocation",location)
                    .addParameter("datez",datez)
                    .addParameter("timez",timez)
                    .addParameter("missingdescription",description)
                    .setMaxRetries(3);

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
                NotificationChannel channel = new NotificationChannel("Upload", "Upload service", NotificationManager.IMPORTANCE_DEFAULT);
                notificationManager.createNotificationChannel(channel);

                UploadNotificationConfig notificationConfig = new UploadNotificationConfig();
                notificationConfig.setNotificationChannelId("Upload");

                uploadRequest.setNotificationConfig(notificationConfig);
            } else {
                // If android < Oreo, just set a simple notification (or remove if you don't wanna any notification
                // Notification is mandatory for Android > 8
                uploadRequest.setNotificationConfig(new UploadNotificationConfig());
            }

            uploadRequest.startUpload();

        }catch (Exception e){

            Toast.makeText(this, "Upload Error--"+e, Toast.LENGTH_LONG).show();
        }


    }




}
