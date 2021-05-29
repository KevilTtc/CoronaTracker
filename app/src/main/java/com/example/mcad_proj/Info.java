package com.example.mcad_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mcad_proj.view.Home;

public class Info extends AppCompatActivity {
    ImageView back1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        back1 = findViewById(R.id.imageViewback);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Info.this, Home.class);
                startActivity(i);
                finish();

            }
        });

    }
}

