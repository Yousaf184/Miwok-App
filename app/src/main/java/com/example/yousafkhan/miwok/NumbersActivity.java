package com.example.yousafkhan.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity implements AudioManager.OnAudioFocusChangeListener {

    private ArrayList<Translation> numbersList;
    private ListView listView;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        populateNumbersList();
        populateNumbersListView();
        setListViewListener();
    }

    private void populateNumbersList() {
        this.numbersList = new ArrayList<>();
        this.numbersList.add(new Translation("one", "lutti", R.drawable.number_one, R.raw.number_one));
        this.numbersList.add(new Translation("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        this.numbersList.add(new Translation("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        this.numbersList.add(new Translation("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        this.numbersList.add(new Translation("five", "massokka", R.drawable.number_five, R.raw.number_five));
        this.numbersList.add(new Translation("six", "temokka", R.drawable.number_six, R.raw.number_six));
        this.numbersList.add(new Translation("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        this.numbersList.add(new Translation("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        this.numbersList.add(new Translation("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        this.numbersList.add(new Translation("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));
    }

    private void populateNumbersListView() {
        listView = findViewById(R.id.numbers_listview);
        NumbersAdapter numbersAdapter = new NumbersAdapter(this, this.numbersList);
        listView.setAdapter(numbersAdapter);
    }

    private void setListViewListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // stop the previously playing audio if there's any playing already
                if(mediaPlayer != null) {
                    mediaPlayer.stop();
                }

                // request audio focus
                audioManager = (AudioManager) NumbersActivity.this.getSystemService(AUDIO_SERVICE);

                audioManager.requestAudioFocus(NumbersActivity.this,
                                                AudioManager.STREAM_MUSIC,
                                                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                Translation item = numbersList.get(position);

                mediaPlayer = MediaPlayer.create(NumbersActivity.this, item.getAudioResourceID());
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

                    // unregister the audio focus change listener
                    audioManager.abandonAudioFocus(NumbersActivity.this);
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

    @Override
    public void onAudioFocusChange(int focusChange) {
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
                // focus gained, start audio
                mediaPlayer.start();
                break;

            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                // app has lost the audio focus for short amount of time
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
                break;

            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                // app has lost audio focus for short amount of time but is allowed
                // to play audio at a low volume
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
                break;

            case AudioManager.AUDIOFOCUS_LOSS:
                // app has lost the audio focus
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
                break;
        }
    }

}
