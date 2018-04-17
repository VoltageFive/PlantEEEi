package com.eee.voltagefive.planteeei;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment implements View.OnClickListener{

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
        BtnAddPlant = view.findViewById(R.id.BtnAddPlant);
        BtnAddPlant.setOnClickListener(this);

        /*BtnAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_AddPlant = new Intent(getActivity(), AddPlant.class);
                startActivity(intent_AddPlant);
            }
        });*/

        return view;
    }

    @Override
    public void onClick(View view) {
        openActivity();
    }

    public void openActivity() {
        Intent intent_AddPlant = new Intent(getActivity(), AddPlant.class);
        startActivity(intent_AddPlant);
    }

    /*
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_home:
                Intent intent_AddPlant = new Intent(getActivity(), AddPlant.class);
                startActivity(intent_AddPlant);
                break;
        }
    }*/
}
