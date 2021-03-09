package com.niu.cs.z1802019.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.Scanner;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //variables
        TextView recipeView = (TextView) findViewById(R.id.Recipetext);
        TextView recipeTitle = (TextView) findViewById(R.id.recipeTitle);
        Intent intent = getIntent();
        String choice = intent.getStringExtra("USER_CHOICE");
        Button returnButton = (Button) findViewById(R.id.backButton);
        //set the recipe title to the one we're working with
        recipeTitle.setText(choice);
        //begin configuring the recipeView to scroll and have the text for the recipe
        recipeView.setMovementMethod(new ScrollingMovementMethod());
        recipeView.setText(RecipeReader(choice));
        //setting up the back button
        returnButton.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //when we click, send the finish and it will return us to our main activity
                finish();
            }
        });
        //

    }
    private String RecipeReader(String name)
    {
        String output = "";
        try
        {
            StringBuilder recipe = new StringBuilder();
            Scanner reader = new Scanner(getAssets().open(name + ".txt"));
            while(reader.hasNextLine())
            {
                recipe.append(reader.nextLine() + "\n");
            }
            output = recipe.toString();
        }
        catch(Exception e)
        {
            output = "Error: This recipe couldn't be located";
        }

        return output;
    }
}