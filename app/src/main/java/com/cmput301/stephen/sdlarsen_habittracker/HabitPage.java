package com.cmput301.stephen.sdlarsen_habittracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HabitPage extends AppCompatActivity {

    private Habit displayHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_page);

        displayHabit = (Habit) getIntent().getSerializableExtra("habit"); // retrieve habit


        TextView habitName = (TextView) findViewById(R.id.habit_name);
        TextView habitDate = (TextView) findViewById(R.id.created_on);
        ListView history = (ListView) findViewById(R.id.oldHabits);

        Button completedButton = (Button) findViewById(R.id.completedButton);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);

        habitName.setText(displayHabit.getTitle());
        //habitDate.setText((String) displayHabit.getDate());

        /* On completing habit... */
        completedButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO
            }
        });

        /* On clearing history... */
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO
            }
        });

        /* On deleting habit... */
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO
            }
        });

    }
}
