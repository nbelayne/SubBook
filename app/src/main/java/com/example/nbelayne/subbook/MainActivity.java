package com.example.nbelayne.subbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "subscriptions.sav";

    public static final String SUB_NAME = "SUBSCRIPTION_NAME";
    public static final String SUB_DATE = "SUBSCRIPTION_DATE";
    public static final String SUB_CHARGE = "SUBSCRIPTION_CHARGE";
    public static final String SUB_COMMENT = "SUBSCRIPTION_COMMENT";

    private ListView oldSubList;
    private ArrayList<Subscription> subList;
    private ArrayAdapter<Subscription> adapter;
    private Double totalMonthlyCharge = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addSubButton = findViewById(R.id.addSub);
        oldSubList =  findViewById(R.id.subList);
        //TextView monthlyCharge = findViewById(R.id.monthlyCharge);

        subList = new ArrayList<>();
        adapter = new ArrayAdapter<Subscription>(this,
                R.layout.list_item, subList);
        oldSubList.setAdapter(adapter);

        oldSubList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                totalMonthlyCharge -= Double.parseDouble(subList.get(position).getCharge());
                TextView monthlyCharge = findViewById(R.id.monthlyCharge);
                monthlyCharge.setText("$" + String.format("%.2f", totalMonthlyCharge));
                subList.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        oldSubList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent = new Intent(getApplicationContext(), DisplayNewSubscriptionActivity.class);
              // this is where parcelable comes in, parcelable lets u pass an object from activity to acitivty
              intent.putExtra("EDIT_SUBSCRIPTION", subList.get(position));
              startActivityForResult(intent, 0);
          }
        });
    }

    @Override
    protected void onStart(){
        //called everytime it appears
        super.onStart();

        //loadFromFile();

    }

    /**
     * Load subscription from list (most of code from LonelyTwitter)
     */
//    private void loadFromFile() {
//
//        try {
//            FileInputStream fis = openFileInput(FILENAME);
//            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//            sub.tostrubf.
//            Gson gson = new Gson();
//
//            // Taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
//            // 2018-01-23
//            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
//            subList = gson.fromJson(in, listType);
//
//        } catch (FileNotFoundException e) {
//            subList = new ArrayList<Subscription>();
//        } catch (IOException e) {
//            throw new RuntimeException();
//        }
//
//    }

    /** Called when the user taps the ADD A NEW SUBSCRIPTION button*/
    public void addSubscription(View view){
        Intent intent = new Intent(this, DisplayNewSubscriptionActivity.class);
        // this is where parcelable comes in, parcelable lets u pass an object from activity to acitivty
        intent.putExtra("EDIT_SUBSCRIPTION", new Subscription(this));
        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Subscription sub = new Subscription(this);
        if (requestCode == 0) {
            if(resultCode == Activity.RESULT_OK){
                sub.setName(data.getStringExtra(SUB_NAME));
                sub.setComment(data.getStringExtra(SUB_COMMENT));
                sub.setCharge(data.getStringExtra(SUB_CHARGE));
                sub.setDate(data.getStringExtra(SUB_DATE));
                subList.add(sub);
                totalMonthlyCharge += Double.parseDouble(sub.getCharge());
                TextView monthlyCharge = findViewById(R.id.monthlyCharge);
                monthlyCharge.setText("$" + String.format("%.2f", totalMonthlyCharge));
                adapter.notifyDataSetChanged();
            } else {
                // prompt error
            }
        }
    }//onActivityResult

    /**
     * Saves tweet in list
     */
//    private void saveInFile() {
//        try {
//
//            FileOutputStream fos = openFileOutput(FILENAME,
//                    Context.MODE_PRIVATE);
//            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
//
//            Gson gson = new Gson();
//            gson.toJson(subList, out);
//            out.flush();
//
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            throw new RuntimeException();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            throw new RuntimeException();
//        }
//    }

}

