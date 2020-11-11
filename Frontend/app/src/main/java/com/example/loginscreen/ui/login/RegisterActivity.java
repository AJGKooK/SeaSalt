package com.example.loginscreen.ui.login;

import android.content.Intent;
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
 * The register page activity page for Sea Salt
 * @author Chandler Jurenic and Aaron Goff
 * This page is linked from the login page
 * RegisterActivity allows a user to set up a new username and password, as well as input their first and last name
 */
public class RegisterActivity extends AppCompatActivity {
    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/user/register/";
    private EditText username, password, password_verify, name_first, name_last;
    private Button submit;
    private Map<String, String> map;

    /**
     * This onCreate stores the username, password, password_verify, name_first, and name_last to be checked for errors
     * and passed to register() for upload to the server.
     * The submit button allows users to submit data when they are finished entering
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        password_verify = findViewById(R.id.password_verify);
        name_first = findViewById(R.id.name_first);
        name_last = findViewById(R.id.name_last);

        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    /**
     * register sets and trims the strings for username, password, name_first, and name_last
     * register sends password and password_verify to passwordCheck to verify a valid password has been entered
     */
    private void register() {
        final String username = this.username.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String name_first = this.name_first.getText().toString().trim();
        final String name_last = this.name_last.getText().toString().trim();
        passwordCheck(this.password.getText().toString().trim(), this.password_verify.getText().toString().trim());



        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    /**
                     * onResponse sends the user a message to whether the registration was successful, or if it failed
                     * @param response
                     */
                    @Override
                    public void onResponse(String response) {
                        String success = response;
                        if(success.equals("0")){
                            Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                            openLogin();
                        }
                        else if(success.equals("1")){
                            Toast.makeText(RegisterActivity.this, "Login? Already registered", Toast.LENGTH_SHORT).show();
                            openLogin();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    /**
                     * onErrorResponse sends the user a message as to why there was a register error
                     * @param error
                     */
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Register Error!" + " " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            /**
             * sends username, password, name_first, name_last to the backend server
             * @throws AuthFailureError
             * @return
             */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                map.put("password", password);
                map.put("firstName", name_first);
                map.put("lastName", name_last);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        //push
    }
    /**
     * stringCompare compares two strings to verify they are identical
     * @param str1
     * @param str2
     * @return will return true if both strings are identical, will return false if strings are different
     */
    public static Boolean stringCompare (String str1, String str2){
        if (str1.length() == str2.length()){
            for (int i = 0; i < str1.length(); i++){
                if (str1.charAt(i) != str2.charAt(i)){
                    return false;
                }
                else
                    return true;
            }
        }
        else
            return false;
        return false;
    }
    /**
     * passwordCheck verifies that both passwords input are identical and that the password length isn't too short
     * @param passwordString
     * @param passwordVerifyString
     * @return returns a single valid password as a string
     */
    public String passwordCheck (String passwordString, String passwordVerifyString){
        if (!stringCompare(passwordString, passwordVerifyString)){
            Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            // Passwords do not match
        }
        else if (passwordString.length() < 3){
            Toast.makeText(RegisterActivity.this, "Password length too short", Toast.LENGTH_SHORT).show();
            // Password too short, set to 3 for testing, final version will be 8
        }
        else{
            return passwordString;
        }
        return null;
    }
    /**
     * openLogin allows the user to click a button to get back to the login page without submitting new user data
     */
    public void openLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
