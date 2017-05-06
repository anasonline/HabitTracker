package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by anas on 06.05.17.
 */

public final class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private HabitContract() {
    }

     /* Inner class that defines the table and its contents */

    public static class HabitEntry implements BaseColumns {
        public static final String TABLE_NAME = "habits";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_DURATION = "duration";
    }
}