package com.example.loginscreen.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginscreen.R;
import com.example.loginscreen.ui.login.LoginViewModel;
import com.example.loginscreen.ui.login.LoginViewModelFactory;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/user/login/";
    private EditText username, password;
    private LoginViewModel loginViewModel;
    private Map<String, String> map;
    private Button newaccount, login;

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
                            Toast.makeText(LoginActivity.this, "Welcome" + " " + username.toUpperCase().charAt(0), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(LoginActivity.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                map.put("password", password);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void openRegistration(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    public void openMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}