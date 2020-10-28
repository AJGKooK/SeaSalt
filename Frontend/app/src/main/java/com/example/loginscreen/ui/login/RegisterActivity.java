package com.example.loginscreen.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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


public class RegisterActivity extends AppCompatActivity {
    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/user/register/";
    private EditText username, password, password_verify, name_first, name_last;
    private Button submit;
    private Map<String, String> map;
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

    private void register() {
        final String username = this.username.getText().toString().trim();
        passwordCheck(this.password.getText().toString().trim(), this.password_verify.getText().toString().trim());
        final String password = this.password.getText().toString().trim();
        final String name_first = this.name_first.getText().toString().trim();
        final String name_last = this.name_last.getText().toString().trim();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                new Response.Listener<String>() {
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
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("username", username);
                map.put("password", password);
                map.put("name_first", name_first);
                map.put("name_last", name_last);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        //push
    }
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

    public void openLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
