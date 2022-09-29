package com.example.dushan.crimeandmissingrepoter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchButtonDoc extends AppCompatActivity {
    EditText ed1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_search_button_doc);

        ed1 = findViewById(R.id.typingsearch);
        b1 = findViewById(R.id.Searchdoc);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String text = ed1.getText().toString();
                    if(text.equals("")){
                        Toast.makeText(SearchButtonDoc.this, "Enter Reg Number to search", Toast.LENGTH_SHORT).show();
                    }else{
                        DbConnection.search_text = text;
                        Intent intent = new Intent(SearchButtonDoc.this, SearchDoctors.class);
                        startActivity(intent);
                    }
                }catch (Exception ee){
                    Toast.makeText(SearchButtonDoc.this, "ee"+ee, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}