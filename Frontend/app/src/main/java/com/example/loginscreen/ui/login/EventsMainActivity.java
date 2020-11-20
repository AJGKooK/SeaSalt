package com.example.loginscreen.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginscreen.R;

/**
 * The main Events activity page for Sea Salt
 * @author Chandler Jurenic and Aaron Goff
 * first page the user sees when navigating to events
 */
public class EventsMainActivity  extends AppCompatActivity {

    private Button createButton;
    public static TextView textView;

    /**
     * when user presses the events button, this page is created with a list
     * of the current events that they have in order from top to bottom, soonest to latest
     * and an option to add new events
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_main);

        textView = findViewById(R.id.textView);
        createButton = (Button) findViewById(R.id.createButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createEvent();
            }
        });

    }

    /**
     * creates and event when the button is clicked on the main events page
     */
    public void createEvent(){
        Intent intent = new Intent(this, EventsActivity.class);
        startActivity(intent);
    }


}
