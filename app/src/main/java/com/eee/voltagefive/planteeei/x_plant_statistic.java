package com.eee.voltagefive.planteeei;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.RotateDrawable;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.Calendar;
import org.w3c.dom.Text;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class x_plant_statistic extends AppCompatActivity {

    //Toolbar mToolbar= findViewById(R.id.my_toolbar);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_plant_statistic);

        DatabaseHelper db_helper = new DatabaseHelper(this);

        final String PlantName = getIntent().getStringExtra("PlantName");
        TextView for_plant_name = findViewById(R.id.stats_plant_name);
        for_plant_name.setText(PlantName);

        Cursor plant_data = db_helper.get_info_of(PlantName);

        if(plant_data != null && plant_data.moveToFirst()) {
            final String PlantSpecies = plant_data.getString(plant_data.getColumnIndex("Plant_Species"));
            final int current_step = plant_data.getInt(plant_data.getColumnIndex("Current_Step"));


            //ito ung nag-aayos ng ProgressBar kung gusto mo baguhin ung itsura (like colors bla bla)
            //use this link as reference; https://www.youtube.com/watch?v=6Kp-VbHiCyU
            final RingProgressBar pb = findViewById(R.id.progressBar);
            if(PlantSpecies.equals("Eggplant") || PlantSpecies.equals("Sweet Potato"))
                pb.setMax(15);
            else
                pb.setMax(50);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i <= current_step; i++){
                        try {
                            Thread.sleep(50);
                            pb.setProgress(i);
                        }catch(InterruptedException e){
                            pb.setProgress(current_step);
                        }

                    }
                }
            }).start();

            if (PlantSpecies.equals("Sweet Potato")) {
                ImageView image = findViewById(R.id.stats_image);
                image.setImageResource(R.mipmap.ic_kamote);
            }

            String step1 = db_helper.get_step(PlantSpecies,current_step);
            String step2 = db_helper.get_step(PlantSpecies,current_step + 1);
            String step3 = db_helper.get_step(PlantSpecies,current_step + 2);
            TextView TV_step1 = new TextView(this);
            TextView TV_step2 = new TextView(this);
            TextView TV_step3 = new TextView(this);
            TV_step1.setText(step1);
            TV_step1.setLayoutParams(new LinearLayout.LayoutParams(400,LinearLayout.LayoutParams.WRAP_CONTENT));

            TV_step2.setText(step2);
            TV_step3.setText(step3);

            LinearLayout edit_me = findViewById(R.id.list_steps_here);
            LinearLayout.LayoutParams layout_for_all = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout LL_step1 = new LinearLayout(this);
            LinearLayout LL_step2 = new LinearLayout(this);
            LinearLayout LL_step3 = new LinearLayout(this);
            LL_step1.setLayoutParams(layout_for_all);
            LL_step1.setOrientation(LinearLayout.HORIZONTAL);
            LL_step1.setPadding(0,20,0,20);
            LL_step2.setLayoutParams(layout_for_all);
            LL_step2.setOrientation(LinearLayout.VERTICAL);
            LL_step2.setPadding(0,0,0,20);
            LL_step3.setLayoutParams(layout_for_all);
            LL_step3.setOrientation(LinearLayout.VERTICAL);
            LL_step2.setPadding(0,0,0,20);
            LL_step1.addView(TV_step1);

            LinearLayout.LayoutParams LL_button_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            LinearLayout LL_step1_image = new LinearLayout(this);
            LL_step1_image.setOrientation(LinearLayout.HORIZONTAL);
            LL_step1_image.setPadding(20,0,10,0);
            LL_step1_image.setLayoutParams(LL_button_params);

            if( (PlantSpecies.equals("Eggplant") && (current_step == 8 || current_step == 16) ) ||
                    ((PlantSpecies.equals("Sweet Potato")) && (current_step == 3 || current_step == 6
                            || current_step == 11 || current_step == 12 || current_step == 13 || current_step == 14))){
                LinearLayout cross_LL = new LinearLayout(this);
                cross_LL.setGravity(Gravity.LEFT);
                cross_LL.setLayoutParams(new LinearLayout.LayoutParams(60,LinearLayout.LayoutParams.MATCH_PARENT));
                cross_LL.setPadding(0,0,20,0);

                ImageView cross = new ImageView(this);
                cross.setImageResource(R.mipmap.xbutton);

                cross_LL.addView(cross);
                LL_step1_image.addView(cross_LL);
                cross_LL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // put NOTIF here
                        // ito ung NOTIF na sineset after 24 HRS

                        int numberofsecs = 10; //change to 24hrs, 10s for testing purposes
                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                        Intent notificationIntent = new Intent(x_plant_statistic.this, AlarmReceiver.class);
                        PendingIntent broadcast = PendingIntent.getBroadcast(x_plant_statistic.this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.SECOND, numberofsecs);
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

                        DatabaseHelper db = new DatabaseHelper(x_plant_statistic.this);
                        db.re_step(PlantName);

                        Intent new_intent= new Intent(x_plant_statistic.this, MainActivity.class);
                        new_intent.putExtra("Context","List");
                        startActivity(new_intent);
                    }
                });
            }

            LinearLayout check_LL = new LinearLayout(this);
            check_LL.setGravity(Gravity.RIGHT);
            check_LL.setLayoutParams(new LinearLayout.LayoutParams(60,LinearLayout.LayoutParams.MATCH_PARENT));
            check_LL.setPadding(20,0,0,0);

            ImageView check = new ImageView(this);
            check.setImageResource(R.mipmap.checkbutton);

            check_LL.addView(check);
            check_LL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // put NOTIF here
                    // ito ung notif na VARIABLE ung time + 24 hrs
                    // ung 'variable_time' is ung time na tinutukoy sa earlier sentence; un-comment mo na lang
                    // seconds yan BTW

                    DatabaseHelper db = new DatabaseHelper(x_plant_statistic.this);
                    //long variable_time = db.time_til_next_notif(PlantSpecies,current_step);
                    db.done_step(PlantName,PlantSpecies,current_step);

                    Intent new_intent= new Intent(x_plant_statistic.this, MainActivity.class);
                    new_intent.putExtra("Context","List");
                    startActivity(new_intent);
                }
            });

            LL_step1_image.addView(check_LL);

            LL_step1.addView(LL_step1_image);
            LL_step2.addView(TV_step2);
            LL_step3.addView(TV_step3);

            LinearLayout under_sv = new LinearLayout(this);
            under_sv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            under_sv.setOrientation(LinearLayout.VERTICAL);

            ScrollView sv = new ScrollView(this);
            sv.setBackgroundResource(R.color.colorAccent);
            sv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            under_sv.addView(LL_step1);
            under_sv.addView(LL_step2);
            under_sv.addView(LL_step3);
            sv.addView(under_sv);
            edit_me.addView(sv);

            plant_data.close();
        }
    }
}
