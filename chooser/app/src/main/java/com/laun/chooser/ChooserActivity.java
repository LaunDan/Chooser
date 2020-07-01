package com.laun.chooser;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ChooserActivity extends AppCompatActivity {
    // todo repair method choosefromothers
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

        choose(choices);
    }

    private void choose(String choices) {
        choice = choices.split("\n");
        amountOfChoices = choice.length;
        Dice dice = new Dice(amountOfChoices);
        randNum = dice.throwed() - 1;
        theChoice = choice[randNum];
        choiceTV.setText(theChoice);
    }

    public void chooseAgain(View view) {
        choose(choices);
    }

    public void finish(View view) {
        finish();
    }

    public void chooseFromOthers(View view) {
        if(choice.length >= 2) {
            choice = removeTheElement(choice, randNum);
            amountOfChoices = choice.length;
            Dice dice = new Dice(amountOfChoices);
            randNum = dice.throwed() - 1;
            theChoice = choice[randNum];
            choiceTV.setText(theChoice);
        }

    }

    

}
