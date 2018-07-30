package com.prakashpweb.otpsync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SampleSQLiteDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "otpsync";
    public static final String OTPSYNC_TABLE_NAME = "otpsync";
    public static final String OTPSYNC_COLUMN_IPADDRESS= "ipaddress";
    public static final String OTPSYNC_COLUMN_PORTNO = "portno";
    public static final String OTPSYNC_COLUMN_DBUSERNAME = "dbusername";
    public static final String OTPSYNC_COLUMN_DBPASSWORD = "dbpassword";

    public SampleSQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + OTPSYNC_TABLE_NAME + " (" +
                OTPSYNC_COLUMN_IPADDRESS + " TEXT, " +
                OTPSYNC_COLUMN_PORTNO + " INT, " +
                OTPSYNC_COLUMN_DBUSERNAME + " TEXT, " +
                OTPSYNC_COLUMN_DBPASSWORD + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OTPSYNC_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
