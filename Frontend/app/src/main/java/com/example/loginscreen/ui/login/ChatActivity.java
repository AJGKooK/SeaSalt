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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import android.widget.ImageButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list;
    private ImageButton msgButton;
    private EditText editText;
    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/messages/post/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        listView = (ListView) findViewById(R.id.listview);
        msgButton = (ImageButton) findViewById(R.id.msgButton);
        msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String logText = editText.getText().toString();

                list.add(logText);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
                editText.getText().clear();
                sendMessage();
            }
        });

        editText = (EditText) findViewById(R.id.chatLog);

        list = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1, list);
    }

    public void sendMessage() {
        final String message = this.editText.getText().toString();
        if (message.length() > 0) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            String success = response;
                            if ((success != " ") && (UserActivity.checkUsername == UserActivity.loginUsername) && (UserActivity.checkPassword == UserActivity.loginPassword)) {
                                // Do nothing, message sent successfully
                                Toast.makeText(ChatActivity.this, "Message delivered", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ChatActivity.this, "Message not delivered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ChatActivity.this, "Chat Message Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("username", UserActivity.loginUsername);
                    map.put("password", UserActivity.loginPassword);
                    map.put("msg", message);
                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

            editText.getText().clear();
        }
    }
}