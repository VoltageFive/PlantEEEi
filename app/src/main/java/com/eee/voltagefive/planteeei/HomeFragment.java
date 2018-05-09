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

import com.bumptech.glide.Glide;

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

        final ImageView CurrentPlant = view.findViewById(R.id.CurrentPlant);
        DatabaseHelper myDb = new DatabaseHelper(getActivity());
        Cursor isEmpty = myDb.get_all_info();


        if(isEmpty != null){
            /**no user_db exists yet
             * uses ic_sleep and displays toast message when clicked
             */
            if(isEmpty.getCount() == 0) {
                isEmpty.close();

                /** Image Drawable
                 *  CurrentPlant.setImageResource(R.mipmap.ic_sleep);
                 */

                /**Gif Drawable*/
                Glide.with(getActivity()).load(R.mipmap.gif_sleep).into(CurrentPlant);
                CurrentPlant.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        /**LENGTH_LONG = duration of toast message*/
                        Toast NoPlants = Toast.makeText(getActivity(), "You don't have any plants yet!", Toast.LENGTH_LONG);
                        NoPlants.setGravity(Gravity.CENTER, 0, 450);
                        NoPlants.show();
                    }
                });
            }else{
                /**
                 * user_db exists
                 * change image based on happiness
                 * displays relevant messages when clicked
                 */
                DatabaseHelper myDb2 = new DatabaseHelper(getActivity());
                int happiness = myDb2.get_happiness();

                if(happiness <= 25){
                    CurrentPlant.setImageResource(R.mipmap.ic_angry);

                    CurrentPlant.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            /**LENGTH_LONG = duration of toast message*/
                            Toast NoPlants = Toast.makeText(getActivity(), "You're neglecting me!", Toast.LENGTH_LONG);
                            NoPlants.setGravity(Gravity.CENTER, 0, 450);
                            NoPlants.show();
                        }
                    });
                }else if((happiness > 25) && (happiness <= 50)){
                    CurrentPlant.setImageResource(R.mipmap.ic_sad);

                    CurrentPlant.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            /**LENGTH_LONG = duration of toast message*/
                            Toast NoPlants = Toast.makeText(getActivity(), "Please take care of me...", Toast.LENGTH_LONG);
                            NoPlants.setGravity(Gravity.CENTER, 0, 450);
                            NoPlants.show();
                        }
                    });
                }else if((happiness > 50) && (happiness <= 75)){
                    /**Image Drawable
                     * CurrentPlant.setImageResource(R.mipmap.ic_normal);
                     */

                    /**Gif Drawable*/
                    Glide.with(getActivity()).load(R.mipmap.gif_normal).into(CurrentPlant);
                    CurrentPlant.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            /**LENGTH_LONG = duration of toast message*/
                            Toast NoPlants = Toast.makeText(getActivity(), "Thanks for taking care of me :)", Toast.LENGTH_LONG);
                            NoPlants.setGravity(Gravity.CENTER, 0, 450);
                            NoPlants.show();
                        }
                    });
                }else if(happiness > 75){

                    /** Image Drawable
                     *  CurrentPlant.setImageResource(R.mipmap.ic_vhappy);
                     */

                    /**Gif Drawable*/
                    Glide.with(getActivity()).load(R.mipmap.gif_vhappy).into(CurrentPlant);
                    CurrentPlant.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            /**LENGTH_LONG = duration of toast message*/
                            Toast NoPlants = Toast.makeText(getActivity(), "You must be the best gardener out there!", Toast.LENGTH_LONG);
                            NoPlants.setGravity(Gravity.CENTER, 0, 450);
                            NoPlants.show();
                        }
                    });
                }

            }
        }
        //isEmpty.close();

        /**Button for adding a plant*/
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

}
