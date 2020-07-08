package com.laun.chooser;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChooserActivity extends AppCompatActivity {

    // todo make button for end of app

    private TextView choiceTV;
    private String choices;
    private String theChoice;
    private int randNum;
    private String[] choice;
    private int amountOfChoices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chooser);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        choiceTV = findViewById(R.id.choice);
        choices = getIntent().getStringExtra("CHOICES");

        assert choices != null;
        choose(choices);
    }

    private void choose(String choices) {
        if (choice == null) {
            choice = choices.split("\n");
        }
        amountOfChoices = choice.length;
        Dice dice = new Dice(amountOfChoices);
        randNum = dice.throwed() - 1;
        theChoice = choice[randNum];
        choiceTV.setText(theChoice);
        if (choice.length == 1) {
            Toast.makeText(ChooserActivity.this,
                    "Your entered only one choice!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void chooseAgain(View view) {
        choose(choices);
    }

    public void finish(View view) {
        finish();
    }

    public void chooseFromOthers(View view) {
        if (choice.length >= 2) {
            choice = removeTheElement(choice, randNum);
            amountOfChoices = choice.length;
            Dice dice = new Dice(amountOfChoices);
            randNum = dice.throwed() - 1;
            theChoice = choice[randNum];
            choiceTV.setText(theChoice);
        } else {
            Toast.makeText(ChooserActivity.this,
                    "This is your last choice!",
                    Toast.LENGTH_LONG).show();
        }


    }

    public static String[] removeTheElement(String[] arr, int index) {

        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null
                || index < 0
                || index >= arr.length) {

            return arr;
        }

        // Create another array of size one less
        String[] anotherArray = new String[arr.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {

            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }

            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }

        // return the resultant array
        return anotherArray;
    }

}
