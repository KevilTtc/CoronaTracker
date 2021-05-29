package com.example.mcad_proj;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.mcad_proj.view.Home;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Statisticsworld extends AppCompatActivity {
    TextView tv_confirmed, tv_active, tv_death, tv_recovered, tv_date, tv_time;
    ImageView info, back;
    Button country,countrywise;
    Integer c;
    String str_confirmed, str_active, str_recovered, str_death, str_last_update_time;
    Float con, act, rec, dea;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statisticsworld);
        Init();
        Fetchdata();
        countrywise=findViewById(R.id.countrywisebutton);
        countrywise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Statisticsworld.this, CountryWiseDataActivity.class);
                startActivity(i);
                finish();

            }
        });
        country=findViewById(R.id.button3);
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Statisticsworld.this, Statistics.class);
                startActivity(i);
                finish();

            }
        });

        info = findViewById(R.id.imageView2);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Statisticsworld.this, Info.class);
                startActivity(i);
                finish();

            }
        });

        back = findViewById(R.id.imageView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Statisticsworld.this, Home.class);
                startActivity(i);
                finish();

            }
        });
    }


    private void Init() {
        tv_confirmed = findViewById(R.id.confirmed);
        tv_active = findViewById(R.id.active);
        tv_recovered = findViewById(R.id.recovered);
        tv_death = findViewById(R.id.death);

        tv_date = findViewById(R.id.textView9);
        tv_time = findViewById(R.id.textView10);

        pieChart = findViewById(R.id.activity_main_piechart);
    }

    private void Fetchdata() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String apiUrl = "https://corona.lmao.ninja/v2/all";
        pieChart.clearChart();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //As the data of the json are in a nested array, so we need to define the array from which we want to fetch the data.
                        JSONArray all_state_jsonArray = null;
                        JSONArray testData_jsonArray = null;

                        try {                            //Fetching data for India and storing it in String
                            str_confirmed = response.getString("cases");   //Confirmed cases in India
                            str_active = response.getString("active");    //Active cases in India
                            str_recovered = response.getString("recovered");  //Total recovered cased in India
                            str_death = response.getString("deaths");     //Total deaths in India
                            //str_last_update_time = data_india.getString("lastupdatedtime"); //Last update date and time

                            Handler delayToshowProgess = new Handler();

                            delayToshowProgess.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Setting text in the textview
                                    tv_confirmed.setText(NumberFormat.getInstance().format(Integer.parseInt(str_confirmed)));
                                    c = Integer.parseInt(str_confirmed);
                                    tv_active.setText(NumberFormat.getInstance().format(Integer.parseInt(str_active)));

                                    tv_recovered.setText(NumberFormat.getInstance().format(Integer.parseInt(str_recovered)));

                                    tv_death.setText(NumberFormat.getInstance().format(Integer.parseInt(str_death)));
                                    //tv_date.setText(FormatDate(str_last_update_time, 1));
                                    //tv_time.setText(FormatDate(str_last_update_time, 2));

                                    pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(str_active),
                                            Color.parseColor("#8051D3")));
                                    pieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(str_recovered)
                                            , Color.parseColor("#FFC107")));
                                    pieChart.addPieSlice(new PieModel("Deceased", Integer.parseInt(str_death)
                                            , Color.parseColor("#F6404F")));

                                    pieChart.startAnimation();

                                }
                            }, 1000);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    private String FormatDate(String date, int testCase) {
        Date mDate = null;
        String dateFormat;
        try {
            mDate = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US).parse(date);
            if (testCase == 0) {
                dateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a").format(mDate);
                return dateFormat;
            } else if (testCase == 1) {
                dateFormat = new SimpleDateFormat("dd MMM yyyy").format(mDate);
                return dateFormat;
            } else if (testCase == 2) {
                dateFormat = new SimpleDateFormat("hh:mm a").format(mDate);
                return dateFormat;
            } else {
                Log.d("error", "Wrong input! Choose from 0 to 2");
                return "Error";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }
}