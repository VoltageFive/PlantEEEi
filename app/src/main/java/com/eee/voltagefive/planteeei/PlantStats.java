package com.eee.voltagefive.planteeei;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import org.w3c.dom.Text;
import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class PlantStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_stats);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        //Up Button is the Back Button
        ab.setDisplayHomeAsUpEnabled(true);

        DatabaseHelper db_helper = new DatabaseHelper(this);

        final String PlantName = getIntent().getStringExtra("PlantName");
        TextView plantname_stats = findViewById(R.id.textView_plantname_stats);
        plantname_stats.setText(PlantName);

        ImageView plantimage_stats = findViewById(R.id.imageView_plant_stats);
        TextView next_steps = findViewById(R.id.textView_nextsteps_stats);
        ImageView cross_stats = findViewById(R.id.imageView_cross_stats);
        ImageView check_stats = findViewById(R.id.imageView_check_stats);

        Cursor plant_data = db_helper.get_info_of(PlantName);

        if(plant_data != null && plant_data.moveToFirst()) {
            final String PlantSpecies = plant_data.getString(plant_data.getColumnIndex("Plant_Species"));
            final int current_step = plant_data.getInt(plant_data.getColumnIndex("Current_Step"));
            final int time_created = plant_data.getInt(plant_data.getColumnIndex("Time_Created"));


            final RingProgressBar pb = findViewById(R.id.pb_stats);
            if(PlantSpecies.equals("Sweet Potato"))
                pb.setMax(14);
            else if(PlantSpecies.equals("Eggplant"))
                pb.setMax(15);
            else pb.setMax(100);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i <= current_step; i++){
                        try {
                            Thread.sleep(50);
                            pb.setProgress(i);
                        }catch(InterruptedException e){
                            pb.setProgress(current_step-1);
                        }

                    }
                }
            }).start();

            if (PlantSpecies.equals("Sweet Potato")) {
                plantimage_stats.setImageResource(R.mipmap.ic_kamote);
            }else if (PlantSpecies.equals("Eggplant")){
                plantimage_stats.setImageResource(R.mipmap.ic_eggplant);
            }

            String step1 = db_helper.get_step(PlantSpecies,current_step);
            String step2 = db_helper.get_step(PlantSpecies,current_step + 1);
            String step3 = db_helper.get_step(PlantSpecies,current_step + 2);
            final int cs1 = current_step;
            final int cs2 = current_step + 1;
            final int cs3 = current_step + 2;
            next_steps.setText(cs1 + ". " + step1 + '\n' + '\n' + cs2 + ". " + step2 + '\n' + '\n' + cs3 + ". " + step3 + '\n');

            if( (PlantSpecies.equals("Eggplant") && (current_step == 8 || current_step == 15) ) ||
                    ((PlantSpecies.equals("Sweet Potato")) && (current_step == 3 || current_step == 6
                            || current_step == 11 || current_step == 12 || current_step == 13 || current_step == 14))) {

                cross_stats.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int numberofsecs = 86400; //change to 24hrs, 10s for testing purposes
                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                        Intent notificationIntent = new Intent(PlantStats.this, AlarmReceiver.class);
                        notificationIntent.putExtra("id", time_created);
                        notificationIntent.putExtra("id", time_created);
                        notificationIntent.putExtra("species", PlantSpecies);
                        notificationIntent.putExtra("name", PlantName);
                        PendingIntent broadcast = PendingIntent.getBroadcast(PlantStats.this, time_created, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.SECOND, numberofsecs);
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

                        DatabaseHelper db = new DatabaseHelper(PlantStats.this);
                        db.re_step(PlantName);

                        //Go to List and clear previous activities
                        Intent new_intent = new Intent(PlantStats.this, MainActivity.class);
                        new_intent.putExtra("Context", "List");
                        finishAffinity();
                        startActivity(new_intent);
                    }
                });

            }else cross_stats.setImageResource(0);

            check_stats.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseHelper db = new DatabaseHelper(PlantStats.this);
                    long variable_time = db.time_til_next_notif(PlantSpecies, current_step);
                    db.done_step(PlantName, PlantSpecies, current_step);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                    Intent notificationIntent = new Intent(PlantStats.this, AlarmReceiver.class);
                    notificationIntent.putExtra("id", time_created);
                    notificationIntent.putExtra("species", PlantSpecies);
                    notificationIntent.putExtra("name", PlantName);
                    PendingIntent broadcast = PendingIntent.getBroadcast(PlantStats.this, time_created, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.SECOND, (int)variable_time);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

                    //Go to List and clear previous activities
                    Intent new_intent= new Intent(PlantStats.this, MainActivity.class);
                    new_intent.putExtra("Context","List");
                    finishAffinity();
                    startActivity(new_intent);
                }
            });
        }
        plant_data.close();
    }

}
