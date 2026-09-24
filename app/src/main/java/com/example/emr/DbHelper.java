package com.example.emr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE2_NAME = "Records_table";
    public static final String COL_01 = "ID";
    public static final String COL_02 = "Hospital_name";
    public static final String COL_03 = "Test";
    public static final String COL_04 = "Prescripton";
    public static final String COL_05 = "Result";
    public static final String COL_06 = "Report";
    public static final String COL_07 = "Bill";
    private Context context;
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +TABLE2_NAME+ "(ID TEXT, HOSPITAL_NAME TEXT, TEST TEXT, PRESCRIPTION TEXT, RESULT TEXT, REPORT TEXT, BILL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE2_NAME);
        onCreate(db);
    }

    public boolean insertRecords(String id, String hospital_name, String test, String prescription, String result, String report, String bill)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_01, id);
        contentValues.put(COL_02, hospital_name);
        contentValues.put(COL_03, test);
        contentValues.put(COL_04, prescription);
        contentValues.put(COL_05, result);
        contentValues.put(COL_06, report);
        contentValues.put(COL_07, bill);

        long res= db.insert(TABLE2_NAME, null, contentValues);
        if(res== -1)
            return false;
        else
            return true;
    }

    public Cursor getAllRecords()
    {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE2_NAME, null);
        if (res.moveToFirst())
            Log.v("INTEREST CURSOR ", DatabaseUtils.dumpCursorToString(res));
        return res;
    }
}
