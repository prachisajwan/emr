package com.example.emr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import static android.database.sqlite.SQLiteDatabase.openDatabase;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "User.db";
    public static final String TABLE1 = "User_table";
    public static final String TABLE2 = "Records_table";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Surname";
    public static final String COL_3 = "ID";
    public static final String COL_4 = "Phone";
    public static final String COL_5 = "Email";
    public static final String COL_6 = "Password";
    public static final String COL_7 = "DOB";

    public static final String TAB_01 = "ID";
    public static final String TAB_02 = "Hospital_name";
    public static final String TAB_03 = "Test";
    public static final String TAB_04 = "Prescription";
    public static final String TAB_05 = "Result";
    public static final String TAB_06 = "Report";
    public static final String TAB_07 = "Bill";

    private Context context;
    private static final String TAG = "November db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE1 + "(NAME TEXT, SURNAME TEXT, ID INTEGER PRIMARY KEY, PHONE INTEGER UNIQUE," +
                "EMAIL TEXT UNIQUE, PASSWORD TEXT, DOB DATE)");
        db.execSQL("create table " + TABLE2 + "("+ TAB_01+ "  INTEGER, HOSPITAL_NAME TEXT, TEST TEXT, PRESCRIPTION TEXT," +
                "RESULT TEXT, REPORT TEXT, BILL TEXT)");
//        String s = "rame" + "sh";
//        StringBuilder b = new StringBuilder();
//        b.append("create table ").append(TABLE2).append("(").append(TAB_01);
//        db.execSQL(b.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE1);
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE2);
//        onCreate(db);

    }

    public boolean insertData(String name, String surname, String id, String email, String phone, String password, String dob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, surname);
        contentValues.put(COL_3, id);
        contentValues.put(COL_4, phone);
        contentValues.put(COL_5, email);
        contentValues.put(COL_6, password);
        contentValues.put(COL_7, dob);

        long result = db.insert(TABLE1, null, contentValues);
        db.close();
        if (result == -1)
            return false;
        else
            return true;
    }

//    private boolean checkDbExist() {
//        SQLiteDatabase sqLiteDatabase = null;
//
//        try {
//            String path = DATABASE_NAME;
//            sqLiteDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
//        } catch (Exception ex) {
//        }
//
//        if (sqLiteDatabase != null) {
//            sqLiteDatabase.close();
//            return true;
//        }
//
//        return false;
//    }
//
//    public String getSinlgeEntry(String userName) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
//        if (cursor.getCount() < 1) // UserName Not Exist
//        {
//            cursor.close();
//            return "NOT EXIST";
//        }
//        cursor.moveToFirst();
//        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
//        cursor.close();
//        return password;
//    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE1, null);
        if (res.moveToFirst())
            Log.d(TAG, DatabaseUtils.dumpCursorToString(res));
        return res;
    }

//    public Boolean checkusername(String id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select * from User_table where ID = ?", new String[]{id});
//        if (cursor.getCount() > 0) {
//            return true;
//        } else {
//            return false;
//        }
//
//    }

    public Boolean checkcredentials(String email, String id, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  Log.d("Mail: ", email+"");
        //Log.d("PSWD: ", password+"");
        //String sql="select * from User_table where username =? and password =?";
        Cursor cursor = db.rawQuery("select * from " + TABLE1 + " where Email =? and Id =? and Password =?",
                new String[]{email, id, password});
//        Log.d("Cursor value: ", cursor.getCount()+"");
//        Log.d("Cursor value: ", cursor.getString(1)+"");
        if (cursor.getCount() > 0) {
            Log.d(TAG, "db 1st if");
            return true;
        } else {
            Log.d(TAG, "db 1st else");
            return false;
        }
    }
    public Boolean checkaccess(String name, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //  Log.d("Mail: ", email+"");
        //Log.d("PSWD: ", password+"");
        //String sql="select * from User_table where username =? and password =?";
        Cursor cursor = db.rawQuery("select * from " + TABLE1 + " where Name =? and ID =?",
                new String[]{name, id});
//        Log.d("Cursor value: ", cursor.getCount()+"");
//        Log.d("Cursor value: ", cursor.getString(1)+"");
        if (cursor.getCount() > 0) {
            //Log.d(TAG, "db 1st if");
            return true;
        } else {
            // Log.d(TAG, "db 1st else");
            return false;
        }
    }

    public boolean insertRecords(String id, String hospital_name, String test, String prescription,
                                 String result, String report, String bill) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content_values = new ContentValues();
        content_values.put(TAB_01, id);
        content_values.put(TAB_02, hospital_name);
        content_values.put(TAB_03, test);
        content_values.put(TAB_04, prescription);
        content_values.put(TAB_05, result);
        content_values.put(TAB_06, report);
        content_values.put(TAB_07, bill);

        long res = -1;
        try
        {
            Log.d(TAG, "content_values: "+ content_values);
            try {
                res = db.insertOrThrow(TABLE2, null, content_values);
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }

            Log.d(TAG, "insertRecords value: "+ res);
            db.close();
            if (res == -1)
                return false;
            else
                return true;
        }
        catch (Exception exception)
        {
            Log.d(TAG, "Insert: "+ exception.getMessage());
            return false;
        }
