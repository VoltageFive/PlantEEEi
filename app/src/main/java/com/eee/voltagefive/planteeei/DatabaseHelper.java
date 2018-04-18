package com.eee.voltagefive.planteeei;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PLANTEEI_db";
    public static final String TABLE_NAME1 = "user_db";
    public static final String COL_1 = "Plant_Name";
    public static final String COL_2 = "Plant_Species";
    //public static final string COL_3 = "Current_Step";
    //public static final string COL_4 = "Additional_Time";
    //public static final string COL_5 = "Time_Created";
    //public static final string COL_6 = "Observation_Time";

    public static final String TABLE_NAME2 = "plant_data";
    public static final String COL_1_2 = "ID";
    public static final String COL_2_2 = "Plant_Species";
    //insert plant steps here
    //let x be the maximum number of steps
    //column number would then be 2 + x; use NULL if certain species have < ( 2 + x )


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);

        //gets called by onCreate in MainActivity
        //creates/opens database and table
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*SQLiteDatabase dbcheck = null;
        try{
            dbcheck = SQLiteDatabase.openDatabase(DATABASE_NAME,null, SQLiteDatabase.OPEN_READONLY);
            dbcheck.close();
        } catch (SQLiteException e) {*/
            db.execSQL("create table " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Plant_Species TEXT)");
            db.execSQL("create table " + TABLE_NAME1 + " (Plant_Name TEXT PRIMARY KEY, Plant_Species TEXT)");

        //}
    }

    //SQLite removes the table only if the table exists, otherwise, do nothing
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    public boolean insertData(String PlantName, String PlantSpecies) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, PlantName);
        contentValues.put(COL_2, PlantSpecies);
        long result = db.insert(TABLE_NAME1,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
}
