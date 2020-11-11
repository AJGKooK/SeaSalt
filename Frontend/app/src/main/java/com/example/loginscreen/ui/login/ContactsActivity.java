package com.example.loginscreen.ui.login;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.loginscreen.R;

/**
 * The contacts activity page for Sea Salt
 * @author Chandler Jurenic and Aaron Goff
 * The main screen for contacts when a user navigates to their friends list
 */
public class ContactsActivity extends AppCompatActivity {

    /**
     * when a the friends list button is clicked, this page is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);




    }

}
