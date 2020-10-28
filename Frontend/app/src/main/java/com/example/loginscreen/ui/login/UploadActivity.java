package com.example.loginscreen.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;


public class UploadActivity extends AppCompatActivity {

    private Uri selectedImage;
    private Button uploadButton;
    private ImageButton fileFinderButton;
    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/messages/post/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        fileFinderButton = (ImageButton) findViewById(R.id.fileFinderButton);
        fileFinderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileFinder();
            }
        });
        uploadButton = (Button) findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                fileUpload();
            }
        });

    }
    private void openFileFinder() {
        Intent var1 = new Intent("android.intent.action.PICK");
        var1.setType("image/*");
        String[] mimeTypes = new String[]{"image/jpeg", "image/png"};
        var1.putExtra("android.intent.extra.MIME_TYPES", mimeTypes);
        this.startActivityForResult(var1, 100);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch(requestCode) {
                case 100:
                    this.selectedImage = data != null ? data.getData() : null;
                    ImageButton fileFinderButton = this.fileFinderButton;
                    if (fileFinderButton != null) {
                        fileFinderButton.setImageURI(this.selectedImage);
                    }
            }
        }
    }

    public void fileUpload() {
//        final String message = "this.editText.getText().toString()";
//        if (message.length() > 0) {
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, API_URL,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            String success = response;
//                            if ((success != " ") && (UserActivity.checkUsername == UserActivity.loginUsername) && (UserActivity.checkPassword == UserActivity.loginPassword)) {
//                                Toast.makeText(UploadActivity.this, "File uploaded succesfully", Toast.LENGTH_SHORT).show();
//                            } else {
//                                Toast.makeText(UploadActivity.this, "File not uploaded", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(UploadActivity.this, "Upload Error!" + error.toString(), Toast.LENGTH_SHORT).show();
//                        }
//                    }) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> map = new HashMap<>();
//                    map.put("username", UserActivity.loginUsername);
//                    map.put("password", UserActivity.loginPassword);
////                    map.put("/upload/user", selectedImage);
//                    return map;
//                }
//            };
//            RequestQueue requestQueue = Volley.newRequestQueue(this);
//            requestQueue.add(stringRequest);
//        }


    }
}

