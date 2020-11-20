package com.example.loginscreen.ui.login;


import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
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


/**
 * The main Events activity page for Sea Salt
 * @author Chandler Jurenic and Aaron Goff
 * ChatAcivity brings up a ListView which constantly updates from the backend to bring up messages from
 * multiple users of the group.  It is also able to send and store messages to other users.
 */
public class ChatActivity extends AppCompatActivity {
    private WebSocketClient webSocket;
    private ListView listView;
    private TextView textView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list;
    private ImageButton msgButton;
    private EditText editText;
    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/messages/post/";


    /**
     * Once a message is entered, the message is sent once the user presses the end button
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        connectWebSocket();

        textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());
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

    /**
     * sendMessage checks the username and password, checks for connection with the backend, and checks the message length.
     * Upon entering a message into the chat box, it will send the message to the back end which will store the message and
     * send it to other users to display on their app.
     */
    public void sendMessage() {
        final String message = this.editText.getText().toString();
        webSocket.send(message);
        if (message.length() > 0) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
                    new Response.Listener<String>() {
                        /**
                         * onResponse informs the user if the message was not delivered in the case of an error
                         * @param response
                         */
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
                        /**
                         * onErrorResponse sends a message to the user that there was a chat message error
                         * @param error
                         */
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ChatActivity.this, "Chat Message Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    })
            {

                /**
                 * holds the values the user entered when creating a message
                 * @return the map holding the values for username, pass for login verification, and the message content
                 * @throws AuthFailureError
                 */
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("username", UserActivity.loginUsername);
                    map.put("password", UserActivity.loginPassword);
                    map.put("msgContent", message);
                    return map;
                }
            };
            /**
             * adding a string request to the queue
             */
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
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
            }

            @Override
            public void onMessage(String msg) {
                Log.i("Websocket", "Message Received" + " " + msg);
                textView.append("\n" + msg);
            }

            @Override
            public void onClose(int errorCode, String reason, boolean remote) {
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