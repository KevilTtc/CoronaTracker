package com.example.mcad_proj.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mcad_proj.HideStatusBar;
import com.example.mcad_proj.R;

public class MainActivity extends AppCompatActivity {
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HideStatusBar.hideStatusBar(this);
        setContentView(R.layout.activity_main);

        Button btnNavigateToStart = findViewById(R.id.btnNavigateToStart);
        btnNavigateToStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
                finish();
            }
        });





    }
}