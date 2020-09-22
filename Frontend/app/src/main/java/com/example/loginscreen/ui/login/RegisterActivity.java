package com.example.loginscreen.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import org.springframework.web.client.RestTemplate;
import android.os.Bundle;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.StrictMode;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.loginscreen.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
    private String API_URL = "http://coms-309-ug-09.cs.iastate.edu";
    private EditText username, password;
    private Button submit;
    private OkHttpClient client;
    private Map<String, String> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    public boolean register() {
        final String[] result = new String[1];
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("username", username.getText().toString().trim())
                .addFormDataPart("password", password.getText().toString().trim()).build();

        final Request request = new Request.Builder().url("http://coms-309-ug-09.cs.iastate.edu/database/add").post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result[0] = "fail";
                    }
                });


            }

            @Override
            public void onResponse(Call call, final Response response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            result[0] = response.body().string();
                        } catch (IOException ioe){
                            result[0] = "Error during get body";
                        }
                    }
                });


            }
        });

        if(result[0].equals("true")) return true;
        /*RestTemplate restTemplate = new RestTemplate();
        Map<String, String> map = new HashMap<>();
        map.put("username", username.getText().toString().trim());
        map.put("password", password.getText().toString().trim());
        boolean ret = restTemplate.postForObject("http://coms-309-ug-09.cs.iastate.edu/add/", null, boolean.class, map);*/
        return false;
    }
    class MyTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
