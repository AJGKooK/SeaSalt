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

public class ContactsModActivity extends AppCompatActivity {

    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/event/add/";

    EditText title,time,description;
    String owner = "true";
    Button addContact;
    Button removeContact;
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
        description = findViewById(R.id.descriptionEvent);
        addContact = findViewById(R.id.addContact);
        addContact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addContact();
            }
        });
        removeContact = findViewById(R.id.removeContact);
        removeContact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                removeContact();
            }
        });

    }

    /**
     * This function takes the user input from the page then sends
     * the information via hashmap to the back end using Volley Rest Api
     */
    private void addContact() {
        final String title = this.title.getText().toString().trim();
        final String time = this.time.getText().toString().trim();
        final String description = this.description.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String success = response;
                        if ((success != (" ")) && (UserActivity.checkUsername == UserActivity.loginUsername) && (UserActivity.checkPassword == UserActivity.loginPassword)) {

                            Toast.makeText(ContactsModActivity.this, "Contact Added", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ContactsModActivity.this, "Contact Couldn't be added, are you still logged in?", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ContactsModActivity.this, "Contact Adding Error!" + error.toString(), Toast.LENGTH_SHORT).show();
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

    private void removeContact() {
        final String title = this.title.getText().toString().trim();
        final String time = this.time.getText().toString().trim();
        final String description = this.description.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        String success = response;
                        if ((success != (" ")) && (UserActivity.checkUsername == UserActivity.loginUsername) && (UserActivity.checkPassword == UserActivity.loginPassword)) {

                            Toast.makeText(ContactsModActivity.this, "Contact removed", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ContactsModActivity.this, "Contact Couldn't be removed, are you still logged in?", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ContactsModActivity.this, "Contact removing Error!" + error.toString(), Toast.LENGTH_SHORT).show();
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
