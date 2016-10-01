package com.cmput301.stephen.sdlarsen_habittracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by stephen on 2016-09-20.
 */

public abstract class Habit {

    private String title;
    private Date date;
    private Integer checkIns;
    private ArrayList<String> days;
    private ArrayList<Date> historyList;

    public Habit(String title, ArrayList<String> days) {
        this.title = title;
        this.date = new Date();
        this.checkIns = 0;
        this.days = days;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setDays(ArrayList days) {
        this.days = days;
    }

    public void setCheckIns(Integer checkIns) {
        this.checkIns = checkIns;
    }

    public Integer getCheckIns() {
        return checkIns;
    }

    public ArrayList getDays() {
        return days;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date theDate) {
        this.date = theDate;
    }

    @Override
    public String toString(){
        return title;
    }

    public void addToHistory() {
        historyList.add(new Date());
    }

    public abstract Boolean isComplete();

}
