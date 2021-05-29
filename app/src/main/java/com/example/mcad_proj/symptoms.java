package com.example.mcad_proj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.mcad_proj.view.Home;

public class symptoms extends AppCompatActivity {
    ImageView backb;
    CardView cardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        backb=findViewById(R.id.imageView10);

        backb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(symptoms.this, Home.class);
                startActivity(i);

            }

        });
        /*cardview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.action_down) {
                    textview.settextcolor(color.red);
                    return true;
                } else
                {
                    textview.settextcolor(color.blue);
                }
                return false;
            }
        });*/

    }
}
