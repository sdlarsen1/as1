package com.cmput301.stephen.sdlarsen_habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.Date;

public class NewHabit extends AppCompatActivity {

    private static final String FILENAME = "habits.sav";
    private EditText editMessage;
    private ListView oldHabitsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);

        Intent intent = getIntent();

        editMessage = (EditText) findViewById(R.id.edit_message);  // habit name
        Button saveButton = (Button) findViewById(R.id.save);    // save button

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

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sunday: {
                    // do something for button 1 click
                    break;
                }

                case R.id.monday: {
                    //
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
