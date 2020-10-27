package com.example.loginscreen.ui.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginscreen.R;

import org.jetbrains.annotations.Nullable;


public class UploadActivity extends AppCompatActivity {

    private Uri selectedImage;
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
}

