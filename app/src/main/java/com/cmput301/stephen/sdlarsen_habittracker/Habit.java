package com.cmput301.stephen.sdlarsen_habittracker;

import java.util.Date;
import java.util.List;

/**
 * Created by stephen on 2016-09-20.
 */

public class Habit {

    private String title;
    private Integer checkIn;
    private Date date;
    private List days;

    public Habit(String title, List days) {
        this.title = title;
        this.date = new Date();
        this.checkIn = 0;
        this.days = days;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setCheckIn(Integer checkIn) {
        this.checkIn = checkIn;
    }

    public void setDays(List days) {
        this.days = days;
    }

    public List getDays() {

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
