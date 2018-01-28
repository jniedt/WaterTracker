package com.example.android.watertracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.watertracker.data.WaterContract.WaterEntry;
import com.example.android.watertracker.data.WaterDbHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class WaterActivity extends AppCompatActivity {

    private WaterDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        mDbHelper = new WaterDbHelper(this);

        displayDatabaseInfo();
    }


    @Override
    protected void onStart() {
        super.onStart();

        String drinkName = "Coca-Cola";
        int diureticType = 1;
        int drinkOunces = 8;
        String drinkmeasure = " ounces";

        // Create database helper
        WaterDbHelper mDbHelper = new WaterDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(WaterEntry.COLUMN_DRINK_NAME, drinkName);
        values.put(WaterEntry.COLUMN_DIURETIC_TYPE, diureticType);
        values.put(WaterEntry.COLUMN_DRINK_OUNCES, drinkOunces + drinkmeasure);
        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(WaterEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving intake", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Intake saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }

        displayDatabaseInfo();
    }
    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.

        String[] projection = {
                WaterEntry._ID,
                WaterEntry.COLUMN_DRINK_NAME,
                WaterEntry.COLUMN_DIURETIC_TYPE,
                WaterEntry.COLUMN_DRINK_OUNCES
        };

        Cursor cursor = db.query(
                WaterEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.water_view);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The pets table contains " + cursor.getCount() + " pets.\n\n");
            displayView.append(WaterEntry._ID + " - " +
                    WaterEntry.COLUMN_DRINK_NAME + " - " +
                    WaterEntry.COLUMN_DIURETIC_TYPE + " - " +
                    WaterEntry.COLUMN_DRINK_OUNCES + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(WaterEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(WaterEntry.COLUMN_DRINK_NAME);
            int typeColumnIndex = cursor.getColumnIndex(WaterEntry.COLUMN_DIURETIC_TYPE);
            int ouncesColumnIndex = cursor.getColumnIndex(WaterEntry.COLUMN_DRINK_OUNCES);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentType = cursor.getString(typeColumnIndex);
                int currentOunces = cursor.getInt(ouncesColumnIndex);
                String drinkMeasure =" oz.";
                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentType + " - " +
                        currentOunces + drinkMeasure));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }

    }


}
