package com.example.farmings_schedular;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Remainders";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    private static final String TABLE_NAME = "remainder";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TASK = "task";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";                                                   //Table  name to store reminders in sqllite

    // SQL query to create the table
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TASK + " TEXT NOT NULL, " +
                    COLUMN_DATE + " TEXT NOT NULL, " +
                    COLUMN_TIME + " TEXT NOT NULL)";

    public dbManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table when the database is created
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);


    }

    public String addreminder(String task, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TASK, task);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);

        long result = db.insert(TABLE_NAME, null, values);

        if (result == -1) {
            return "Failed";
        } else {
            return "Successfully inserted";
        }

    }

    public Cursor getAllTasks() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;

    }
    public String updateReminders(String id,String task , String date,String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK, task);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);

        long result =db.update(TABLE_NAME,values,COLUMN_ID+" = "+id,null);
        if (result == -1) {
            return "Failed";
        } else {
            return "Successfully Updated";
        }
    }
    public void delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COLUMN_ID+" = ? ", new String[]{id});
    }

}