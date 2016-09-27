package com.cmput301.stephen.sdlarsen_habittracker;

import android.app.LauncherActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by stephen on 2016-09-20.
 */

public class Habit {

    private String title;
    private Integer checkIn;
    private Date date;
    private ArrayList days;

    public Habit(String title, ArrayList days) {
        this.title = title;
        this.date = new Date();
        this.checkIn = 0;
        this.days = days;
    }

    public Habit(String title) {
        this.title = title;
        this.date = new Date();
        this.checkIn = 0;
        this.days.add("");
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCheckIn(Integer checkIn) {
        this.checkIn = checkIn;
    }

    public void setDays(ArrayList days) {
        this.days = days;
    }

    public ArrayList getDays() {
        return days;
    }

    public String getTitle() {
        return title;
    }

    public Integer getCheckIn() {
        return checkIn;
    }

    public Date getDate() {
        return date;
    }
}
