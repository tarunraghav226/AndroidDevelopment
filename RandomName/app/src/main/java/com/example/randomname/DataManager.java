package com.example.randomname;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataManager {

    public final String TABLE_ROW_ID = "_id";
    public final String TABLE_ROW_NAME = "name";
    public final String TABLE_ROW_SECTION = "section";
    public final String TABLE_ROW_YEAR = "year";
    public final int DB_VERSION = 1;
    public final String DB_NAME = "student_db";
    public final String TABLE_NAME = "student_details";
    public SQLiteDatabase sqLiteDB;

    public DataManager(Context context) {
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        sqLiteDB = helper.getWritableDatabase();
    }

    public void insert(String name, String section, String year) {
        String insertQuery = "insert into " + TABLE_NAME + " ( " + TABLE_ROW_NAME + ", " + TABLE_ROW_SECTION + ", " + TABLE_ROW_YEAR + " )" + " values ( '" + name + "', '" + section + "', '" + year + "');";
        sqLiteDB.execSQL(insertQuery);
        Log.i("insert() = ", insertQuery);
    }

    public Cursor getAllStudents() {
        String retrieveAllStudentQuery = "select * from " + TABLE_NAME + ";";
        return sqLiteDB.rawQuery(retrieveAllStudentQuery, null);
    }

    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {

        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            String newTableQuery = "create table " + TABLE_NAME + "(" +
                    TABLE_ROW_ID + " integer primary key autoincrement not null," +
                    TABLE_ROW_NAME +
                    " text not null," +
                    TABLE_ROW_SECTION +
                    " text not null," +
                    TABLE_ROW_YEAR +
                    " text not null);";
            db.execSQL(newTableQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


}
