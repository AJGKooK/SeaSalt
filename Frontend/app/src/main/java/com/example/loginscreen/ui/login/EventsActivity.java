package com.example.loginscreen.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.AttributeSet;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginscreen.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EventsActivity extends AppCompatActivity {
    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/users/events/";
    EditText title,time,description;
    Button addEvent;
    private Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        title = findViewById(R.id.eventTitle);
        time = findViewById(R.id.timeEvent);
        //date = findViewById(R.id.dateEvent);
        description = findViewById(R.id.descriptionEvent);
        addEvent = findViewById(R.id.addEvent);

        addEvent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                submitEvent();
            }
        });


    }

    private void submitEvent() {
        final String title = this.title.getText().toString().trim();
        final String time = this.time.getText().toString().trim();
        //final String date = this.date.getText().toString().trim();
        final String description = this.description.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<int>() {
                    @Override
                    public void onResponse(int response) {
                        int success = response;
                        if (success == 0) {
                            Toast.makeText(EventsActivity.this, "Event Added", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(EventsActivity.this, "Event Couldn't be added", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EventsActivity.this, "Event Adding Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("eventName", title);
                map.put("eventTime", time);
                map.put("eventDesc", description);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}
