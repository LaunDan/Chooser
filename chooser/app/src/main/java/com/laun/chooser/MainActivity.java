package com.laun.chooser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private String choices;
    private EditText choicesET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        choicesET = findViewById(R.id.choicesET);
    }

    public void choose(View view) {
        choices = choicesET.getText().toString();
        Intent chooser = new Intent(this, ChooserActivity.class);
        chooser.putExtra("CHOICES", choices);
        startActivity(chooser);
    }

    public void dellAll(View view) {
        choicesET.setText(null);
    }
}
