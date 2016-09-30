package com.cmput301.stephen.sdlarsen_habittracker;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HabitPage extends AppCompatActivity {

    private static final String FILENAME = "habits.sav";
    private Habit displayHabit;
    private ArrayList<Habit> habitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_page);

        loadFromFile();
        int index = (int) getIntent().getSerializableExtra("index"); // retrieve habit index
        displayHabit = habitList.get(index);  // retrieve habit


        TextView habitName = (TextView) findViewById(R.id.habit_name);
        TextView habitDate = (TextView) findViewById(R.id.created_on);
        TextView habitComplete = (TextView) findViewById(R.id.complete);
        ListView historyView = (ListView) findViewById(R.id.oldHabits);

        Button completedButton = (Button) findViewById(R.id.completedButton);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);

        habitName.setText(displayHabit.getTitle());  // show title

        /**
         * Code taken from http://stackoverflow.com/questions/15541266/displaying-static-and-dynamic-data-in-textview-in-android
         * on Sept. 29, 2016. Written by Chirag Raval.
         */
        String dateString = formatDate();
        SpannableString spanDate = new SpannableString(dateString);
        spanDate.setSpan(new StyleSpan(Typeface.BOLD), 0, spanDate.length(), 0);
        habitDate.append(" ");
        habitDate.append(spanDate);

        String checkInString = Integer.toString(displayHabit.getCheckIns());
        SpannableString spanCheckIn = new SpannableString(checkInString);
        habitComplete.append(" ");
        habitComplete.append(spanCheckIn);
        habitComplete.append(" time(s).");


        /* On completing habit... */
        completedButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO
                int checkIns = displayHabit.getCheckIns();
                displayHabit.setCheckIns(checkIns+1);
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
                habitList.remove(displayHabit);
                saveInFile();
                Intent intent = new Intent(HabitPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // on September 22, 2016
            Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
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

    public String formatDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date theDate = displayHabit.getDate();
        String dateString = df.format(theDate);
        return dateString;
    }
}
