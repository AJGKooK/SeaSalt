package com.example.loginscreen.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.loginscreen.R;

import com.util.server.ServerRequestMaker;


public class RegisterActivity extends AppCompatActivity {
    private EditText username, password;
    private Button submit;
    private ServerRequestMaker requestMaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);
        requestMaker = new ServerRequestMaker();
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

        requestMaker.clearParams();
    }
}
