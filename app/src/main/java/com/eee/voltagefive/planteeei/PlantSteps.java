package com.eee.voltagefive.planteeei;

import android.annotation.TargetApi;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class PlantSteps extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_steps);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_plantsteps);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);

        final String PlantSpecies = getIntent().getStringExtra("PlantSpecies");
        final String kamote = "Sweet Potato";
        final String eggplant = "Eggplant";

        final EditText NameInput = findViewById(R.id.editText_plant_name);

        ImageView PlantThumbnail = findViewById(R.id.imageView_steps_plant);
        TextView Name = findViewById(R.id.textView_steps_name);
        TextView ScientificName = findViewById(R.id.textView_steps_sname);
        TextView Weeks = findViewById(R.id.textView_steps_weeks);
        TextView step_1 = findViewById(R.id.textView_steps_1);
        TextView step_2 = findViewById(R.id.textView_steps_2);
        TextView step_3 = findViewById(R.id.textView_steps_3);
        TextView step_4 = findViewById(R.id.textView_steps_4);
        TextView step_5 = findViewById(R.id.textView_steps_5);
        TextView step_6 = findViewById(R.id.textView_steps_6);
        TextView step_7 = findViewById(R.id.textView_steps_7);
        TextView step_8 = findViewById(R.id.textView_steps_8);
        TextView step_9 = findViewById(R.id.textView_steps_9);
        TextView step_10 = findViewById(R.id.textView_steps_10);
        TextView step_11 = findViewById(R.id.textView_steps_11);
        TextView step_12 = findViewById(R.id.textView_steps_12);
        TextView step_13 = findViewById(R.id.textView_steps_13);
        TextView step_14 = findViewById(R.id.textView_steps_14);
        TextView step_15 = findViewById(R.id.textView_steps_15);
        TextView step_16 = findViewById(R.id.textView_steps_16);
        TextView step_17 = findViewById(R.id.textView_steps_17);
        TextView step_18 = findViewById(R.id.textView_steps_18);
        TextView step_19 = findViewById(R.id.textView_steps_19);

        if(Objects.equals(PlantSpecies, kamote)) {  /**Sweet Potato selected*/
            PlantThumbnail.setImageResource(R.mipmap.ic_kamote);
            Name.setText(getText(R.string.mats_kamote_name));
            ScientificName.setText(getText(R.string.mats_kamote_sname));
            Weeks.setText(getText(R.string.mats_kamote_weeks));
            step_1.setText(R.string.kamote_steps_1);
            step_2.setText(R.string.kamote_steps_2);
            step_3.setText(R.string.kamote_steps_3);
            step_4.setText(R.string.kamote_steps_4);
            step_5.setText(R.string.kamote_steps_5);
            step_6.setText(R.string.kamote_steps_6);
            step_7.setText(R.string.kamote_steps_7);
            step_8.setText(R.string.kamote_steps_8);
            step_9.setText(R.string.kamote_steps_9);
            step_10.setText(R.string.kamote_steps_10);
            step_11.setText(R.string.kamote_steps_11);
            step_12.setText(R.string.kamote_steps_12);
            step_13.setText(R.string.kamote_steps_13);
            step_14.setText(R.string.kamote_steps_14);
            step_15.setText(R.string.kamote_steps_15);
            step_16.setText(R.string.kamote_steps_16);
            step_17.setText(R.string.kamote_steps_17);
            step_18.setText(R.string.kamote_steps_18);
            step_19.setVisibility(View.GONE);
        }else if(Objects.equals(PlantSpecies, eggplant)) {  /**Eggplant selected*/
            PlantThumbnail.setImageResource(R.mipmap.ic_eggplant);
            Name.setText(getText(R.string.mats_eggplant_name));
            ScientificName.setText(getText(R.string.mats_eggplant_sname));
            Weeks.setText(getText(R.string.mats_eggplant_weeks));
            step_1.setText(R.string.eggplant_steps_1);
            step_2.setText(R.string.eggplant_steps_2);
            step_3.setText(R.string.eggplant_steps_3);
            step_4.setText(R.string.eggplant_steps_4);
            step_5.setText(R.string.eggplant_steps_5);
            step_6.setText(R.string.eggplant_steps_6);
            step_7.setText(R.string.eggplant_steps_7);
            step_8.setText(R.string.eggplant_steps_8);
            step_9.setText(R.string.eggplant_steps_9);
            step_10.setText(R.string.eggplant_steps_10);
            step_11.setText(R.string.eggplant_steps_11);
            step_12.setText(R.string.eggplant_steps_12);
            step_13.setText(R.string.eggplant_steps_13);
            step_14.setText(R.string.eggplant_steps_14);
            step_15.setText(R.string.eggplant_steps_15);
            step_16.setText(R.string.eggplant_steps_16);
            step_17.setText(R.string.eggplant_steps_17);
            step_18.setText(R.string.eggplant_steps_18);
            step_19.setText(R.string.eggplant_steps_19);
        }

        Button ButtonPlantSteps = findViewById(R.id.ButtonPlantSteps);
        ButtonPlantSteps.setOnClickListener(new View.OnClickListener() {
            /**intent is created inside another class, getApplicationContext() or PlantSteps.this*/
            @TargetApi(Build.VERSION_CODES.O)
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String PlantName = NameInput.getText().toString();
                /**check if input is empty*/
                if(PlantName.length() == 0){
                    Toast NoName = Toast.makeText(PlantSteps.this, "Plant Name can't be empty!", Toast.LENGTH_LONG);
                    NoName.setGravity(Gravity.CENTER, 0, 450);
                    NoName.show();
                }else{
                    Cursor LimitCheck = myDb.get_all_info();

                    /**10 Plant Limit*/
                    if(LimitCheck.getCount() < 10){

                        /**Check for existing names*/
                        boolean isInserted = myDb.insertData(PlantName, PlantSpecies);
                        if(isInserted == true){
                            //Display Message
                            Toast SameName = Toast.makeText(PlantSteps.this, "Plant has been added", Toast.LENGTH_LONG);
                            SameName.setGravity(Gravity.CENTER, 0, 450);
                            SameName.show();


                            Intent plant_steps = new Intent(PlantSteps.this, MainActivity.class);
                            finishAffinity();
                            startActivity(plant_steps);
                        }else{
                            //Display Message
                            Toast SameName = Toast.makeText(PlantSteps.this, "Plant Name already exists", Toast.LENGTH_LONG);
                            SameName.setGravity(Gravity.CENTER, 0, 450);
                            SameName.show();
                        }

                    }else{
                        //Display Message
                        Toast SameName = Toast.makeText(PlantSteps.this, "Maximum Number of Plants reached! (Max is 10)", Toast.LENGTH_LONG);
                        SameName.setGravity(Gravity.CENTER, 0, 450);
                        SameName.show();
                    }

                }
            }
        });
    }


}
