package com.laun.chooser;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

public class MainActivity extends AppCompatActivity {

    private EditText choicesET;

    // todo make button for choose in ordered list in random places

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        choicesET = findViewById(R.id.choicesET);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
    }

    public void choose(View view) {
        if (!isEmpty()) {
            String choices = choicesET.getText().toString();
            Intent chooser = new Intent(this, ChooserActivity.class);
            chooser.putExtra("CHOICES", choices);
            startActivity(chooser);
        } else {
            Toast.makeText(MainActivity.this,
                    "You must enter some choices!",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void dellAll(View view) {
        choicesET.setText(null);
    }

    public void finish(View view) {
        finish();
    }

    private boolean isEmpty() {
        String lengthOfText = choicesET.getText().toString();
        if (lengthOfText.length() < 1) {
            return true;
        } else return false;
    }

    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.show();
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.end:
                        finish();
                        return true;

                    default:
                        return false;
                }
            }
        });

    }

}
