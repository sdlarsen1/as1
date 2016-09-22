package com.cmput301.stephen.sdlarsen_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Set;

public class NewHabit extends AppCompatActivity {

    private static final String FILENAME = "habits.sav";
    private EditText editMessage;
    private ListView oldHabitsList;
    private Set daysSet;  // Not sur eif this goes here.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);


        editMessage = (EditText) findViewById(R.id.edit_message);  // Habit name

        Button saveButton = (Button) findViewById(R.id.save);    // Save button
        saveButton.setOnClickListener(this);

        /* Days of the week buttons */
        Button sundayButton = (Button) findViewById(R.id.sunday);
        sundayButton.setOnClickListener(this);
        Button mondayButton = (Button) findViewById(R.id.monday);
        mondayButton.setOnClickListener(this);
        Button tuesdayButton = (Button) findViewById(R.id.tuesday);
        tuesdayButton.setOnClickListener(this);
        Button wednesdayButton = (Button) findViewById(R.id.wednesday);
        wednesdayButton.setOnClickListener(this);
        Button thursdayButton = (Button) findViewById(R.id.thursday);
        thursdayButton.setOnClickListener(this);
        Button fridayButton = (Button) findViewById(R.id.friday);
        fridayButton.setOnClickListener(this);
        Button saturdayButton = (Button) findViewById(R.id.saturday);
        saturdayButton.setOnClickListener(this);


        /* Button Control */
        // Keep getting errors "constant expression required," issues with View.
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.save: {
                    //Save habit
                    setResult(RESULT_OK);
                    String text = editMessage.getText().toString();

                    Habit newHabit = new Habit(text, daysSet);
                    habitList.add(newHabit);  // find way to make this global --> public in main

                    // Save infile??

                    Intent intent = new Intent(this, MainActivity.class);  // Go back to main page
                }

                case R.id.sunday: {
                    daysSet.add("Sunday");
                    break;
                }

                case R.id.monday: {
                    daysSet.add("Monday");
                    break;
                }

                case R.id.tuesday: {
                    daysSet.add("Tuesday");
                    break;
                }

                case R.id.wednesday: {
                    daysSet.add("Wednesday");
                    break;
                }

                case R.id.thursday: {
                    daysSet.add("Thursday");
                    break;
                }

                case R.id.friday: {
                    daysSet.add("Friday");
                    break;
                }

                case R.id.saturday: {
                    daysSet.add("Saturday");
                    break;
                }
            }
        }



        /* On pressing save... */
        /*saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = editMessage.getText().toString();

                //Date theDate = new Date();
                Habit newHabit = new Habit(text, days);

            }

        });*/

    }
}
