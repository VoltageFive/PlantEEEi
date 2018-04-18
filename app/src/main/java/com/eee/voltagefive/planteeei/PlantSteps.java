package com.eee.voltagefive.planteeei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PlantSteps extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_steps);

        myDb = new DatabaseHelper(this);

        final String PlantName = getIntent().getStringExtra("PlantSelected");
        final String PlantSpecies = getIntent().getStringExtra("PlantSpecies");
        /*TextView recvtxt = findViewById(R.id.testtxt);
        recvtxt.setText(PlantName);*/


        Button ButtonPlantSteps = findViewById(R.id.ButtonPlantSteps);
        ButtonPlantSteps.setOnClickListener(new View.OnClickListener() {

            //intent is created inside another class, getApplicationContext() or PlantSteps.this
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(PlantName, PlantSpecies);

                Intent plant_steps = new Intent(PlantSteps.this, MainActivity.class);
                startActivity(plant_steps);
            }
        });
    }


}
