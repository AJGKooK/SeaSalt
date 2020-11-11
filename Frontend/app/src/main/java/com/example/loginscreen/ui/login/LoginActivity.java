package com.example.loginscreen.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

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
 * The login page for Sea Salt
 * @author Chandler Jurenic and Aaron Goff
 * the login page that a user sees when wanting to access the apps features
 */
public class LoginActivity extends AppCompatActivity {
    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/user/login/";
    private EditText username, password;
    private LoginViewModel loginViewModel;
    private Map<String, String> map;
    private Button newaccount, login;

    /**
     * displays the login page when a user opens the application
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        //ProgressBar loadingProgressBar = findViewById(R.id.loading);
        newaccount = (Button) findViewById(R.id.newaccount);
        newaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistration();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });
    }

    /**
     * Takes the user's login info(username and password)
     * then updates a temp user and pass to have continuous user verification
     * sends a request to backend using Volley Rest Api to verify user/pass are
     * correct or even exist, offering registration if the user doesn't exist.
     */
    private void userLogin(){
        final String username = this.username.getText().toString().trim();
        final String password = this.password.getText().toString().trim();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String success = response;
                        if(success.equals("0")){
                            UserActivity.loginUsername = username;
                            UserActivity.loginPassword = password;
                            UserActivity.checkUsername = username;
                            UserActivity.checkPassword = password;
                            Toast.makeText(LoginActivity.this, "Welcome" + " " + username, Toast.LENGTH_SHORT).show();
                            openMainMenu();

                        }
                        else if(success.equals("1")){
                            Toast.makeText(LoginActivity.this, "Password incorrect", Toast.LENGTH_SHORT).show();

                        }
                        else if(success.equals("2")){
                            Toast.makeText(LoginActivity.this, "Username doesn't exist, Register!", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(LoginActivity.this, "User doesn't exist, want to register?", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Login Error!" + " " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {

            /**
             * Takes the username and password that the user entered and stores them in a hashmap
             * to be sent to the database, the database then returns 0/1/other value to indicate
             * if the user exists or user/pass are incorrect.
             * @return
             * @throws AuthFailureError
             */
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                map.put("password", password);
                return map;
            }
        };
        /**
         * adds this string request to the queue
         */
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    /**
     * This function opens the registration page when the account doesn't exist
     * and the user wishes to register for Sea Salt
     */
    public void openRegistration(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    /**
     * This function opens the main menu interface once the user successfully logs in to the
     * application and leads them to the plethora of features Sea Salt offers
     */
    public void openMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}