//        Log.d(TAG, "Content: " +content_values.toString());
//        Log.d(TAG, "ID= " +id + " Hospital name=" + hospital_name);

//        if (res == -1)
//            return false;
//        else
//            return true;
    }

    public Cursor getAllRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE2, null);
        if (res.moveToFirst())
            Log.d(TAG, DatabaseUtils.dumpCursorToString(res));
        return res;
    }

//    public ArrayList getRecords() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<String> array_list = new ArrayList<String>();
//        Cursor res = db.rawQuery( "select (id ||' : '||hospital_name || ' : ' || test || ' : '|| prescription || ' : '|| result) AS fullname from "+TABLE2, null );
//        res.moveToFirst();
//        while(res.isAfterLast() == false)
//        {
//            array_list.add(res.getString(res.getColumnIndex("fullname")));
//            res.moveToNext();
//        }
//        return array_list;
//    }
//
//    public boolean update(String s, String s1) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("UPDATE "+TABLE2+" SET test = "+"'"+s+"', "+ "result = "+"'"+s1+"'");
//        return true;
//    }
//    public boolean delete() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DELETE from "+TABLE2);
//        return true;
//    }

    public ArrayList<String> getFullData(String id)
    {
        ArrayList<String> llist= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery( "select id || ' \n Hospital Name:  ' || hospital_name || '\n Test Done:  '" +
                "|| test || '\n Prescription:  '|| prescription || '\n Result:  '|| result AS Records from "
                +TABLE2+ " where id=?", new String[]{id});
        Log.d(TAG, cursor.getCount()+"");
     //   Log.d(TAG, cursor.getString(1)+"");
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
        llist.add(cursor.getString(cursor.getColumnIndex("Records")));
        cursor.moveToNext();
        }
        Log.d(TAG, llist+"");
        return llist;
    }

    public ArrayList<String> getFullDataUser(String id)
    {
        ArrayList<String> llist= new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery( "select id || ' \n Hospital Name:  ' || hospital_name || '\n Test Done:  ' ||" +
                "test || '\n Prescription:  '|| prescription || '\n Result:  '|| result  || '\n Report:  ' || report " +
                "|| '\n Bill:  ' || bill AS Records from "+TABLE2+ " where id=?", new String[]{id});
        Log.d(TAG, cursor.getCount()+"");
        //   Log.d(TAG, cursor.getString(1)+"");
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            llist.add(cursor.getString(cursor.getColumnIndex("Records")));
            cursor.moveToNext();
        }
        Log.d(TAG, llist+"");
        return llist;
    }

//    public boolean updateData(String id, String hospital_name,String test,String prescription,String result) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues content_values = new ContentValues();
//        content_values.put(TAB_02, hospital_name);
//        content_values.put(TAB_03, test);
//        content_values.put(TAB_04, prescription);
//        content_values.put(TAB_05, result);
//        db.update(TABLE2, content_values, "ID = ?",new String[] { id });
//        return true;
//    }

    public void updateRecord(String id, String hospital_name,String test,String prescription,String result) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(TAB_02, hospital_name);
        values.put(TAB_03, test);
        values.put(TAB_04, prescription);
        values.put(TAB_05, result);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        long res = db.update(TABLE2, values, "name=?", new String[]{id});
        if(res == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}