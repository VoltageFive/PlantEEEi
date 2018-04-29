package com.eee.voltagefive.planteeei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PLANTEEI_db";
    public static final String TABLE_NAME1 = "user_db";
    public static final String COL_1 = "Plant_Name";
    public static final String COL_2 = "Plant_Species";
    public static final String COL_3 = "Time_Created";
    public static final String COL_4 = "Current_Step";
    public static final String COL_5 = "Additional_Time";
    public static final String COL_6 = "Observation_Time";

    public static final String TABLE_NAME2 = "plant_data";
    public static final String COL_1_2 = "ID";
    public static final String COL_2_2 = "Plant_Species";
    //insert plant steps here
    //let x be the maximum number of steps
    //column number would then be 2 + x; use NULL if certain species have < ( 2 + x )
    public static final String TABLE_NAME3 = "table_for_happiness";
    public static final String COL_1_3 = "Happiness";
    public static final String COL_2_3 = "BLABLA";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);

        //gets called by onCreate in MainActivity
        //creates/opens database and table
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //treat this as if it is a structure ( ala - C Programming )
    public class plant_info{
        private String plant_name;
        private String plant_species;

        private int time_created;
        private int current_step;

        //constructor
        public plant_info(String plant_name, String plant_species, int time_created, int current_step){
            this.plant_name = plant_name;
            this.plant_species = plant_species;
            this.time_created = time_created;
            this.current_step = current_step;
        }

        public String getPlant_name(){
            return plant_name;
        }
        public String getPlant_species(){
            return plant_species;
        }
        public int getTime_created(){
            return time_created;
        }
        public int getCurrent_step(){
            return current_step;
        }

        public void setPlant_name(String name){
            this.plant_name = name;
        }
        public void setPlant_species(String species){
            this.plant_species = species;
        }
        public void setTime_create(int time_created){
            this.time_created = time_created;
        }
        public void setCurrent_step(int current_step){
            this.current_step = current_step;
        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*SQLiteDatabase dbcheck = null;
        try{
            dbcheck = SQLiteDatabase.openDatabase(DATABASE_NAME,null, SQLiteDatabase.OPEN_READONLY);
            dbcheck.close();
        } catch (SQLiteException e) {*/
            db.execSQL("create table " + TABLE_NAME2 + "(" + COL_1_2 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2_2 + " TEXT)");
            //db.execSQL("create table " + TABLE_NAME2 + +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Plant_Species TEXT)");
            //db.execSQL("create table " + TABLE_NAME1 + " (Plant_Name TEXT PRIMARY KEY, Plant_Species TEXT)");
            db.execSQL("create table " + TABLE_NAME1 + "(" + COL_1 + " TEXT PRIMARY KEY," + COL_2 + " TEXT," +
                COL_3 + " INTEGER," + COL_4 + " INTEGER," + COL_5 + " INTEGER," + COL_6 + " INTEGER)");
            db.execSQL("create table " + TABLE_NAME3 + " (" + COL_1_3 + " INTEGER," + COL_2_3 + " TEXT PRIMARY KEY)");
            db.execSQL("insert into " + TABLE_NAME3 + " (" + COL_1_3 + ", " + COL_2_3 + ") values (60, 'H_Total')" );
        //}
    }

    //SQLite removes the table only if the table exists, otherwise, do nothing
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    //@RequiresApi(api = Build.VERSION_CODES.O)
    public boolean insertData(String PlantName, String PlantSpecies) {
        //Date current_time = Calendar.getInstance().getTime();
        //long ms_since_epoch = (long) current_time;
        //Instant current_time = Instant.now();
        //long seconds_since_epoch = current_time.getEpochSecond();
        //int i = (int) (new Date().getTime()/1000);
        long current_time = System.currentTimeMillis();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues new_plant_data = new ContentValues();
        new_plant_data.put(COL_1, PlantName);
        new_plant_data.put(COL_2, PlantSpecies);
        new_plant_data.put(COL_3, current_time);
        new_plant_data.put(COL_4,0);
        new_plant_data.put(COL_5,0);
        new_plant_data.put(COL_6,0);
        long result = db.insert(TABLE_NAME1,null ,new_plant_data);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor get_all_info(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT " + COL_1 + "," + COL_2 + "," + COL_3 + "," + COL_4 + " FROM " + TABLE_NAME1, null);
        return res;
    }

    public void get_plant_info(plant_info[] info){
        Cursor db_info = get_all_info();
        if(db_info.getCount() == 0){
            //initialize array size to zero, maybe?
            info = new plant_info[0];
            return;
        }
        info = new plant_info[db_info.getCount()];
        int i = 0;
        while(db_info.moveToNext()) {
            info[i] = new plant_info(db_info.getString(0), db_info.getString(1), db_info.getInt(2), db_info.getInt(3));
            i++;
        }
        db_info.close();
    }


    //use this to increment current step once user has confirmed a step was accomplished
    public Boolean step_confirmed(String plant_name_input){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select Current_Step,Plant_Species from " + TABLE_NAME1 + " where "
            + COL_1 + "='" + plant_name_input + "'",null);
        if(res.getCount() == 0){
            return false; // means that no plant_name = plant_name_input
        }
        int step = res.getInt(0) + 1;
        String species = res.getString(1);
        res.close();

        if(plant_name_input.equals("Eggplant") && step == 12){
            //plant is finished
            update_happiness(5);
            db.execSQL("delete from " + TABLE_NAME1 + " where " + COL_1 + "='" + plant_name_input + "'");
        }
        //put query below in else statement
        //add if statement later on to check if ALL plant steps are done; delete then
        //might want to increment happiness in here as well
        else{
            db.execSQL("update " + TABLE_NAME1 + " set " + COL_4 + " = " + Integer.toString(step) + " where " + COL_1 + "='" +
                    plant_name_input + "'");
        }
        return true;
    }

    public void update_happiness(int modification){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor temp = db.rawQuery("select " + COL_1_3 + " from " + TABLE_NAME3, null);
        int new_happiness = temp.getInt(0) + modification;
        temp.close();
        db.execSQL("update " + TABLE_NAME3 + " set " + COL_1_3 + " = " + Integer.toString(new_happiness) + " where " + COL_2_3 +
                "='H_Total'");
    }

}
