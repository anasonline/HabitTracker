package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.habittracker.data.HabitContract.HabitEntry;
import com.example.android.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    // Create a DB helper object
    HabitDbHelper mDbHelper = new HabitDbHelper(this);
    // A string variable to store a habit's name i.e: walking, playing guitar, ... etc
    private String mHabitName;
    // A integer variable to store duration: i.e: 1 hour.
    private int mHabitDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insert() {
        // Create a writable SQLite database object
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object to store values into columns
        ContentValues values = new ContentValues();

        values.put(HabitEntry.COLUMN_HABIT_NAME, mHabitName);
        values.put(HabitEntry.COLUMN_HABIT_DURATION, mHabitDuration);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
    }

    public void read() {

        // Create a readable SQLite database object
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {HabitEntry._ID, HabitEntry.COLUMN_HABIT_NAME, HabitEntry.COLUMN_HABIT_DURATION};

        Cursor cursor = db.query(HabitEntry.TABLE_NAME, projection, null, null, null,
                null, null);

        try {

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
            int habitNameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int habitDurationColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DURATION);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(habitNameColumnIndex);
                int currentDuration = cursor.getInt(habitDurationColumnIndex);
            }

        } finally {
            cursor.close();
        }
    }
}