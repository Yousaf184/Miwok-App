package com.example.yousafkhan.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private ArrayList<Translation> numbersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        populateNumbersList();
        populateNumbersListView();
    }

    private void populateNumbersList() {
        this.numbersList = new ArrayList<>();
        this.numbersList.add(new Translation("one", "lutti"));
        this.numbersList.add(new Translation("two", "otiiko"));
        this.numbersList.add(new Translation("three", "tolookosu"));
        this.numbersList.add(new Translation("four", "oyyisa"));
        this.numbersList.add(new Translation("five", "massokka"));
        this.numbersList.add(new Translation("six", "temokka"));
        this.numbersList.add(new Translation("seven", "kenekaku"));
        this.numbersList.add(new Translation("eight", "kawinta"));
        this.numbersList.add(new Translation("nine", "wo'e"));
        this.numbersList.add(new Translation("ten", "na'aacha"));
    }

    private void populateNumbersListView() {
        ListView listView = findViewById(R.id.numbersList);
        NumbersAdapter numbersAdapter = new NumbersAdapter(this, this.numbersList);
        listView.setAdapter(numbersAdapter);
    }

}
