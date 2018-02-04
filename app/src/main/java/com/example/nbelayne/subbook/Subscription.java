import android.app.Notification;

import java.util.Date;

/**
 * Created by nbelayne on 2/3/18.
 */

public class Subscription {
    private String name;
    private Date date;
    private double charge;
    private String comment;

    /**
     * Constructs a Subscription object.
     *
     * @param name Subscription message
     * @param date date when Subscription was started
     * @param charge monthly charge of Subscription
     * @param comment comments about the Subscription
     */
    Subscription(String name, Date date, double charge, String comment){
        this.name = name;
        this.date = date;
        this.charge = charge;
        this.comment = comment;
    }

    /**
     * Retrieve name of Subscription
     *
     * @return name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Sets Subscription name
     *
     * @param newName name of new Subscription
     * @throws InvalidEntryException the Subscription name is over 20 characters
     */
    public void setName(String newName){
        if (newName.length() <= 20){
            this.name = newName;
        }
        else{
            //throw new InvalidEntryException();
        }
    }

    /**
     *  Retrieve the date that the Subscription was started
     *
     * @return date
     */
    public Date getDate(){
        return this.date;
    }

    /**
     * Sets Subscription's start date
     *
     * @param newDate name of start date of new Subscription
     */
    public void setDate(Date newDate){
        this.date = date;
    }

    /**
     *  Retrieve the monthly charge for the Subscription
     *
     * @return charge
     */
    public double getCharge(){
        return this.charge;
    }

    /**
     * Sets Subscription's monthly charge
     *
     * @param newCharge monthly charge of new Subscription
     */
    public void setCharge(double newCharge){
        this.charge = newCharge;
    }

    /**
     * Retrieve comments for Subscription
     *
     * @return comment
     */
    public String getComment(){
        return this.comment;
    }

    /**
     * Sets comments for Subscription
     *
     * @param newComment new comments for Subscription
     */
    public void setComment(String newComment){
            this.comment = newComment;
    }
}
