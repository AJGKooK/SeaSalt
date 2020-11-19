package com.example.loginscreen.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.loginscreen.R;

import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * The user activity page for Sea Salt
 * @author Chandler Jurenic and Aaron Goff
 * UserAcitivty holds the string values forcheckUsername, checkPassword, loginUsername, loginPassword to be used in the ChatActivity page
 */
public class UploadActivity extends AppCompatActivity {

    private Uri selectedImage;
    private Button uploadButton;
    private ImageButton fileFinderButton;
    private static String API_URL = "http://coms-309-ug-09.cs.iastate.edu/upload/user/";

    /**
     * onCreate allows user to press a button in order to upload the selected file to the server
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        fileFinderButton = (ImageButton) findViewById(R.id.fileFinderButton);
        fileFinderButton.setOnClickListener(new View.OnClickListener() {
            /**
             * allows user to open the file finder to locate the file to be uploaded
             * @param view
             */
            @Override
            public void onClick(View view) {
                openFileFinder();
            }
        });
        uploadButton = (Button) findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(new View.OnClickListener(){
            /**
             * uploads file on click
             * @param v
             */
            @Override
            public void onClick(View v){
                try {
                    fileUpload();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * openFileFinder gives the parameters for the file to be uploaded
     */
    private void openFileFinder() {
        Intent var1 = new Intent("android.intent.action.PICK");
        var1.setType("image/*");
        String[] mimeTypes = new String[]{"image/jpeg", "image/png"};
        var1.putExtra("android.intent.extra.MIME_TYPES", mimeTypes);
        this.startActivityForResult(var1, 100);
    }

    /**
     * onActivityResult readies the file within the app
     * @param requestCode
     * @param resultCode
     * @param data
     */
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


                /**
     * fileUpload sends the file to the server
     */
    public void fileUpload() throws IOException {
        final String message = "this.editText.getText().toString()";
        if (message.length() > 0) {
            final VolleyMultiPartRequest multipartRequest = new VolleyMultiPartRequest(Request.Method.POST, API_URL, new Response.Listener<NetworkResponse>() {
                        @Override
                        public void onResponse(NetworkResponse response) {
                            String success = response.toString();
                            if ((success != " ") && (UserActivity.checkUsername == UserActivity.loginUsername) && (UserActivity.checkPassword == UserActivity.loginPassword)) {
                                Toast.makeText(UploadActivity.this, "File uploaded successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(UploadActivity.this, "File not uploaded", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(UploadActivity.this, "Upload Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                /*
                Push <String, String>
                 */
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    Looper.prepare();
                    map.put("username", UserActivity.loginUsername);
                    map.put("password", UserActivity.loginPassword);
                    return map;
                }
                /*
                Push <String, DataPart>
                 */
                @Override
                protected Map<String, DataPart> getByteData() throws IOException {
                    Map<String, DataPart> params = new HashMap<>();
                    InputStream iStream =   getContentResolver().openInputStream(selectedImage);
                    final byte[] inputData;
                    inputData = getBytes(iStream);
//                    long imagename = System.currentTimeMillis();
                      long imagename = 3;
                    params.put("file", new DataPart(imagename + ".png", inputData));
                    return params;
                }
                                    /*
                Convert URI to Byte[]
                 */
                public byte[] getBytes(InputStream inputStream) throws IOException {
                    ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
                    int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];

                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        byteBuffer.write(buffer, 0, len);
                    }
                    return byteBuffer.toByteArray();
                }

            };

            VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);


        }


    }
}
