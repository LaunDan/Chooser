package com.laun.chooser;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

public class ChooserActivity extends AppCompatActivity {

    // todo make button in menu for print list of chooses in some random order

    // todo make mylogo at welcome activity to look bigger and was in the middle of screen
    
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

    private void exit() {
        Intent intent = new Intent(ChooserActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_chooser, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.end:
                        exit();
                        return true;

                    default:
                        return false;
                }
            }
        });

    }
}
