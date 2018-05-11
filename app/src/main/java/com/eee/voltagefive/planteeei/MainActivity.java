package com.eee.voltagefive.planteeei;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static MainActivity MyMainContext;
    DatabaseHelper myDb;
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.home_home:
                    selectedFragment = HomeFragment.newInstance();
                    break;
                case R.id.home_about:
                     selectedFragment = AboutFragment.newInstance();
                    break;
                case R.id.home_list:
                    selectedFragment = ListFragment.newInstance();
                    break;

            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, selectedFragment);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MyMainContext = this;

        myDb = new DatabaseHelper(this); //create or open database and table

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(getIntent().getStringExtra("Context") != null) {
            navigation.getMenu().getItem(1).setChecked(true);
            transaction.replace(R.id.frame_layout, ListFragment.newInstance());
        }else //home is default
            transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();


        /**
         * Used to select an item programmatically
         *
         * navigation.getMenu().getItem(x).setChecked(true);
         *
         * replace x with 1, 2, 3 where in this case:
         * 0 - home (default)
         * 1 - List
         * 2 - About
         *
         * This highlights the corresponding tab in bottom navigation view
         */
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder DialogBox = new AlertDialog.Builder(this);
        DialogBox.setTitle(Html.fromHtml("<b>Confirm App Closure</b>"));
        DialogBox.setMessage("Are you sure you want to exit?");

        /**Positive and Negative Buttons use color/colorAccent by default*/
        DialogBox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.super.onBackPressed();
                    }
                });
        DialogBox.setNegativeButton("No", null);

        DialogBox.show();
    }


}
