package com.cmput301.stephen.sdlarsen_habittracker;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import static com.cmput301.stephen.sdlarsen_habittracker.R.id.days_view;


public class NewHabit extends AppCompatActivity implements  ChooseDaysDialogFragment.onSaveListener {

    private static final String FILENAME = "habits.sav";
    private EditText editMessage;
    private ArrayList<Habit> newHabitList;
    private ArrayList<String> daysList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);

        newHabitList = (ArrayList<Habit>) getIntent().getSerializableExtra("habitList"); // retrieve habitList

        editMessage = (EditText) findViewById(R.id.edit_message);  // Habit name

        Button saveButton = (Button) findViewById(R.id.saveButton);    // Save button
        Button cancelButton = (Button) findViewById(R.id.cancelButton);  // Cancel Button
        TextView daysViewButton = (TextView) findViewById(days_view);  // Initialize view of selected days

        /* On pressing save... */
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = editMessage.getText().toString();

                Habit newHabit = new Habit(text, daysList);
                newHabitList.add(newHabit);

                //Save in file
                saveInFile();

                Intent intent = new Intent(NewHabit.this, MainActivity.class);
                //intent.putExtra("newHabitList", newHabitList);
                startActivity(intent);

            }

        });

        /* On pressing cancel... */
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NewHabit.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /* On choosing days... */
        daysViewButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO bring up dialog
                showChooseDaysDialog();
            }
        });

        /**
         * this breaks the page..?
         */
        /*if (daysList.isEmpty()) {
            daysViewButton.setText("empty");
        } else {
            for (int y = 0; y <= daysList.size()-1; y++){
                daysViewButton.setText(daysList.get(y));
            }
        }*/

    }

    public void showChooseDaysDialog() {
        DialogFragment dialog = new ChooseDaysDialogFragment();
        dialog.show(getSupportFragmentManager(), "ChooseDays");
    }


    @Override
    public void onSaveClick(ArrayList selectedDays) {
        daysList = selectedDays;   // need to make sure it returns proper strings
    }


    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(newHabitList, writer);
            writer.flush();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

}
