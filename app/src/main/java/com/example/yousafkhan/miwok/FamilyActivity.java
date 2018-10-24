package com.example.yousafkhan.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private ArrayList<Translation> familyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        populateFamilyList();
        populateFamilyListview();
    }

    private void populateFamilyList() {
        this.familyList = new ArrayList<>();
        this.familyList.add(new Translation("father", "әpә"));
        this.familyList.add(new Translation("mother", "әṭa"));
        this.familyList.add(new Translation("son", "angsi"));
        this.familyList.add(new Translation("daughter", "tune"));
        this.familyList.add(new Translation("older brother", "taachi"));
        this.familyList.add(new Translation("younger brother", "chalitti"));
        this.familyList.add(new Translation("older sister", "teṭe"));
        this.familyList.add(new Translation("younger sister", "kolliti"));
        this.familyList.add(new Translation("grandmother", "ama"));
        this.familyList.add(new Translation("grandfather", "paapa"));
    }

    private void populateFamilyListview() {
        ListView listView = findViewById(R.id.family_listview);
        FamilyAdapter familyAdapter = new FamilyAdapter(this, this.familyList);
        listView.setAdapter(familyAdapter);
    }
}
