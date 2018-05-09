package com.eee.voltagefive.planteeei;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class Materials extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_materials);
        setSupportActionBar(toolbar);

        /** Back button interferes with putExtra
            ActionBar ab = getSupportActionBar();
            // Enable the Up button
            //Up Button is the Back Button
            ab.setDisplayHomeAsUpEnabled(true);
         */

        final String PlantSpecies = getIntent().getStringExtra("PlantSpecies");

        final String kamote = "Sweet Potato";
        final String eggplant = "Eggplant";

        ImageView PlantThumbnail = findViewById(R.id.imageView_mats_plant);
        TextView PlantName = findViewById(R.id.textView_mats_name);
        TextView ScientificName = findViewById(R.id.textView_mats_sname);
        TextView Weeks = findViewById(R.id.textView_mats_weeks);
        TextView Description = findViewById(R.id.textView_mats_desc);
        ImageView M1_pic = findViewById(R.id.imageView_mats_1);
        TextView M1_name = findViewById(R.id.textView_mats_1);
        ImageView M2_pic = findViewById(R.id.imageView_mats_2);
        TextView M2_name = findViewById(R.id.textView_mats_2);
        ImageView M3_pic = findViewById(R.id.imageView_mats_3);
        TextView M3_name = findViewById(R.id.textView_mats_3);
        ImageView M4_pic = findViewById(R.id.imageView_mats_4);
        TextView M4_name = findViewById(R.id.textView_mats_4);
        ImageView M5_pic = findViewById(R.id.imageView_mats_5);
        TextView M5_name = findViewById(R.id.textView_mats_5);
        ImageView M6_pic = findViewById(R.id.imageView_mats_6);
        TextView M6_name = findViewById(R.id.textView_mats_6);
        ImageView M7_pic = findViewById(R.id.imageView_mats_7);
        TextView M7_name = findViewById(R.id.textView_mats_7);

        if(Objects.equals(PlantSpecies, kamote)){  /**Sweet Potato selected*/
            PlantThumbnail.setImageResource(R.mipmap.ic_kamote);
            PlantName.setText(getText(R.string.mats_kamote_name));
            ScientificName.setText(getText(R.string.mats_kamote_sname));
            Weeks.setText(getText(R.string.mats_kamote_weeks));
            Description.setText(R.string.mats_kamote_desc);
            M1_pic.setImageResource(R.mipmap.ic_kamote);
            M1_name.setText(R.string.kamote_mats_1);
            M2_pic.setImageResource(R.mipmap.ic_jar);
            M2_name.setText(R.string.kamote_mats_2);
            M3_pic.setImageResource(R.mipmap.ic_shallowbowl);
            M3_name.setText(R.string.kamote_mats_3);
            M4_pic.setImageResource(R.mipmap.ic_spadingfork);
            M4_name.setText(R.string.kamote_mats_4);
            M5_pic.setImageResource(R.mipmap.ic_wateringcan);
            M5_name.setText(R.string.kamote_mats_5);
            M6_pic.setVisibility(View.GONE);
            M6_name.setVisibility(View.GONE);
            M7_pic.setVisibility(View.GONE);
            M7_name.setVisibility(View.GONE);
        }else if(Objects.equals(PlantSpecies, eggplant)){  /**Eggplant selected*/
            PlantThumbnail.setImageResource(R.mipmap.ic_eggplant);
            PlantName.setText(getText(R.string.mats_eggplant_name));
            ScientificName.setText(getText(R.string.mats_eggplant_sname));
            Weeks.setText(getText(R.string.mats_eggplant_weeks));
            Description.setText(R.string.mats_eggplant_desc);
            M1_pic.setImageResource(R.mipmap.ic_smallpot);
            M1_name.setText(R.string.eggplant_mats_1);
            M2_pic.setImageResource(R.mipmap.ic_pot);
            M2_name.setText(R.string.eggplant_mats_2);
            M3_pic.setImageResource(R.mipmap.ic_soil);
            M3_name.setText(R.string.eggplant_mats_3);
            M4_pic.setImageResource(R.mipmap.ic_sand);
            M4_name.setText(R.string.eggplant_mats_4);
            M5_pic.setImageResource(R.mipmap.ic_fertilizer);
            M5_name.setText(R.string.eggplant_mats_5);
            M6_pic.setImageResource(R.mipmap.ic_woodensupport);
            M6_name.setText(R.string.eggplant_mats_6);
            M7_pic.setImageResource(R.mipmap.ic_trowel);
            M7_name.setText(R.string.eggplant_mats_7);
        }


        Button ButtonMaterials = findViewById(R.id.ButtonMaterials);
        ButtonMaterials.setOnClickListener(new View.OnClickListener() {

            /**intent is created inside another class, getApplicationContext() or Materials.this*/
            @Override
            public void onClick(View view) {
                Intent plant_steps = new Intent(Materials.this, PlantSteps.class);

                /**pass string dependent on chosen plant*/
                if(Objects.equals(PlantSpecies, kamote)) {
                    plant_steps.putExtra("PlantSpecies", PlantSpecies);
                }else{
                    plant_steps.putExtra("PlantSpecies", PlantSpecies);
                }
                startActivity(plant_steps);
            }
        });
    }
}
