package com.example.loginscreen.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;
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

import com.util.server.ServerRequestMaker;


public class RegisterActivity extends AppCompatActivity {
    private EditText username, password;
    private Button submit;
    private Map<String, String> map;
    private ServerRequestMaker requestMaker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);
        requestMaker = new ServerRequestMaker("http://coms-309-ug-09.cs.iastate.edu/database/");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

   private void register() {
        requestMaker.setParam("username", this.username.getText().toString().trim());
        requestMaker.setParam("password", this.username.getText().toString().trim());

        requestMaker.sendRequest("add/",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String success = response;
                        if(success.equals("true")){
                            Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, this);
    }
}
