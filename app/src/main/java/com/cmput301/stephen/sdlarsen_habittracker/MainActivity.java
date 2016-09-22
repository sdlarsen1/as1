package com.cmput301.stephen.sdlarsen_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ArrayList<Habit> habitList = new ArrayList<>();  // List of Habits

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button newHabitButton = (Button) findViewById(R.id.newHabit);
        Intent intent = new Intent(this, NewHabit.class);
        startActivity(intent);

    }
}
