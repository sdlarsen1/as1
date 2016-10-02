package com.cmput301.stephen.sdlarsen_habittracker;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.cmput301.stephen.sdlarsen_habittracker.R.id.days_view;


public class NewHabit extends AppCompatActivity implements  ChooseDaysDialogFragment.onSaveListener {

    private static final String FILENAME = "habits.sav";
    private EditText editMessage;
    private ArrayList<Habit> habitList;
    private ArrayList<String> daysList = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);


        editMessage = (EditText) findViewById(R.id.edit_message);  // Habit name
        Button saveButton = (Button) findViewById(R.id.saveButton);    // Save button
        Button cancelButton = (Button) findViewById(R.id.cancelButton);  // Cancel Button
        TextView daysView = (TextView) findViewById(days_view);  // Initialize view of selected days

        /* On pressing save... */
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                String text = editMessage.getText().toString();
                Habit newHabit = new IncompleteHabit(text, daysList);

                loadFromFile();
                if (habitList == null){
                    habitList = new ArrayList<Habit>();
                } else {
                    habitList.add(newHabit);
                }

                saveInFile();

                Intent intent = new Intent(NewHabit.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }

        });

        /* On pressing cancel... */
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NewHabit.this, MainActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        /* On choosing days... */
        daysView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showChooseDaysDialog();
            }
        });

    }

    public void showChooseDaysDialog() {
        DialogFragment dialog = new ChooseDaysDialogFragment();
        dialog.show(getSupportFragmentManager(), "ChooseDays");
    }


    @Override
    public void onSaveClick(ArrayList selectedDays) {
        daysList = selectedDays;   // need to make sure it returns proper strings
    }


    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // on September 22, 2016
            Type listType = new TypeToken<ArrayList<IncompleteHabit>>(){}.getType();
//            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            habitList = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            habitList = new ArrayList<Habit>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(habitList, writer);
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
