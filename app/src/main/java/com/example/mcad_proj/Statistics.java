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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mcad_proj.view.Home;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Statistics extends AppCompatActivity {
    TextView tv_confirmed, tv_active, tv_death, tv_recovered,tv_date, tv_time;
    ImageView info, back;
    Button global;
    Integer c;
    String str_confirmed,str_active,str_recovered,str_death,str_last_update_time;
    Float con,act,rec,dea;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        Init();
        Fetchdata();

        global=findViewById(R.id.button4);
        global.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Statistics.this, Statisticsworld.class);
                startActivity(i);
                finish();

            }
        });


        info = findViewById(R.id.imageView2);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Statistics.this, Info.class);
                startActivity(i);
                finish();

            }
        });

        back = findViewById(R.id.imageView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Statistics.this, Home.class);
                startActivity(i);
                finish();

            }
        });
    }

    private void Fetchdata() {
        // String url = "https://corona.lmao.ninja/v2/all/";
        String url = "https://corona.lmao.ninja/v2/countries/vietnam";
        //  simpleArcLoader.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                            Handler delayToshowProgess = new Handler();

                            delayToshowProgess.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    JSONObject jsonObject = null;
                                    try {
                                        jsonObject = new JSONObject(response.toString());
                                        tv_confirmed.setText(jsonObject.getString("cases"));
                                        tv_recovered.setText(jsonObject.getString("recovered"));
                                        tv_active.setText(jsonObject.getString("todayCases"));
                                        tv_death.setText(jsonObject.getString("deaths"));
                                        tv_death.setText(jsonObject.getString("todayDeaths"));
                                       // str_last_update_time = jsonObject.getString("lastupdatedtime"); //Last update date and time
                                        // tv_date.setText(FormatDate(str_last_update_time, 1));
                                        // tv_time.setText(FormatDate(str_last_update_time, 2));
                                        // tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));


                                        pieChart.addPieSlice(new PieModel("Cases",
                                                Integer.parseInt(tv_confirmed.getText().toString()), Color.parseColor("#FFC107")));
                                        pieChart.addPieSlice(new PieModel("Recoverd",
                                                Integer.parseInt(tv_recovered.getText().toString()),
                                                Color.parseColor("#8051D3")));
                                        pieChart.addPieSlice(new PieModel("Deaths",
                                                Integer.parseInt(tv_death.getText().toString()), Color.parseColor("#F6404F")));
                                        pieChart.startAnimation();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }
                            }, 1000);





                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


    private void Init() {
        tv_confirmed=findViewById(R.id.confirmed);
        tv_active=findViewById(R.id.active);
        tv_recovered=findViewById(R.id.recovered);
        tv_death=findViewById(R.id.death);

        tv_date = findViewById(R.id.textView9);
        tv_time = findViewById(R.id.textView10);

        pieChart = findViewById(R.id.activity_main_piechart);
    }

//    private void Fetchdata() {
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        String apiUrl = "https://api.covid19india.org/data.json";
//        pieChart.clearChart();
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//                Request.Method.GET,
//                apiUrl,
//                null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        //As the data of the json are in a nested array, so we need to define the array from which we want to fetch the data.
//                        JSONArray all_state_jsonArray = null;
//                        JSONArray testData_jsonArray = null;
//
//                        try {
//                            all_state_jsonArray = response.getJSONArray("statewise");
//                            testData_jsonArray = response.getJSONArray("tested");
//                            JSONObject data_india = all_state_jsonArray.getJSONObject(0);
//
//
//                            //Fetching data for India and storing it in String
//                            str_confirmed = data_india.getString("confirmed");   //Confirmed cases in India
//                            str_active = data_india.getString("active");    //Active cases in India
//                            str_recovered = data_india.getString("recovered");  //Total recovered cased in India
//                            str_death = data_india.getString("deaths");     //Total deaths in India
//                            str_last_update_time = data_india.getString("lastupdatedtime"); //Last update date and time
//
//                            Handler delayToshowProgess = new Handler();
//
//                            delayToshowProgess.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    //Setting text in the textview
//                                    tv_confirmed.setText(NumberFormat.getInstance().format(Integer.parseInt(str_confirmed)));
//                                    c = Integer.parseInt(str_confirmed);
//                                    tv_active.setText(NumberFormat.getInstance().format(Integer.parseInt(str_active)));
//
//                                    tv_recovered.setText(NumberFormat.getInstance().format(Integer.parseInt(str_recovered)));
//
//                                    tv_death.setText(NumberFormat.getInstance().format(Integer.parseInt(str_death)));
//                                    tv_date.setText(FormatDate(str_last_update_time, 1));
//                                    tv_time.setText(FormatDate(str_last_update_time, 2));
//
//                                    pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(str_active), Color.parseColor("#8051D3")));
//                                    pieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(str_recovered), Color.parseColor("#FFC107")));
//                                    pieChart.addPieSlice(new PieModel("Deceased", Integer.parseInt(str_death), Color.parseColor("#F6404F")));
//
//                                    pieChart.startAnimation();
//
//                                }
//                            }, 1000);
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                });
//        requestQueue.add(jsonObjectRequest);
//    }

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









/*{
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://pomber.github.io/covid19/timeseries.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray all_state_jsonArray = response.getJSONArray("India");
                            int len = all_state_jsonArray.length() - 1;
                            JSONObject data_india = all_state_jsonArray.getJSONObject(len);
                            String str_confirmed = data_india.getString("confirmed");   //Confirmed cases in India
                            String str_active = data_india.getString("date");    //Active cases in India
                            String str_recovered = data_india.getString("deaths");  //Total recovered cased in India
                            String str_death = data_india.getString("recovered");


                            tv_confirmed.setText(str_confirmed);
                            tv_active.setText(str_active);
                            tv_recovered.setText(str_recovered);
                            tv_death.setText(str_death);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        requestQueue.add(jsonObjectRequest);


    }*/
}
