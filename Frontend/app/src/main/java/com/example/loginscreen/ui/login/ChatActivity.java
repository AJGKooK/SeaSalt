package com.example.loginscreen.ui.login;


import android.os.Bundle;
import android.util.Log;
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

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class ChatActivity extends AppCompatActivity {
    private WebSocketClient webSocket;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list;
    private ImageButton msgButton;
    private EditText editText;
    private String text;
    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/messages/post/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        connectWebSocket();

        listView = (ListView) findViewById(R.id.listview);
        msgButton = (ImageButton) findViewById(R.id.msgButton);
        editText = (EditText) findViewById(R.id.chatLog);
        final String message = editText.getText().toString();
        msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String logText = editText.getText().toString();

                list.add(logText);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
                sendMessage();
                webSocket.send(message);

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

    private void connectWebSocket(){
        URI uri;
        try{
            uri = new URI("ws://coms-309-ug-09.cs.iastate.edu/chat/" + UserActivity.loginUsername);
        }catch(URISyntaxException e){
            e.printStackTrace();
            return;
        }
        webSocket = new WebSocketClient(uri){

            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.i("Websocket", "Opened");
            }

            @Override
            public void onMessage(String message) {
                Log.i("Websocket", "Message Received");
                list.add(message);
                listView.setAdapter(arrayAdapter);
                arrayAdapter.notifyDataSetChanged();
                editText.append("\n" + message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.i("Websocket", "Closed" + reason);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Closed" + e.getMessage());
            }
        };
        webSocket.connect();
    }



}