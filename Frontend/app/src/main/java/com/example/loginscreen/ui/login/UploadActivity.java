package com.example.loginscreen.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginscreen.R;

import java.io.File;

public class UploadActivity extends AppCompatActivity {

    private ImageButton fileFinderButton;

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


    }

    public void openFileFinder(){
//        startActivity(new Intent(Intent.ACTION_PICK)){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + "/sdcard/test.jpg"), "image/*");
        startActivity(intent);


        }



}


