package com.example.yousafkhan.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private ArrayList<Translation> phrasesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        populatePhrasesList();
        populatePhrasesListview();
    }

    private void populatePhrasesList() {
        this.phrasesList = new ArrayList<>();
        this.phrasesList.add(new Translation("Where are you going?", "minto wuksus"));
        this.phrasesList.add(new Translation("What is your name?", "tinnә oyaase'nә"));
        this.phrasesList.add(new Translation("My name is...", "oyaaset..."));
        this.phrasesList.add(new Translation("How are you feeling?", "michәksәs?"));
        this.phrasesList.add(new Translation("I am feeling good", "kuchi achit?"));
        this.phrasesList.add(new Translation("Are you coming?", "әәnәs'aa?"));
        this.phrasesList.add(new Translation("Yes, i am coming", "hәә’ әәnәm"));
        this.phrasesList.add(new Translation("I am coming", "әәnәm"));
        this.phrasesList.add(new Translation("Let's go", "yoowutis"));
        this.phrasesList.add(new Translation("Come here", "әnni'nem"));
    }

    private void populatePhrasesListview() {
        ListView listView = findViewById(R.id.phrases_listview);
        PhrasesAdapter phrasesAdapter = new PhrasesAdapter(this, this.phrasesList);
        listView.setAdapter(phrasesAdapter);
    }
}
