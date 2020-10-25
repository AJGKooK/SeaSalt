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

public class ChatActivity extends AppCompatActivity {

    private String channelID = "Channel ID";
    private String roomName = "observable-room";
    private EditText editText;
    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/database/add/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

//        editText = (EditText) findViewById(R.id.editText);
    }
    

}
