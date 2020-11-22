package com.example.loginscreen.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.loginscreen.R;

import org.w3c.dom.Text;

import java.text.BreakIterator;

/**
 * The contacts activity page for Sea Salt
 * @author Chandler Jurenic and Aaron Goff
 * The main screen for contacts when a user navigates to their friends list
 */
public class ContactsActivity extends AppCompatActivity {

    public static TextView textView;
    private Button modifyButton;


    /**
     * when a the friends list button is clicked, this page is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        textView = (TextView) findViewById(R.id.textView);
        modifyButton = (Button) findViewById(R.id.modifyButton);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyContacts();
            }
        });
    }

    public void modifyContacts(){
        Intent intent = new Intent(this, ContactsModActivity.class);
        startActivity(intent);
    }

}
