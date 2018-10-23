package com.example.yousafkhan.miwok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openNumbersActivity(View v) {
        Intent numbersActivity = new Intent(this, NumbersActivity.class);
        startActivity(numbersActivity);
    }

    public void openFamilyActivity(View v) {
        Intent familyActivity = new Intent(this, FamilyActivity.class);
        startActivity(familyActivity);
    }

    public void openColorsActivity(View v) {
        Intent colorsActivity = new Intent(this, ColorsActivity.class);
        startActivity(colorsActivity);
    }

    public void openPhrasesActivity(View v) {
        Intent phrasesActivity = new Intent(this, PhrasesActivity.class);
        startActivity(phrasesActivity);
    }
}
