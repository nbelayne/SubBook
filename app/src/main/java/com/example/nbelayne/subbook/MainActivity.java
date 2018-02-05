package com.example.nbelayne.subbook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by nbelayne on 2/3/18.
 *
 * NOTE: my loadFromFile() and saveInFile() methods were largely based off of Jingming Huang's
 * code structure.
 */

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "subscriptions.txt";

    public static final String SUB_NAME = "SUBSCRIPTION_NAME";
    public static final String SUB_DATE = "SUBSCRIPTION_DATE";
    public static final String SUB_CHARGE = "SUBSCRIPTION_CHARGE";
    public static final String SUB_COMMENT = "SUBSCRIPTION_COMMENT";

    private ListView oldSubList;
    private ArrayList<Subscription> subList;
    private ArrayAdapter<Subscription> adapter;
    private TextView totalMonthlyCharge;
    private int arrayPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldSubList =  findViewById(R.id.subList);
        subList = new ArrayList<>();
        totalMonthlyCharge = findViewById(R.id.monthlyCharge);

        oldSubList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                subList.remove(position);
                adapter.notifyDataSetChanged();
                saveInFile();
                return true;
            }
        });
        oldSubList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Intent intent = new Intent(getApplicationContext(), DisplayNewSubscriptionActivity.class);
              intent.putExtra("EDIT_SUBSCRIPTION", subList.get(position));
              arrayPosition = position;
              startActivityForResult(intent, 1);
          }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Subscription>(this,
                R.layout.list_item, subList);
        oldSubList.setAdapter(adapter);
    }


    /** Called when the user taps the ADD A NEW SUBSCRIPTION button*/
    public void addSubscription(View view){
        Intent intent = new Intent(this, DisplayNewSubscriptionActivity.class);
        intent.putExtra("EDIT_SUBSCRIPTION", new Subscription(this));
        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            Subscription sub = new Subscription(this);
            if (resultCode == Activity.RESULT_OK) {
                sub.setName(data.getStringExtra(SUB_NAME));
                sub.setComment(data.getStringExtra(SUB_COMMENT));
                sub.setCharge(data.getStringExtra(SUB_CHARGE));
                sub.setDate(data.getStringExtra(SUB_DATE));
                subList.add(sub);
                adapter.notifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        }
        else if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK) {
                Subscription sub = subList.get(arrayPosition);
                sub.setName(data.getStringExtra(SUB_NAME));
                sub.setComment(data.getStringExtra(SUB_COMMENT));
                sub.setCharge(data.getStringExtra(SUB_CHARGE));
                sub.setDate(data.getStringExtra(SUB_DATE));
                adapter.notifyDataSetChanged();
            }
        }
        saveInFile();
    }

    /**
     * Saves Subscriptions into file
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            for(Subscription item: subList){
                fos.write(item.toString().getBytes());
            }
            fos.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
        loadFromFile();
    }

    /**
     * Load subscription from file into ArrayList
     */
    private void loadFromFile() {
        subList.clear();
        Double tmc = 0.00;
        try {
            String line = "";
            FileInputStream fis = openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            int count = 0;
            Subscription reAdd = new Subscription(this);
            while ((line = br.readLine()) != null) {
                String[] token = line.split(": ", 2);
                if (token[0].equals("Name")){
                    reAdd.setName(token[1]);
                    count++;
                }
                else if (token[0].equals("Date Created")){
                    reAdd.setDate(token[1]);
                    count++;
                }
                else if (token[0].equals("Monthly Charge")){
                    reAdd.setCharge(token[1].substring(1));
                    tmc += Double.parseDouble(token[1].substring(1));
                    count++;
                }
                else if (token[0].equals("Comment")){
                    reAdd.setComment(token[1]);
                    count++;
                }
                if (count == 4){
                    subList.add(reAdd);
                    reAdd = new Subscription(this);
                    count = 0;
                }
            }
            fis.close();
            totalMonthlyCharge.setText("$" + String.format("%.2f", tmc));
        } catch (FileNotFoundException e) {
            subList = new ArrayList<Subscription>();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

}

