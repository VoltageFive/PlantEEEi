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
    public String item2 = "NULL";
    public String eggplant = "Eggplant";
    public String kamote = "Sweet Potato";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_plant_beginner, container, false);

        //Format: putExtra("key", String);

        /**Begin
         * Sweet Potato OnClickListeners for ImageViews and TextViews*/

        TextView kamote_name = view.findViewById(R.id.textView_kamote_name);
        TextView kamote_desc = view.findViewById(R.id.textView_kamote_desc);
        ImageView kamote_pic = view.findViewById(R.id.imageView_kamote_pic);

        //Sweet Potato Name TextView OnClickListener
        kamote_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kamote_name_mats= new Intent(getActivity(), Materials.class);
                kamote_name_mats.putExtra("PlantSpecies", kamote);
                startActivity(kamote_name_mats);
            }
        });

        //Sweet Potato Description TextView OnClickListener
        kamote_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kamote_desc_mats = new Intent(getActivity(), Materials.class);
                kamote_desc_mats.putExtra("PlantSpecies", kamote);
                startActivity(kamote_desc_mats);
            }
        });

        //Sweet Potato Thumbnail ImageView OnClickListener
        kamote_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kamote_pic_mats = new Intent(getActivity(), Materials.class);
                kamote_pic_mats.putExtra("PlantSpecies", kamote);
                startActivity(kamote_pic_mats);
            }
        });
        /**End*/


        /**Begin
         *Eggplant OnClickListeners for ImageViews and TextViews*/

        TextView eggplant_name = view.findViewById(R.id.textView_eggplant_name);
        TextView eggplant_desc = view.findViewById(R.id.textView_eggplant_description);
        ImageView eggplant_pic = view.findViewById(R.id.imageView_eggplant_pic);

        //Eggplant Name TextView OnClickListener
        eggplant_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eggplant_name_mats = new Intent(getActivity(), Materials.class);
                eggplant_name_mats.putExtra("PlantSpecies", eggplant);
                startActivity(eggplant_name_mats);
            }
        });

        //Eggplant Description TextView OnClickListener
        eggplant_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eggplant_desc_mats = new Intent(getActivity(), Materials.class);
                eggplant_desc_mats.putExtra("PlantSpecies", eggplant);
                startActivity(eggplant_desc_mats);
            }
        });

        //Eggplant Thumbnail ImageView OnClickListener
        eggplant_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent eggplant_pic_mats = new Intent(getActivity(), Materials.class);
                eggplant_pic_mats.putExtra("PlantSpecies", eggplant);
                startActivity(eggplant_pic_mats);
            }
        });
        /**End*/

        return view;
    }
}
