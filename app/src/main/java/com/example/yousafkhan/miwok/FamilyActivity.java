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
        this.familyList.add(new Translation("father", "әpә", R.drawable.family_father));
        this.familyList.add(new Translation("mother", "әṭa", R.drawable.family_mother));
        this.familyList.add(new Translation("son", "angsi", R.drawable.family_son));
        this.familyList.add(new Translation("daughter", "tune", R.drawable.family_daughter));
        this.familyList.add(new Translation("older brother", "taachi", R.drawable.family_older_brother));
        this.familyList.add(new Translation("younger brother", "chalitti", R.drawable.family_younger_brother));
        this.familyList.add(new Translation("older sister", "teṭe", R.drawable.family_older_sister));
        this.familyList.add(new Translation("younger sister", "kolliti", R.drawable.family_younger_sister));
        this.familyList.add(new Translation("grandmother", "ama", R.drawable.family_grandmother));
        this.familyList.add(new Translation("grandfather", "paapa", R.drawable.family_grandfather));
    }

    private void populateFamilyListview() {
        ListView listView = findViewById(R.id.family_listview);
        FamilyAdapter familyAdapter = new FamilyAdapter(this, this.familyList);
        listView.setAdapter(familyAdapter);
    }
}
