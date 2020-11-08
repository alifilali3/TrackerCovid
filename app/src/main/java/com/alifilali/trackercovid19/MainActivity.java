package com.alifilali.trackercovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
  TextView tvCases,tvtodaycases,tvdeaths,tvtodaydeaths,tvrecovered,tvactive,tvcritical,tvaffectedcountries;
  SimpleArcLoader simpleArcLoader;
  ScrollView scrollView;
  PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCases = findViewById(R.id.tvcases);
        tvtodaycases = findViewById(R.id.tvtodaycases);
        tvdeaths = findViewById(R.id.tvdeaths);
        tvtodaydeaths = findViewById(R.id.tvtodaydeaths);
        tvrecovered = findViewById(R.id.tvrecovred);
        tvactive = findViewById(R.id.tvrecovred);
        tvcritical = findViewById(R.id.tvcritical);
        simpleArcLoader=findViewById(R.id.loader);
        scrollView = findViewById(R.id.scrollView);
        pieChart = findViewById(R.id.piechart);
           fetchData();
    }
       private void fetchData()
       {
           String url ="https://corona.lmao.ninja/v2/all";
           simpleArcLoader.start();
           final StringRequest request = new StringRequest(Request.Method.GET, url,
                   new Response.Listener<String>() {
                       @Override
                       public void onResponse(String response) {
                           try {
                               JSONObject jsonObject = new JSONObject(response.toString());

                               tvCases.setText(jsonObject.getString("cases"));
                               tvtodaycases.setText(jsonObject.getString("todayCases"));
                               tvdeaths.setText(jsonObject.getString("deaths"));
                               tvtodaydeaths.setText(jsonObject.getString("tvtodaydeaths"));
                               tvrecovered.setText(jsonObject.getString("recovered"));
                               tvactive.setText(jsonObject.getString("tvactive"));
                               tvcritical.setText(jsonObject.getString("critical"));
                               tvaffectedcountries.setText(jsonObject.getString("tvaffectedcountries"));
                               pieChart.addPieSlice(new PieModel("cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFA726")));
                               pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvrecovered.getText().toString()), Color.parseColor("#66BB6A")));
                               pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvdeaths.getText().toString()), Color.parseColor("#66BB6A")));
                               pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvactive.getText().toString()), Color.parseColor("#2986F6")));
                                pieChart.startAnimation();
                                simpleArcLoader.stop();
                                simpleArcLoader.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);

                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                       }
                   }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   simpleArcLoader.stop();
                   simpleArcLoader.setVisibility(View.GONE);
                   scrollView.setVisibility(View.VISIBLE);
               }
           });
           RequestQueue requestQueue = Volley.newRequestQueue(this);
           requestQueue.add(request);

       }
    public void goTrackCountries(View view) {

    }
}