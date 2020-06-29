package com.laun.chooser;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChooserActivity extends AppCompatActivity {
// todo make button for choose from others
    private TextView choiceTV;
    private String choices;

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
        String[] choice = choices.split("\n");
        int amountOfChoices = choice.length;
        Dice dice = new Dice(amountOfChoices);
        String theChoice = choice[dice.throwed() - 1];
        choiceTV.setText(theChoice);
    }

    public void chooseAgain(View view) {
        choose(choices);
    }

    public void finish(View view) {
        finish();
    }

}
