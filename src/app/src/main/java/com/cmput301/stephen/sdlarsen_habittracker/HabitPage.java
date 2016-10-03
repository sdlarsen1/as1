package com.cmput301.stephen.sdlarsen_habittracker;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ArrayAdapter;
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
    private ArrayAdapter<Habit> habitAdapter;
    private ListView historyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_page);

        TextView habitName = (TextView) findViewById(R.id.habit_name);
        TextView habitDate = (TextView) findViewById(R.id.created_on);
        TextView habitDays = (TextView) findViewById(R.id.repeat_on);
        TextView habitComplete = (TextView) findViewById(R.id.complete);
        historyView = (ListView) findViewById(R.id.habit_history);

        Button completedButton = (Button) findViewById(R.id.completedButton);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);


        loadFromFile();

        int index = (int) getIntent().getSerializableExtra("index"); // retrieve habit index
        displayHabit = habitList.get(index);


        habitName.setText(displayHabit.getTitle());  // show title

        /**
         * Code taken from http://stackoverflow.com/questions/15541266/displaying-static-and-dynamic-data-in-textview-in-android
         * on Sept. 29, 2016. Written by Chirag Raval.
         */
        final String dateString = formatDate();
        SpannableString spanDate = new SpannableString(dateString);
        spanDate.setSpan(new StyleSpan(Typeface.BOLD), 0, spanDate.length(), 0);
        habitDate.append(" ");
        habitDate.append(spanDate);

        displayDays(habitDays);

        String checkInString = Integer.toString(displayHabit.getCheckIns());
        SpannableString spanCheckIn = new SpannableString(checkInString);
        habitComplete.append(" ");
        habitComplete.append(spanCheckIn);
        habitComplete.append(" time(s).");


        /* On completing habit... */
        completedButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (displayHabit.isComplete()) {
                    // TODO
                    displayHabit.addToHistory();
                    displayHabit.setCheckIns((displayHabit.getCheckIns()+1));

                    habitAdapter.notifyDataSetChanged();
                    saveInFile();
                    recreate();

                } else {
                    Habit temp = makeNewCompleted(displayHabit);   // Make new CompletedHabit
                    habitList.remove(displayHabit);
                    displayHabit = temp;
                    habitList.add(displayHabit);

                    displayHabit.setCheckIns(displayHabit.getCheckIns()+1);
                    displayHabit.addToHistory();

                    habitAdapter.notifyDataSetChanged();
                    saveInFile();
                    recreate();
                }
            }
        });

        /* On clearing history... */
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                displayHabit.clearHistory();
                habitAdapter.notifyDataSetChanged();
                saveInFile();
            }
        });

        /* On deleting habit... */
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                habitList.remove(displayHabit);
                saveInFile();
                Intent intent = new Intent(HabitPage.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        habitAdapter = new ArrayAdapter<Habit>(this,
                R.layout.list_item, displayHabit.getHistory());
        historyView.setAdapter(habitAdapter);
        //habitAdapter.notifyDataSetChanged();
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // on September 22, 2016
            Type listType = new TypeToken<ArrayList<IncompleteHabit>>(){}.getType();
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

    public Habit makeNewCompleted(Habit notComplete) {
        Habit completedHabit = new CompletedHabit(notComplete.getTitle(),notComplete.getDaysList());
        completedHabit.setDate(notComplete.getDate());
        return completedHabit;
    }

    public void displayDays(TextView habitDays) {
        for (int y=0; y < displayHabit.sizeOfDays(); y++) {
            String day = displayHabit.getDay(y);
            SpannableString spanDay = new SpannableString(day);
            habitDays.append(" ");
            habitDays.append(spanDay);
            habitDays.append(" ");
        }
    }
}
