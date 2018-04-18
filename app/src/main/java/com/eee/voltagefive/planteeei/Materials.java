package com.eee.voltagefive.planteeei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Materials extends AppCompatActivity {

    //Bundle bundle = getIntent().getExtras();
    //String message = bundle.getString("item2");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);

        final String PlantName = getIntent().getStringExtra("PlantSelected");
        final String PlantSpecies = getIntent().getStringExtra("PlantSpecies");

        Button ButtonMaterials = findViewById(R.id.ButtonMaterials);
        ButtonMaterials.setOnClickListener(new View.OnClickListener() {

            //intent is created inside another class, getApplicationContext() or Materials.this
            @Override
            public void onClick(View view) {
                Intent plant_steps = new Intent(Materials.this, PlantSteps.class);
                plant_steps.putExtra("PlantSelected", PlantName);
                plant_steps.putExtra("PlantSpecies", PlantSpecies);
                startActivity(plant_steps);
            }
        });
    }
}
