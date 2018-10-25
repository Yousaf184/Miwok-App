package com.example.yousafkhan.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private ArrayList<Translation> colorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        populateColorsList();
        populateColorsListView();
    }

    private void populateColorsList() {
        this.colorsList = new ArrayList<>();
        this.colorsList.add(new Translation("red", "weṭeṭṭi", R.drawable.color_red));
        this.colorsList.add(new Translation("green", "chokokki", R.drawable.color_green));
        this.colorsList.add(new Translation("brown", "ṭakaakki", R.drawable.color_brown));
        this.colorsList.add(new Translation("gray", "ṭopoppi", R.drawable.color_gray));
        this.colorsList.add(new Translation("black", "kululli", R.drawable.color_black));
        this.colorsList.add(new Translation("white", "kalelli", R.drawable.color_white));
        this.colorsList.add(new Translation("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow));
        this.colorsList.add(new Translation("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow));
    }

    private void populateColorsListView() {
        ListView listView = findViewById(R.id.colors_listview);
        ColorsAdapter colorsAdapter = new ColorsAdapter(this, this.colorsList);
        listView.setAdapter(colorsAdapter);
    }
}
