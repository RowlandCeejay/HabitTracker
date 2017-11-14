package com.example.android.habit_tracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.habit_tracker.data.HabitContract.BookHabit;
import com.example.android.habit_tracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * Database helper that will provide us access to the database
     */
    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new HabitDbHelper(this);

        insertHabit();
    }

    private Cursor myCursor() {
        // Create and/or open a database to read from it
        SQLiteDatabase myData = mDbHelper.getReadableDatabase();
        String[] projection = {
                BookHabit.Book_ID,
                BookHabit.COLUMN_BOOK_TITLE,
                BookHabit.COLUMN_BOOK_AUTHOR,
                BookHabit.COLUMN_BOOK_TYPE};

        return myData.query(
                BookHabit.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
    }

    private void read() {
        Cursor cursor = myCursor();

        try {
            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(BookHabit.Book_ID);
            int titleColumnIndex = cursor.getColumnIndex(BookHabit.COLUMN_BOOK_TITLE);
            int authorColumnIndex = cursor.getColumnIndex(BookHabit.COLUMN_BOOK_AUTHOR);
            int typeColumnIndex = cursor.getColumnIndex(BookHabit.COLUMN_BOOK_TYPE);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int presentBookID = cursor.getInt(idColumnIndex);
                String presentBookTitle = cursor.getString(titleColumnIndex);
                String presentBookAuthor = cursor.getString(authorColumnIndex);
                int presentBookType = cursor.getInt(typeColumnIndex);

                Log.d(LOG_TAG, presentBookID + " " + presentBookTitle + " " + presentBookAuthor + " " + presentBookType);
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded book data into the database.
     */
    private void insertHabit() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Treasure Island book attributes are the values.
        ContentValues values = new ContentValues();
        values.put(BookHabit.COLUMN_BOOK_TITLE, "Treasure Island");
        values.put(BookHabit.COLUMN_BOOK_AUTHOR, "Robert Louis Stevenson");
        values.put(BookHabit.COLUMN_BOOK_TYPE, BookHabit.TYPE_NOVEL);

        // The third argument is the ContentValues object containing the info for Treasure Island.
        long newRowId = db.insert(BookHabit.TABLE_NAME, null, values);
        Log.v("MainActivity", "New row ID" + newRowId);
        read();
    }

}
