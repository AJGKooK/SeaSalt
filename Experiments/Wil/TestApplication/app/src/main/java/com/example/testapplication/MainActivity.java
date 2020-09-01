package com.example.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeText(View view)
    {
        TextView screenText = findViewById(R.id.MainText);
        View painPic = findViewById(R.id.pain);

        if(painPic.getVisibility() == View.INVISIBLE)
        {
            screenText.setText(R.string.alt_text0);
            painPic.setVisibility(View.VISIBLE);
        }
    }
}