package com.cmput301.stephen.sdlarsen_habittracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by stephen on 2016-09-29.
 */

public class CompletedHabit extends Habit {

    public CompletedHabit(String title, ArrayList<String> days) {
        super(title, days);
    }

    @Override
    public Boolean isComplete() {
        return Boolean.TRUE;
    }

}
