package com.example.loginscreen.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
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
        editText = (EditText) findViewById(R.id.chatLog);
        msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String logText = editText.getText().toString();

                list.add(logText);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
                sendMessage();
            }

        });


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
                    map.put("msgContent", message);
                    return map;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

            editText.getText().clear();
        }
    }
}