package com.cmput301.stephen.sdlarsen_habittracker;

import java.util.ArrayList;

/**
 * Created by stephen on 2016-10-01.
 */

public class IncompleteHabit extends Habit {

    public IncompleteHabit(String title, ArrayList<String> days) {
        super(title, days);
    }

    public Boolean isComplete() {
        return Boolean.FALSE;
    }
}
