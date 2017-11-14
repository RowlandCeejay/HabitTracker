package com.example.android.habit_tracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.habit_tracker.data.HabitContract.BookHabit;


public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String LOG_TAG = HabitDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "habitsTracker.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabitDbHelper}.
     *
     * @param context of the app
     */
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_HABITS_TABLE = "CREATE TABLE " + BookHabit.TABLE_NAME + " ("
                + BookHabit.Book_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookHabit.COLUMN_BOOK_TITLE + " TEXT NOT NULL, "
                + BookHabit.COLUMN_BOOK_AUTHOR + " TEXT, "
                + BookHabit.COLUMN_BOOK_TYPE + " INTEGER NOT NULL DEFAULT 0);";
        Log.v(LOG_TAG, SQL_CREATE_HABITS_TABLE);
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_HABITS_TABLE);

    }

    /**
     * This is called when the database needs to be upgraded.
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
