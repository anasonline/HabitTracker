package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    // A string variable to store a habit's name i.e: walking, playing guitar, ... etc
    private String mHabitName;

    // A integer variable to store duration: i.e: 1 hour.
    private int mHabitDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHabitName = "running";
        mHabitDuration = 2;

        insert();

    }

    public void insert() {

        // Create a DB helper object
        HabitDbHelper mDbHelper = new HabitDbHelper(this);

        // Create a writable SQLite database object
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object to store values into columns
        ContentValues values = new ContentValues();

        values.put(HabitEntry.COLUMN_HABIT_NAME, mHabitName);
        values.put(HabitEntry.COLUMN_HABIT_DURATION, mHabitDuration);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error with saving Habit", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Habit saved with id: " + newRowId, Toast.LENGTH_LONG).show();
        }

    }

}
