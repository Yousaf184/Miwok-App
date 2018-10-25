package com.example.yousafkhan.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    private ArrayList<Translation> familyList;
    private ListView listView;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);

        populateFamilyList();
        populateFamilyListview();
        setListViewListener();
    }

    private void populateFamilyList() {
        this.familyList = new ArrayList<>();
        this.familyList.add(new Translation("father", "әpә", R.drawable.family_father, R.raw.family_father));
        this.familyList.add(new Translation("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        this.familyList.add(new Translation("son", "angsi", R.drawable.family_son, R.raw.family_son));
        this.familyList.add(new Translation("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        this.familyList.add(new Translation("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        this.familyList.add(new Translation("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        this.familyList.add(new Translation("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        this.familyList.add(new Translation("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        this.familyList.add(new Translation("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        this.familyList.add(new Translation("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));
    }

    private void populateFamilyListview() {
        listView = findViewById(R.id.family_listview);
        FamilyAdapter familyAdapter = new FamilyAdapter(this, this.familyList);
        listView.setAdapter(familyAdapter);
    }

    private void setListViewListener() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // stop the previously playing audio if there's any playing already
                if(mediaPlayer != null) {
                    mediaPlayer.stop();
                }

                Translation item = familyList.get(position);

                mediaPlayer = MediaPlayer.create(FamilyActivity.this, item.getAudioResourceID());
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

    @Override
    protected void onStop() {
        super.onStop();

        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
