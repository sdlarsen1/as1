package com.cmput301.stephen.sdlarsen_habittracker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    private ArrayList<String> historyList;

    public Habit(String title, ArrayList<String> days) {
        this.title = title;
        this.date = new Date();
        this.checkIns = 0;
        this.days = days;
        this.historyList = new ArrayList<>(); // eliminated the if/else in addToHistory
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

    public String getDay(int index) {
        return days.get(index);
    }
    public ArrayList getDaysList() {
        return days;
    }

    public int sizeOfDays() {
        return days.size();
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
        Date theDate = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = df.format(theDate);
        historyList.add(dateString);
    }

    public ArrayList getHistory() {
        return historyList;
    }

    public abstract Boolean isComplete();

}
