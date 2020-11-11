package com.example.loginscreen.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginscreen.R;

import java.util.HashMap;
import java.util.Map;

/**
 * The Event activity for Sea Salt
 * @author Chandler Jurenic and Aaron Goff
 * This is the secondary events page that communicates with
 * the backend in order to take the users event request and store that
 * data to the database
 */
public class EventsActivity extends AppCompatActivity {

    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/event/add/";

    EditText title,time,description;
    String owner = "true";
    Button addEvent;
    private Map<String, String> map;

    /**
     * This page is created once the user presses the make an event button
     * on the main event page
     * @param savedInstanceState
     */
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

    /**
     * This function takes the user input from the page then sends
     * the information via hashmap to the back end using Volley Rest Api
     */
    private void submitEvent() {
        final String title = this.title.getText().toString().trim();
        final String time = this.time.getText().toString().trim();
        //final String date = this.date.getText().toString().trim();
        final String description = this.description.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String success = response;
                        if ((success != (" ")) && (UserActivity.checkUsername == UserActivity.loginUsername) && (UserActivity.checkPassword == UserActivity.loginPassword)) {

                            Toast.makeText(EventsActivity.this, "Event Added", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(EventsActivity.this, "Event Couldn't be added, are you still logged in?", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EventsActivity.this, "Event Adding Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            /**
             * holds the values the user entered when creating a new event
             * @return the map holding the values for username, pass for login verification, event title, time, description, and owner of event
             * @throws AuthFailureError
             */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", UserActivity.loginUsername);
                map.put("password", UserActivity.loginPassword);
                map.put("eventName", title);
                map.put("eventTime", time);
                map.put("eventDesc", description);
                map.put("owner", owner);
                return map;
            }
        };
        /**
         * adding a string request to the queue
         */
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
