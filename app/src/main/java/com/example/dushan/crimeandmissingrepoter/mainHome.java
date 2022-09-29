package com.example.dushan.crimeandmissingrepoter;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class mainHome extends AppCompatActivity {
    Animation topanim,bottomanim;
    ImageView img;
    TextView tv1,tv22;
    private  static int splash_screen = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_home);


        topanim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        img = findViewById(R.id.imageView);
        tv1 = findViewById(R.id.textView);
        tv22 = findViewById(R.id.textView2);

        img.setAnimation(topanim);
        tv1.setAnimation(bottomanim);
        tv22.setAnimation(bottomanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent ii = new Intent(mainHome.this,HomeBothmain.class);
                startActivity(ii);
                finish();
            }
        },splash_screen);

    }
}
