package com.example.nbelayne.subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.lang.*;

public class DisplayNewSubscriptionActivity extends AppCompatActivity {

    public static final String SUB_NAME = "SUBSCRIPTION_NAME";
    public static final String SUB_DATE = "SUBSCRIPTION_DATE";
    public static final String SUB_CHARGE = "SUBSCRIPTION_CHARGE";
    public static final String SUB_COMMENT = "SUBSCRIPTION_COMMENT";

    private EditText subName;
    private EditText startDate;
    private EditText subCharge;
    private EditText subComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_subscription);

        subName =  findViewById(R.id.nameEdit);
        startDate =  findViewById(R.id.dateEdit);
        subCharge = findViewById(R.id.chargeEdit);
        subComment =  findViewById(R.id.commentEdit);

        Intent i = getIntent();
        Subscription newSub = (Subscription) i.getParcelableExtra("EDIT_SUBSCRIPTION");
        subName.setText(newSub.getName());
        startDate.setText(newSub.getDate());
        subCharge.setText(newSub.getCharge());
        subComment.setText(newSub.getComment());
    }

    //https://stackoverflow.com/questions/10407159/how-to-manage-startactivityforresult-on-android
    //2018-02-04
    public void newSubscription(View view){
        String nameText = subName.getText().toString();
        String dateText = startDate.getText().toString();
        String chargeText = subCharge.getText().toString();
        String commentText = subComment.getText().toString();

        Subscription sub = new Subscription(this);
        sub.setName(nameText);
        sub.setComment(commentText);
        sub.setCharge(chargeText);
        sub.setDate(dateText);
        if (sub.getCharge() != null && sub.getComment() != null && sub.getDate() != null &&
                sub.getName() != null) {
            Intent returnIntent = new Intent();

            returnIntent.putExtra(SUB_NAME, nameText);
            returnIntent.putExtra(SUB_DATE, dateText);
            returnIntent.putExtra(SUB_CHARGE, chargeText);
            returnIntent.putExtra(SUB_COMMENT, commentText);

            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }
}
