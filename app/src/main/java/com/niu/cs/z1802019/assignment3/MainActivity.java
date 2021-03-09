package com.niu.cs.z1802019.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity
{
    private ArrayList<String> fileLocations = new ArrayList<String>(); // used for managing our txt files
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //create
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //variables
        final Button go = (Button) findViewById(R.id.button);
        TextView title = (TextView) findViewById(R.id.titleText);
        TextView dessertNameText = (TextView) findViewById(R.id.dessertNameText);
        Spinner chooser = findViewById(R.id.spinner);
        ImageView image = (ImageView) findViewById(R.id.imageView);
        //We use loading to dynamically create what recipes we have installed in our app
        Loading();

        //implement the click listener for the go button
        // this will make us switch to our next activity, populated by the choice from spinner
        go.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                nextActivity(v, chooser.getSelectedItem().toString());

            }
        });
        // implement spinner
        // context is the main app, with a simple spinner dropdown, using FileLocations as the string arraylist
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, fileLocations);
        chooser.setAdapter(adapter);
        chooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
            {
                int imgId = getResources().getIdentifier(chooser.getSelectedItem().toString() + "_img", "drawable", getPackageName());
                //image.setImageResource(R.drawable.brownie_img);
                image.setImageResource(imgId);
                dessertNameText.setText(chooser.getSelectedItem().toString());
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }

        });
    }
    /**********************************************
     *loading
     * inputs: none
     * outputs: none
     * purpose: To read a file that contains the names
     * of all the recipies we are using and for our
     * spinner to be loaded with names
     **********************************************/
    private void Loading()
    {
        try
        {

            Scanner reader = new Scanner(getAssets().open("list.txt"));
            String line = "";
            while(reader.hasNextLine())
            {
                fileLocations.add(reader.nextLine());
            }
        }
        catch(Exception e)
        {
            Toast toast = Toast.makeText(getApplicationContext(),"recipeList not Found", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void nextActivity(View view, String choice)
    {
       Intent intent = new Intent(this, SecondActivity.class);
       intent.putExtra("USER_CHOICE", choice);
       startActivity(intent);
    }

}