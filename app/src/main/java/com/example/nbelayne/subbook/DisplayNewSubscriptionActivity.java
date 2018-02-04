package com.example.nbelayne.subbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    private EditText subName;
    private EditText startDate;
    private EditText subCharge;
    private EditText subComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        subName = (EditText) findViewById(R.id.nameEdit);
        startDate = (EditText) findViewById(R.id.dateEdit);
        subCharge = (EditText) findViewById(R.id.chargeEdit);
        subComment = (EditText) findViewById(R.id.commentEdit);
        Button doneButton = (Button) findViewById(R.id.doneButton);


        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        //String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        //TextView textView = findViewById(R.id.textView);
        //textView.setText(message);

    }
}
