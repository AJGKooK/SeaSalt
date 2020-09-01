package com.example.experiment309cj;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
//this is a change for pushing purposes only
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addButton = (Button) findViewById(R.id.addButton);
        Button resetButton = (Button) findViewById(R.id.resetButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstNum = (EditText) findViewById(R.id.firstNum);
                TextView showResult = (TextView) findViewById(R.id.showResult);
                int num1 = Integer.parseInt(firstNum.getText().toString());
                int result = num1 * num1 * num1;
                showResult.setText(result + "");

            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstNum = (EditText) findViewById(R.id.firstNum);
                TextView showResult = (TextView) findViewById(R.id.showResult);
                firstNum.setText("");
                showResult.setText("");

            }
        });
    }
}