package com.example.android.watertracker.data;

import android.provider.BaseColumns;

/**
 * Created by Ch on 1/28/2018.
 */
public final class WaterContract {

    private WaterContract() {}

    public static final class WaterEntry implements BaseColumns {
        public final static String TABLE_NAME = "water";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_DRINK_NAME = "name";
        public final static String COLUMN_DIURETIC_TYPE = "diuretic";
        public final static String COLUMN_DRINK_OUNCES = "amount";

        public static final int NONE = 0;
        public static final int CAFFEINE = 1;
        public static final int ALCOHOL = 2;
    }
}
