package com.example.yousafkhan.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    private ArrayList<Translation> colorsList;
    private ListView listView;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        populateColorsList();
        populateColorsListView();
        setListViewListener();
    }

    private void populateColorsList() {
        this.colorsList = new ArrayList<>();
        this.colorsList.add(new Translation("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        this.colorsList.add(new Translation("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        this.colorsList.add(new Translation("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        this.colorsList.add(new Translation("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        this.colorsList.add(new Translation("black", "kululli", R.drawable.color_black, R.raw.color_black));
        this.colorsList.add(new Translation("white", "kalelli", R.drawable.color_white, R.raw.color_white));
        this.colorsList.add(new Translation("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        this.colorsList.add(new Translation("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
    }

    private void populateColorsListView() {
        listView = findViewById(R.id.colors_listview);
        ColorsAdapter colorsAdapter = new ColorsAdapter(this, this.colorsList);
        listView.setAdapter(colorsAdapter);
    }

    private void setListViewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // stop the previously playing audio if there's any playing already
                if(mediaPlayer != null) {
                    mediaPlayer.stop();
                }

                Translation item = colorsList.get(position);

                mediaPlayer = MediaPlayer.create(ColorsActivity.this, item.getAudioResourceID());
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
