package com.example.yousafkhan.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private ArrayList<Translation> phrasesList;
    private ListView listView;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        populatePhrasesList();
        populatePhrasesListview();
        setListViewListener();
    }

    private void populatePhrasesList() {
        this.phrasesList = new ArrayList<>();
        this.phrasesList.add(new Translation("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        this.phrasesList.add(new Translation("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        this.phrasesList.add(new Translation("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        this.phrasesList.add(new Translation("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        this.phrasesList.add(new Translation("I am feeling good", "kuchi achit?", R.raw.phrase_im_feeling_good));
        this.phrasesList.add(new Translation("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        this.phrasesList.add(new Translation("Yes, i am coming", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        this.phrasesList.add(new Translation("I am coming", "әәnәm", R.raw.phrase_im_coming));
        this.phrasesList.add(new Translation("Let's go", "yoowutis", R.raw.phrase_lets_go));
        this.phrasesList.add(new Translation("Come here", "әnni'nem", R.raw.phrase_come_here));
    }

    private void populatePhrasesListview() {
        listView = findViewById(R.id.phrases_listview);
        PhrasesAdapter phrasesAdapter = new PhrasesAdapter(this, this.phrasesList);
        listView.setAdapter(phrasesAdapter);
    }

    private void setListViewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // stop the previously playing audio if there's any playing already
                if(mediaPlayer != null) {
                    mediaPlayer.stop();
                }

                Translation item = phrasesList.get(position);

                mediaPlayer = MediaPlayer.create(PhrasesActivity.this, item.getAudioResourceID());
                mediaPlayer.start();

                // set audio completion listener on newly created MediaPlayer instance
                setAudioCompletionListener();
            }
        });
    }

    private void setAudioCompletionListener() {

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(mediaPlayer != null) {
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
            }
        });
    }
}
