package com.example.dushan.crimeandmissingrepoter;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;
import net.gotev.uploadservice.UploadService;

public class RegisterDoctor extends AppCompatActivity {
    TextView tv1;
    Button b1,b2;
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9;
    AutoCompleteTextView acTV1;
    ImageView img;
    private  static  final int STORAGE_PERMISSION_CODE = 4655;
    private  static  final String upload_uri = "http://192.168.1.10/ADMINDOCOR/phpfiles/Loadupload.php";
    private int PICK_IMAGE_RESULT = 1;
    private Uri filepath;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_doctor);
        UploadService.NAMESPACE = BuildConfig.APPLICATION_ID;
        storagePermission();
        List<String> data = new ArrayList<>();
        data.add("None");
        data.add("Pediatrician");
        data.add("Family physician");
        data.add("Internist");
        data.add("Emergency physician");
        data.add("Neurologist");
        acTV1 = findViewById(R.id.acT12);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        final String[] selection = {""};
        acTV1.setAdapter(arrayAdapter);
        acTV1.setCursorVisible(false);
        acTV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                acTV1.showDropDown();
                selection[0] = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selection[0],
                        Toast.LENGTH_SHORT);
            }
        });

        acTV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                acTV1.showDropDown();
            }
        });

        b1 = findViewById(R.id.btnregisterz);
        b2 = findViewById(R.id.btnselectimage);
        img = findViewById(R.id.selectedimage);

        ed1 = findViewById(R.id.docname);
        ed2 = findViewById(R.id.docnic);
        ed3 = findViewById(R.id.docregno);
        ed4 = findViewById(R.id.acT12);
        ed5 = findViewById(R.id.doccontact);
        ed6 = findViewById(R.id.dochos);
        ed7 = findViewById(R.id.pw1);
        ed8 = findViewById(R.id.pw2);
        ed9 = findViewById(R.id.descdoc);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String name_doc = ed1.getText().toString();
                String nic_doc = ed2.getText().toString();
                String regno_doc = ed3.getText().toString();
                String type_doc = ed4.getText().toString();
                String contact_doc = ed5.getText().toString();
                String hos_doc = ed6.getText().toString();
                String pw1_doc = ed7.getText().toString();
                String pw2_doc = ed8.getText().toString();
                String desc = ed9.getText().toString();

                if(name_doc.equals("") || nic_doc.equals("") || regno_doc.equals("") || type_doc.equals("") || contact_doc.equals("")|| hos_doc.equals("")|| pw1_doc.equals("")|| pw2_doc.equals("")|| desc.equals("")){

                    Toast.makeText(RegisterDoctor.this, "Please Fill all feilds..!", Toast.LENGTH_LONG).show();

                }else {

                    if (!pw1_doc.equals(pw2_doc)) {

                        Toast.makeText(RegisterDoctor.this, "Passwrods are not Matched..!", Toast.LENGTH_LONG).show();
                    } else {

                        UploadData(name_doc,nic_doc,regno_doc,type_doc,contact_doc,hos_doc,pw1_doc,desc);
                    }
                }
            }
        });




    }



    private  void storagePermission(){

        if(ContextCompat.checkSelfPermission(RegisterDoctor.this, Manifest.permission.READ_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED ) {

            return;
        }
        ActivityCompat.requestPermissions(RegisterDoctor.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);


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


            try{
                filepath = data.getData();
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

    private void UploadData(String name_doc, String nic_doc, String regno_doc, String type_doc, String contact_doc, String hos_doc, String pw1_doc, String desc) {
            try {

                String path = getPath(filepath);

                System.out.println("patheeee"+path);

                String upload_Id = UUID.randomUUID().toString();
                MultipartUploadRequest uploadRequest=  new MultipartUploadRequest(this,upload_Id,upload_uri)
                        .addFileToUpload(path,"image")
                        .addParameter("name_doc",name_doc)
                        .addParameter("nic_doc",nic_doc)
                        .addParameter("regno_doc",regno_doc)
                        .addParameter("type_doc", type_doc)
                        .addParameter("contact_doc",contact_doc)
                        .addParameter("hos_doc",hos_doc)
                        .addParameter("pw1_doc",pw1_doc)
                        .addParameter("des",desc)
                        .setMaxRetries(3);
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);
                    NotificationChannel channel = new NotificationChannel("Upload", "Upload service", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                   // UploadService.NAMESPACE = BuildConfig.APPLICATION_ID;
                    UploadNotificationConfig notificationConfig = new UploadNotificationConfig();

                    notificationConfig.setNotificationChannelId("Upload");

                    uploadRequest.setNotificationConfig(notificationConfig);
                } else {
                    // If android < Oreo, just set a simple notification (or remove if you don't wanna any notification
                    // Notification is mandatory for Android > 8
                    uploadRequest.setNotificationConfig(new UploadNotificationConfig());
                }

                uploadRequest.startUpload();
                Toast.makeText(this, "Your Registration is send to aproval", Toast.LENGTH_LONG).show();

            }catch (Exception ee){
                Toast.makeText(this, "errr"+ee, Toast.LENGTH_LONG).show();
            }
    }
}