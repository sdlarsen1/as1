package com.cmput301.stephen.sdlarsen_habittracker;

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

        editMessage = (EditText) findViewById(R.id.edit_message);
        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = editMessage.getText().toString();

                //Date theDate = new Date();
                Habit newHabit = new Habit(text, days);

            }

        });

        }
    }
}
