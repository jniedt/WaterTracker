package com.example.android.watertracker.data;

/**
 * Created by Ch on 1/28/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.watertracker.data.WaterContract.WaterEntry;

/**
 * Database helper for Pets app. Manages database creation and version management.
 */
public class WaterDbHelper extends SQLiteOpenHelper {


    /** Name of the database file */
    private static final String DATABASE_NAME = "water.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link WaterDbHelper}.
     *
     * @param context of the app
     */
    public WaterDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    //database variable can be shortened for efficiency "db"
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + WaterEntry.TABLE_NAME + " ("
                + WaterEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WaterEntry.COLUMN_DRINK_NAME + " TEXT NOT NULL, "
                + WaterEntry.COLUMN_DIURETIC_TYPE + " INTEGER NOT NULL);"
                + WaterEntry.COLUMN_DRINK_OUNCES + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        //Pass in command above
        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}