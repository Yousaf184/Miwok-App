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
        this.numbersList.add(new Translation("one", "lutti", R.drawable.number_one));
        this.numbersList.add(new Translation("two", "otiiko", R.drawable.number_two));
        this.numbersList.add(new Translation("three", "tolookosu", R.drawable.number_three));
        this.numbersList.add(new Translation("four", "oyyisa", R.drawable.number_four));
        this.numbersList.add(new Translation("five", "massokka", R.drawable.number_five));
        this.numbersList.add(new Translation("six", "temokka", R.drawable.number_six));
        this.numbersList.add(new Translation("seven", "kenekaku", R.drawable.number_seven));
        this.numbersList.add(new Translation("eight", "kawinta", R.drawable.number_eight));
        this.numbersList.add(new Translation("nine", "wo'e", R.drawable.number_nine));
        this.numbersList.add(new Translation("ten", "na'aacha", R.drawable.number_ten));
    }

    private void populateNumbersListView() {
        ListView listView = findViewById(R.id.numbers_listview);
        NumbersAdapter numbersAdapter = new NumbersAdapter(this, this.numbersList);
        listView.setAdapter(numbersAdapter);
    }

}
