package com.example.android.habit_tracker.data;

import android.provider.BaseColumns;

public final class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {
    }

    public static final class BookHabit implements BaseColumns {

        /**
         * Name of database table for habit
         */
        public final static String TABLE_NAME = "bookReadingHabit";

        /**
         * Unique ID number for the book (only for use in the database table).
         * Type: INTEGER
         */

        public final static String Book_ID = BaseColumns._ID;

        /**
         * Title of the book.
         * Type: TEXT
         */

        public final static String COLUMN_BOOK_TITLE = "title";

        /**
         * Author of the book.
         * Type: TEXT
         */

        public final static String COLUMN_BOOK_AUTHOR = "author";

        /**
         * Type of the book.
         * Type: INTEGER
         */

        public final static String COLUMN_BOOK_TYPE = "type";

        /**
         * Possible values for the types of books.
         */

        public static final int TYPE_NOVEL = 0;

    }

}
