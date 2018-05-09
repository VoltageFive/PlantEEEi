package com.eee.voltagefive.planteeei;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ListFragment extends Fragment {
    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStat){
        DatabaseHelper db = new DatabaseHelper(getActivity());
        final Cursor all_plant_info = db.get_all_info();

        View view = inflater.inflate(R.layout.home_list, container, false);

        int[] imageView_IDs = new int[]{
                R.id.imageView_list_1,
                R.id.imageView_list_2,
                R.id.imageView_list_3,
                R.id.imageView_list_4,
                R.id.imageView_list_5,
                R.id.imageView_list_6,
                R.id.imageView_list_7,
                R.id.imageView_list_8,
                R.id.imageView_list_9,
                R.id.imageView_list_10
        };

        int[] textView_IDs = new int[]{
                R.id.textView_list_1,
                R.id.textView_list_2,
                R.id.textView_list_3,
                R.id.textView_list_4,
                R.id.textView_list_5,
                R.id.textView_list_6,
                R.id.textView_list_7,
                R.id.textView_list_8,
                R.id.textView_list_9,
                R.id.textView_list_10

        };

        int i = 0;

        final ImageView[] PlantImage = new ImageView[imageView_IDs.length];
        TextView[] PlantInfo = new TextView[textView_IDs.length];
        TextView NoPlants = view.findViewById(R.id.textView_empty_list);

        if(all_plant_info != null){

            /**Database Empty and Display Text*/
            if(all_plant_info.getCount() == 0){
                NoPlants.setText("You Don't Have Plants Yet!");

                while(i != 10){
                    PlantImage[i] = view.findViewById(imageView_IDs[i]);
                    PlantInfo[i] = view.findViewById(textView_IDs[i]);
                    PlantImage[i].setVisibility(View.GONE);
                    PlantInfo[i].setVisibility(View.GONE);
                    i++;
                }
            }else{
                NoPlants.setVisibility(View.GONE);
                while(all_plant_info.moveToNext()){

                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy hh:mm a");
                    String new_string = sdf.format(new Date(all_plant_info.getLong(2)));

                    final String boo = all_plant_info.getString(0);

                    /**OnClick Listeners to go to Plant Stats Page*/
                    PlantImage[i] = view.findViewById(imageView_IDs[i]);
                    PlantImage[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent Stats = new Intent(getActivity(), PlantStats.class);
                            Stats.putExtra("PlantName", boo);
                            startActivity(Stats);
                        }
                    });


                    PlantInfo[i] = view.findViewById(textView_IDs[i]);
                    PlantInfo[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent Stats = new Intent(getActivity(), PlantStats.class);
                            Stats.putExtra("PlantName", boo);
                            startActivity(Stats);
                        }
                    });


                    /**Long Click Listeners for deleting plant data*/
                    PlantImage[i].setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            AlertDialog.Builder DialogBox = new AlertDialog.Builder(getActivity());
                            DialogBox.setTitle(Html.fromHtml("<b>Delete Plant</b>"));
                            DialogBox.setMessage(Html.fromHtml("Do you want to delete your plant named <b>" + boo + "</b>?"));

                            /**Confirmation Message for plant deletion*/
                            DialogBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatabaseHelper DbDel = new DatabaseHelper(getActivity());
                                    final Cursor DbInfo = DbDel.get_all_info();
                                    DbInfo.moveToNext();
                                    final String PlantName = DbInfo.getString(0);
                                    DbDel.deleteData(PlantName);
                                    DbInfo.close();
                                    Intent reset = new Intent(getActivity(), MainActivity.class);
                                    reset.putExtra("Context", "List");
                                    startActivity(reset);
                                }
                            });
                            DialogBox.setNegativeButton("No", null);
                            DialogBox.show();

                            return false;
                        }
                    });

                    PlantInfo[i].setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            AlertDialog.Builder DialogBox = new AlertDialog.Builder(getActivity());
                            DialogBox.setTitle(Html.fromHtml("<b>Delete Plant</b>"));
                            DialogBox.setMessage(Html.fromHtml("Do you want to delete your plant named <b>" + boo + "</b>?"));

                            /**Confirmation Message for plant deletion*/
                            DialogBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatabaseHelper DbDel = new DatabaseHelper(getActivity());
                                    final Cursor DbInfo = DbDel.get_all_info();
                                    DbInfo.moveToNext();
                                    final String PlantName = DbInfo.getString(0);
                                    DbDel.deleteData(PlantName);
                                    DbInfo.close();
                                    Intent reset = new Intent(getActivity(), MainActivity.class);
                                    reset.putExtra("Context", "List");
                                    startActivity(reset);
                                }
                            });
                            DialogBox.setNegativeButton("No", null);
                            DialogBox.show();

                            return false;
                        }
                    });

                    if (all_plant_info.getString(1).equals("Eggplant")){
                        PlantImage[i].setImageResource(R.mipmap.ic_eggplant);
                    } else if (all_plant_info.getString(1).equals("Sweet Potato")){
                        PlantImage[i].setImageResource(R.mipmap.ic_kamote);
                    }

                    PlantInfo[i].setText(Html.fromHtml("<b>" + all_plant_info.getString(0) + "</b>" + "<br/>" +
                            all_plant_info.getString(1) + "<br/>Date Created: " + new_string));
                    i++;
                }

                while(i != 10){
                    PlantImage[i] = view.findViewById(imageView_IDs[i]);
                    PlantInfo[i] = view.findViewById(textView_IDs[i]);
                    PlantImage[i].setVisibility(View.GONE);
                    PlantInfo[i].setVisibility(View.GONE);
                    i++;
                }


            }
        }
        all_plant_info.close();
        return view;

    }
}
