package com.example.loginscreen.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Event activity for Sea Salt
 * @author Chandler Jurenic and Aaron Goff
 * This is the secondary events page that communicates with
 * the backend in order to take the users event request and store that
 * data to the database
 */
public class EventsActivity extends AppCompatActivity {

    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/event/";
    EditText title,time,description;
    String owner = "true";
    private final String events = "";
    Button addEvent;
    private Map<String, String> map;
    private int j;

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

        //Send a request to /event/discover using the username and password
        //Assign the result to an integer array list (the values stored in the array list are now the IDs of the events)
        //For each of the elements in the list, send requests for the event to find the different elements of the events you need.
        //Do what you need to do to add the info from each of the events to the events screen
/*
        String discoverUrl = API_URL + "discover/";

        Response.Listener<ArrayList<Integer>> listener = new Response.Listener<ArrayList<Integer>>() {

            ArrayList<Integer> list;
            ListView eventList;

            View listEntryTemplate;

            @Override
            public void onResponse(ArrayList<Integer> response) {


                eventList = findViewById(R.id.listview);
                for(int i = 0; i < response.size(); i++)
                {

                }

                eventList.
            }


        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, discoverUrl,
                listener,
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
             *//*
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", UserActivity.loginUsername);
                map.put("password", UserActivity.loginPassword);
                return map;
            }
        };
    */

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

        String submitUrl = API_URL + "add/";


        StringRequest stringRequest = new StringRequest(Request.Method.POST, submitUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String success = response;
                        if ((success != (" ")) && (UserActivity.checkUsername == UserActivity.loginUsername) && (UserActivity.checkPassword == UserActivity.loginPassword)) {
                            Toast.makeText(EventsActivity.this, "Event Added", Toast.LENGTH_SHORT).show();
                            openEvents();
                            getEvents();

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
    public void openEvents(){
        Intent intent = new Intent(this, EventsMainActivity.class);
        startActivity(intent);

    }
    public void getEvents(){
        final ArrayList<String> contacts = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(EventsActivity.this);
        String url = "http://coms-309-ug-09.cs.iastate.edu/user/events/involved?username=" + UserActivity.loginUsername +"&password=" + UserActivity.loginPassword;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                EventsMainActivity.textView.setText("Upcoming events:\n");
                if (response.length() == 2) {
                    EventsMainActivity.textView.setText("You have no events");
                } else {
                    response = response.substring(1, response.length() - 1);
                    String[] usernames = response.split(",");
                    for (String bubbles : usernames) {
                        bubbles = bubbles.replace("\"", "");
                        RequestQueue queue = Volley.newRequestQueue(EventsActivity.this);
                        String url = "http://coms-309-ug-09.cs.iastate.edu/event/info?username=" + UserActivity.loginUsername + "&password=" + UserActivity.loginPassword + "&id=" + bubbles;
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                int i = 0;
                                Pattern p = Pattern.compile("\"([^\"]*)\"");
                                Matcher m = p.matcher(response);
                                while (m.find()) {
                                    if (i == 0)
                                        EventsMainActivity.textView.append("\n");
                                    if (i%2 == 0)
                                        EventsMainActivity.textView.append(m.group(1) + ": ");
                                    else
                                        EventsMainActivity.textView.append(m.group(1) + "\n");
                                    i++;
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                EventsMainActivity.textView.setText("Events Failed to display: ");
                                Log.i("Event list", "Event adding failed");
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> map = new HashMap<>();
                                map.put("username", UserActivity.loginUsername);
                                map.put("password", UserActivity.loginPassword);
                                map.put("id", events);
                                return map;
                            }
                        };
                        queue.add(stringRequest);

                    }
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
//                ContactsActivity.textView.setText("Events Failed to display");
                Log.i("Event list", "Event adding failed");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", UserActivity.loginUsername);
                map.put("password", UserActivity.loginPassword);
                return map;
            }
        };
        queue.add(stringRequest);


    }
}
