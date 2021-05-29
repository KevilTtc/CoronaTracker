package com.example.mcad_proj.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mcad_proj.R;
import com.example.mcad_proj.Statistics;
import com.example.mcad_proj.Statisticsworld;
import com.example.mcad_proj.symptoms;

public class Home extends AppCompatActivity {

    ImageView vietnamIf, world;
    Spinner spinner;
    Button stat;
    Button callbutton, smsbutton, btnkhaibaoyte, btnReflect;
    ImageView button_notify, symp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        vietnamIf = findViewById(R.id.indiaflag);
        world = findViewById(R.id.worldflag);
        stat = findViewById(R.id.stat);
        callbutton = findViewById(R.id.button);
        smsbutton = findViewById(R.id.button2);
        btnReflect = findViewById(R.id.btnReflect);
        symp = findViewById(R.id.imageView6);
        btnkhaibaoyte = findViewById(R.id.khaibaoyte);

        vietnamIf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Statistics.class);
                startActivity(i);

            }

        });

        btnReflect.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, ActivityReflect.class);
            startActivity(intent);
        });

        world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Statisticsworld.class);
                startActivity(i);

            }

        });


        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Statistics.class);
                startActivity(i);

            }

        });

        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:19009095"));
                startActivity(intent);
            }
        });

        smsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //So you can get the edittext value
                String mobileNumber = "19009095";
                String message = "Hi! I need help regarding corona";
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + mobileNumber));
                smsIntent.putExtra("sms_body", message);
                startActivity(smsIntent);


            }
        });

        symp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, symptoms.class);
                startActivity(intent);
            }
        });

        btnkhaibaoyte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://tokhaiyte.vn/"));
                startActivity(intent);
            }
        });

    }

    private boolean appInstalledOrNot(String url) {
        PackageManager packageManager = getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}
