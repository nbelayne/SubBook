package com.example.nbelayne.subbook;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by nbelayne on 2/3/18.
 */

public class Subscription implements Parcelable {
    private Context context;
    private String name;
    private String date;
    private String charge;
    private String comment;

    /**
     * Constructs a com.example.nbelayne.subbook.Subscription object.
     *
     * @param context
     */
    public Subscription(Context context) {
        this.context = context;

    }

    protected Subscription(Parcel in) {
        name = in.readString();
        date = in.readString();
        charge = in.readString();
        comment = in.readString();
    }

    public static final Creator<Subscription> CREATOR = new Creator<Subscription>() {
        @Override
        public Subscription createFromParcel(Parcel in) {
            return new Subscription(in);
        }

        @Override
        public Subscription[] newArray(int size) {
            return new Subscription[size];
        }
    };

    /**
     * Retrieve name of com.example.nbelayne.subbook.Subscription
     *
     * @return name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Sets com.example.nbelayne.subbook.Subscription name
     *
     * @param newName name of new com.example.nbelayne.subbook.Subscription
     */
    public void setName(String newName){
        if ((newName.length() <= 20) && (newName.length() > 0)){
            this.name = newName;
        }
        else if (newName.length() >= 20){
            Toast.makeText(this.context,
                    "Length of name must be less than 20 characters Please Try Again.",
                    Toast.LENGTH_SHORT).show();
        }
        else if (newName.length() <= 0){
            Toast.makeText(this.context,
                    "Length of name must be greater than 0 characters Please Try Again.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *  Retrieve the date that the com.example.nbelayne.subbook.Subscription was started
     *
     * @return date
     */
    public String getDate(){
        return this.date;
    }

    /**
     * Sets com.example.nbelayne.subbook.Subscription's start date
     *
     * @param newDate name of start date of new com.example.nbelayne.subbook.Subscription
     */
    public void setDate(String newDate){
        // check yyyy-mm-dd
        String year;
        int yearNum = 0;
        String month;
        int monthNum = 0;
        String day;
        int dayNum = 0;
        int cnt = 0;
        String[] token = newDate.split("-");
        for(String i : token){
            if(cnt == 0){
                year = i;
                yearNum = Integer.parseInt(year);
                cnt++;
            }
            else if (cnt == 1){
                month = i;
                monthNum = Integer.parseInt(month);
                cnt++;
            }
            else if (cnt == 2){
                day = i;
                dayNum = Integer.parseInt(day);
                cnt++;
            }
        }
        if(((yearNum >= 0) || (yearNum <= 2018)) && ((monthNum >= 1) ||
                (monthNum <= 12)) && ((dayNum >= 1) || (dayNum <= 31))){
            this.date = newDate;
        }
        else{
            Toast.makeText(this.context,
                    "Date must be in the form YYYY-MM-DD. Please Try Again.",
                    Toast.LENGTH_SHORT).show();
        }

    }

    /**
     *  Retrieve the monthly charge for the com.example.nbelayne.subbook.Subscription
     *
     * @return charge
     */
    public String getCharge(){
        return this.charge;
    }

    /**
     * Sets com.example.nbelayne.subbook.Subscription's monthly charge
     *
     * @param newCharge monthly charge of new com.example.nbelayne.subbook.Subscription
     */
    public void setCharge(String newCharge){
        // check if charge is correct . ie positive
        Double chargeNum;
        chargeNum = Double.parseDouble(newCharge);
        if (chargeNum >= 0.0){
            this.charge = newCharge;
        }
        else{
            Toast.makeText(this.context,
                    "Monthly charge must be a positive number. Please Try Again.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Retrieve comments for com.example.nbelayne.subbook.Subscription
     *
     * @return comment
     */
    public String getComment(){
        return this.comment;
    }

    /**
     * Sets comments for com.example.nbelayne.subbook.Subscription
     *
     * @param newComment new comments for com.example.nbelayne.subbook.Subscription
     */
    public void setComment(String newComment){
        // check if newcomment < 30 chars
        if (newComment.length() <= 20){
            this.comment = newComment;
        }
        else {
            // look at toast class
            Toast.makeText(this.context,
                    "Length of comment must be less than 30 characters. Please Try Again.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public String toString(){
        return "Name: " + this.name + "\n" + "Date Created: " + this.date + "\n" +
                "Monthly Charge: $" + this.charge + "\n" + "Comment: " + this.comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(date);
        dest.writeString(charge);
        dest.writeString(comment);
    }
}
