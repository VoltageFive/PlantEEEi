package com.eee.voltagefive.planteeei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.sql.SQLInput;

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

    public static final String MISC_TABLE = "MISC_TABLE";
    public static final String COL_1_MISC_TABLE = "Name";
    public static final String COL_2_MISC_TABLE = "Value";
    //insert plant steps here
    //let x be the maximum number of steps
    //column number would then be 2 + x; use NULL if certain species have < ( 2 + x )

    public static final String EGGPLANT_STEP_1 = "Clean your containers.";
    public static final String EGGPLANT_STEP_2 = "Prepare soil. Mix 2 parts potting soil, 1 part sand. Mix a little compost and 5-10-5 fertilizer.";
    public static final String EGGPLANT_STEP_3 = "Make a support system.";
    public static final String EGGPLANT_STEP_4 = "Start indoors, temperature must be 12.8 C or higher. Fill your small pots or trays with the potting mix";
    public static final String EGGPLANT_STEP_5 = "Poke a ½ inch (1.25 cm) hole in the center of each pot or tray. Place 2 seeds in each hole. Cover them afterwards, just lightly drop the soil over the seeds, don’t pack it in.";
    public static final String EGGPLANT_STEP_6 = "Set the pots or trays out on a warm, sunny windowsill. Ensure that the window receives at least 8 hours of sunlight a day.";
    public static final String EGGPLANT_STEP_7 = "Water the seeds every day. Don’t create puddles of water on top of your soil but prevent the soil from drying out.";
    public static final String EGGPLANT_STEP_8 = "Once the seedlings sprout two sets of leaves, snip out the weaker seedling, do not yank the weaker seedling out.";
    public static final String EGGPLANT_STEP_9 = "Is the plant at least ½ foot (15.25 cm) in height?";
    public static final String EGGPLANT_STEP_10 = "Set up the supporting stakes in the permanent pot.";
    public static final String EGGPLANT_STEP_11 = "Fill the permanent pot with the potting medium.";
    public static final String EGGPLANT_STEP_12 = "Dig a hole in the center of the pot that is as deep and wide as the container the seedlings are currently planted in.";
    public static final String EGGPLANT_STEP_13 = "Remove the seedling from its container and place it into the new pot. It is easier to transplant a wet and compact soil than dry, crumbly soil.";
    public static final String EGGPLANT_STEP_14 = "Place the pot in a sunny location.";
    public static final String EGGPLANT_STEP_15 = "Water the plant daily. " +
            "You might need to water additionally if the weather is hot and dry." +
            " Add liquid fertilizer once every 2 weeks. Make sure you add fertilizer after watering the soil, don’t add fertilizer on dry soil." +
            " Tie the eggplant to the support to promote upward growth.";
    public static final String EGGPLANT_STEP_16 = "Harvest when the skin looks glossy.";
    public static final long EGGPLANT_STEP_9_TIME = 3628800;
    //public static final long EGGPLANT_STEP_9_TIME = 5; //for testing purposes
    public static final long EGGPLANT_STEP_16_TIME = 5011200;
    //public static final long EGGPLANT_STEP_16_TIME = 10; //for testing purposes

    public static final String SWEET_POTATO_STEP_1 = "Clean the sweet potato and split it in half (or large sections)";
    public static final String SWEET_POTATO_STEP_2 = "Put them in a container with half of the potato submerged in water";
    public static final String SWEET_POTATO_STEP_3 = "Put it in a window ledge or anywhere warm (e.g. on top of a radiator)";
    public static final String SWEET_POTATO_STEP_4 = "Is it 6-9 inches long?";
    public static final String SWEET_POTATO_STEP_5 = "Twist the slips off from the potato.";
    public static final String SWEET_POTATO_STEP_6 = "Transfer the slip to a shallow bowl with half the stem submerged in water";
    public static final String SWEET_POTATO_STEP_7 = "Observe if the roots have grown about an inch and keep the water fresh (few days)";
    public static final String SWEET_POTATO_STEP_8 = "Transfer the slips; make sure the soil is loose";
    public static final String SWEET_POTATO_STEP_9 = "Using a small hand trowel, dig a hole about 4 in. or 5 in. deep a" +
            "nd 3 in. wide. Place one slip in each hole with the roots pointing down. Position the slip so tha" +
            "t the bottom half will be covered with dirt while the top half with all of the new leaves is above ground. ";
    public static final String SWEET_POTATO_STEP_10 = "Carefully cover the hole with soil " +
            "and gently press (to remove remaining air pockets)";
    public static final String SWEET_POTATO_STEP_11 = "Water until the surrounding dirt is wet. Stop watering before your mound starts to erode.";
    public static final String SWEET_POTATO_STEP_12 = "Water everyday";
    public static final String SWEET_POTATO_STEP_13 = "Water every other day(second week)";
    public static final String SWEET_POTATO_STEP_14 = "Water twice a week(third week)";
    public static final String SWEET_POTATO_STEP_15 = "Water once a week until leaves start to yellow; then harvest.";



    public static final long SWEET_POTATO_STEP_4_TIME = 608400 * 6;
    public static final long SWEET_POTATO_STEP_7_TIME = 172800;
    public static final long SWEET_POTATO_STEP_12_TIME = 608400;
    public static final long SWEET_POTATO_STEP_13_TIME = 608400;
    public static final long SWEET_POTATO_STEP_14_TIME = 608400;
    public static final long SWEET_POTATO_STEP_15_TIME = 608400 * 21;


    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME, null, 1);

        //gets called by onCreate in MainActivity
        //creates/opens database and table
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME2 + "(" + COL_1_2 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2_2 + " TEXT)");
            //db.execSQL("create table " + TABLE_NAME2 + +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Plant_Species TEXT)");
            //db.execSQL("create table " + TABLE_NAME1 + " (Plant_Name TEXT PRIMARY KEY, Plant_Species TEXT)");
        db.execSQL("create table " + TABLE_NAME1 + "(" + COL_1 + " TEXT PRIMARY KEY," + COL_2 + " TEXT," +
                COL_3 + " INTEGER," + COL_4 + " INTEGER," + COL_5 + " INTEGER," + COL_6 + " INTEGER)");
            //db.execSQL("create table " + TABLE_NAME3 + " (" + COL_1_3 + " INTEGER," + COL_2_3 + " TEXT PRIMARY KEY)");
        db.execSQL("create table " + MISC_TABLE + "(" + COL_1_MISC_TABLE + " TEXT PRIMARY KEY," + COL_2_MISC_TABLE + " INTEGER)");

        ContentValues for_misc = new ContentValues();
        for_misc.put(COL_1_MISC_TABLE,"Happiness");
        for_misc.put(COL_2_MISC_TABLE, 55);
        db.insert(MISC_TABLE,null,for_misc);
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
        long current_time = System.currentTimeMillis();

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues new_plant_data = new ContentValues();
        new_plant_data.put(COL_1, PlantName);
        new_plant_data.put(COL_2, PlantSpecies);
        new_plant_data.put(COL_3, current_time);
        new_plant_data.put(COL_4,1);
        new_plant_data.put(COL_5,0);
        new_plant_data.put(COL_6,0);
        long result = db.insert(TABLE_NAME1,null ,new_plant_data);
        if(result == -1)
            return false;
        else
            return true;
    }

    public void deleteData(String PlantName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from " + TABLE_NAME1 + " where " + COL_1 + " ='" + PlantName + "'");
    }

    public Cursor get_all_info(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT " + COL_1 + "," + COL_2 + "," + COL_3 + "," + COL_4 + " FROM " + TABLE_NAME1, null);
        return res;
    }

    public Cursor get_info_of(String PlantName){
        SQLiteDatabase mdb = this.getWritableDatabase();
        Cursor res = mdb.rawQuery("select * from " + TABLE_NAME1 + " where " + COL_1 + " = ?", new String[]{PlantName});
        return res;
    }

    public long time_til_next_notif(String Species, int step_number){
        long res = 0;
        if(Species.equals("Eggplant")){
            switch (step_number){
                case 8: res = EGGPLANT_STEP_9_TIME;
                    break;
                case 15: res = EGGPLANT_STEP_16_TIME;
                    break;
                default: res = 10;
            }
        }
        else if(Species.equals("Sweet Potato")){
            switch (step_number){
                case 3: res = SWEET_POTATO_STEP_4_TIME;
                    break;
                case 6: res = SWEET_POTATO_STEP_7_TIME;
                    break;
                case 11: res = SWEET_POTATO_STEP_12_TIME;
                    break;
                case 12: res = SWEET_POTATO_STEP_13_TIME;
                    break;
                case 13: res = SWEET_POTATO_STEP_14_TIME;
                    break;
                case 14: res = SWEET_POTATO_STEP_15_TIME;
                    break;
                default: res = 10;
            }
        }

        return res;
    }

    public int get_happiness(){
        int happiness;
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("select " + COL_2_MISC_TABLE + " from " + MISC_TABLE + " where " + COL_1_MISC_TABLE + "='Happiness'",null);
        if(res.moveToFirst()){
            happiness = res.getInt(0);
        }
        else
            happiness = 0;
        return happiness;
    }

    public void update_happiness(int mod){
        int new_happiness = get_happiness() + mod;

        if(new_happiness > 100)
            new_happiness = 100;
        else if(new_happiness < 0)
            new_happiness = 0;

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("update " + MISC_TABLE + " set " + COL_2_MISC_TABLE + " = " + new_happiness + " where " + COL_1_MISC_TABLE
        + "='Happiness'");
    }

    public void done_step(String PlantName, String Species, int Current_Step){
        SQLiteDatabase db = getWritableDatabase();
        int next_step = Current_Step + 1;

        if((Species.equals("Sweet Potato") && (Current_Step == 15)) || (Species.equals("Eggplant") && (Current_Step == 16))){
            db.execSQL("delete from " + TABLE_NAME1 + " where " + COL_1 + " ='" + PlantName + "'");
            update_happiness(7);
        }
        else{
            db.execSQL("update " + TABLE_NAME1 + " set " + COL_4 + " = " + next_step + " where " + COL_1 + " ='" + PlantName
            + "'");
            update_happiness(4);
        }
    }

    public void re_step(String PlantName){
        SQLiteDatabase db = getWritableDatabase();
        long old_time;

        Cursor time = db.rawQuery("select " + COL_5 + " from " + TABLE_NAME1 + " where " + COL_1 + " ='" + PlantName + "'",null);
        if(time.moveToFirst())
            old_time = time.getLong(0) + 86400;     //1 day
        else
            old_time = 0;

        db.execSQL("update " + TABLE_NAME1 + " set " + COL_5 + " = " + old_time + " where " + COL_1 + " ='" + PlantName + "'");
        update_happiness(-5);
    }

    public String get_step(String Species, int step_number){
        String res = new String();
        if(Species.equals("Eggplant")){
            switch (step_number){
                case 1: res = EGGPLANT_STEP_1;
                    break;
                case 2: res = EGGPLANT_STEP_2;
                    break;
                case 3: res = EGGPLANT_STEP_3;
                    break;
                case 4: res = EGGPLANT_STEP_4;
                    break;
                case 5: res = EGGPLANT_STEP_5;
                    break;
                case 6: res = EGGPLANT_STEP_6;
                    break;
                case 7: res = EGGPLANT_STEP_7;
                    break;
                case 8: res = EGGPLANT_STEP_8;
                    break;
                case 9: res = EGGPLANT_STEP_9;
                    break;
                case 10: res = EGGPLANT_STEP_10;
                    break;
                case 11: res = EGGPLANT_STEP_11;
                    break;
                case 12: res = EGGPLANT_STEP_12;
                    break;
                case 13: res = EGGPLANT_STEP_13;
                    break;
                case 14: res = EGGPLANT_STEP_14;
                    break;
                case 15: res = EGGPLANT_STEP_15;
                     break;
                case 16: res = EGGPLANT_STEP_16;
                    break;
                default: res = "";
            }
        }
        else if(Species.equals("Sweet Potato")){
            switch (step_number){
                case 1: res = SWEET_POTATO_STEP_1;
                    break;
                case 2: res = SWEET_POTATO_STEP_2;
                    break;
                case 3: res = SWEET_POTATO_STEP_3;
                    break;
                case 4: res = SWEET_POTATO_STEP_4;
                    break;
                case 5: res = SWEET_POTATO_STEP_5;
                    break;
                case 6: res = SWEET_POTATO_STEP_6;
                    break;
                case 7: res = SWEET_POTATO_STEP_7;
                    break;
                case 8: res = SWEET_POTATO_STEP_8;
                    break;
                case 9: res = SWEET_POTATO_STEP_9;
                    break;
                case 10: res = SWEET_POTATO_STEP_10;
                    break;
                case 11: res = SWEET_POTATO_STEP_11;
                    break;
                case 12: res = SWEET_POTATO_STEP_12;
                    break;
                case 13: res = SWEET_POTATO_STEP_13;
                    break;
                case 14: res = SWEET_POTATO_STEP_14;
                    break;
                case 15: res = SWEET_POTATO_STEP_15;
                    break;
                default: res = "";
            }
        }
        else
            res = "Unknown Species";

        return res;
    }
}
