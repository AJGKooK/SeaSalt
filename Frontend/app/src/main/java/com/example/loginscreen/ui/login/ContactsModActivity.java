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

    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/user/";

    EditText contactResult;
    String owner = "true";
    Button addContactButton;
    Button removeContactButton;
    Boolean removeContact = false;
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

        contactResult = findViewById(R.id.contactTextBox);
        addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                modifyContact();
            }
        });
        removeContactButton = (Button) findViewById(R .id.removeContactButton);
        removeContactButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                removeContact = true;
                modifyContact();
            }
        });
    }

    /**
     * This function takes the user input from the page then sends
     * the information via hashmap to the back end using Volley Rest Api
     */
    private void modifyContact() {
        final String contactString = this.contactResult.getText().toString().trim();
        if(removeContact){
            API_URL += "delcontact";
            removeContact = false;
        }
        else
            API_URL += "addcontact";
        
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
                map.put("contact", contactString);
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
