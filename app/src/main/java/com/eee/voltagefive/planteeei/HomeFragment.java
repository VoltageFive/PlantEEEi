package com.eee.voltagefive.planteeei;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {//implements View.OnClickListener{


    View view;
    FloatingActionButton BtnAddPlant;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_home, container, false);

        ImageView CurrentPlant = view.findViewById(R.id.CurrentPlant);
        DatabaseHelper myDb = new DatabaseHelper(getActivity());
        Cursor isEmpty = myDb.get_all_info();


        if(isEmpty != null){
            //no user_db exists yet
            if(isEmpty.getCount() == 0) {
                isEmpty.close();
                CurrentPlant.setImageResource(R.mipmap.ic_sleep);

                //Clickable Image
                CurrentPlant.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //LENGTH_LONG = duration of toast message
                        Toast NoPlants = Toast.makeText(getActivity(), "You don't have any plants yet!", Toast.LENGTH_LONG);
                        NoPlants.setGravity(Gravity.CENTER, 0, 450);
                        NoPlants.show();
                    }
                });
            }else{
                //user_db exists
                isEmpty.close();
                CurrentPlant.setImageResource(R.mipmap.ic_vhappy);

                CurrentPlant.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent_PlantList = new Intent(getActivity(), PlantList.class);
                        startActivity(intent_PlantList);
                    }
                });

            }
        }

        BtnAddPlant = view.findViewById(R.id.BtnAddPlant);
        BtnAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_AddPlant = new Intent(getActivity(), AddPlant.class);
                startActivity(intent_AddPlant);
            }
        });
        return view;
    }

    /*
    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }*/
}
