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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button getBtn;
    private TextView result;
    private OkHttpClient client;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        result = (TextView) findViewById(R.id.result);
        getBtn = (Button) findViewById(R.id.getBtn);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWebservice();
            }

        });

        client = new OkHttpClient();
    }
        private void getWebservice() {
        final Request request = new Request.Builder().url("http://coms-309-ug-09.cs.iastate.edu").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        result.setText("Fail");
                    }
                });


            }

            @Override
            public void onResponse(Call call, final Response response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            result.setText(response.body().string());
                        } catch (IOException ioe){
                            result.setText("Error during get body");
                        }
                    }
                });


            }
        });
    }

}







