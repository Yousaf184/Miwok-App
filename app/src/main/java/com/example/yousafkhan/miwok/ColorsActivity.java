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
        this.colorsList.add(new Translation("red", "weṭeṭṭi"));
        this.colorsList.add(new Translation("green", "chokokki"));
        this.colorsList.add(new Translation("brown", "ṭakaakki"));
        this.colorsList.add(new Translation("gray", "ṭopoppi"));
        this.colorsList.add(new Translation("black", "kululli"));
        this.colorsList.add(new Translation("white", "kalelli"));
        this.colorsList.add(new Translation("dusty yellow", "ṭopiisә"));
        this.colorsList.add(new Translation("mustard yellow", "chiwiiṭә"));
    }

    private void populateColorsListView() {
        ListView listView = findViewById(R.id.colors_listview);
        ColorsAdapter colorsAdapter = new ColorsAdapter(this, this.colorsList);
        listView.setAdapter(colorsAdapter);
    }
}
