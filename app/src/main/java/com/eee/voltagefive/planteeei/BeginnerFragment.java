package com.eee.voltagefive.planteeei;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class BeginnerFragment extends Fragment {
    public String item2 = "EggPlant";
    public String species2 = "Solanum melongena";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_plant_beginner, container, false);
        TextView text2 = view.findViewById(R.id.textView2_beginner);
        ImageView image2 = view.findViewById(R.id.imageView2_beginner);


        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent text2_mats = new Intent(getActivity(), Materials.class);
                text2_mats.putExtra("PlantSelected", item2);
                text2_mats.putExtra("PlantSpecies", species2);
                startActivity(text2_mats);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent image2_mats = new Intent(getActivity(), Materials.class);
                image2_mats.putExtra("PlantSelected", item2);
                image2_mats.putExtra("PlantSpecies", species2);
                startActivity(image2_mats);
            }
        });
        return view;
    }
}
