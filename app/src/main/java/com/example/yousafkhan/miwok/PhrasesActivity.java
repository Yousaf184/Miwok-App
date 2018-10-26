package com.example.yousafkhan.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity implements AudioManager.OnAudioFocusChangeListener {

    private ArrayList<Translation> phrasesList;
    private ListView listView;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

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

                // request audio focus
                audioManager = (AudioManager) PhrasesActivity.this.getSystemService(AUDIO_SERVICE);

                int requestStatus = audioManager.requestAudioFocus(PhrasesActivity.this,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(requestStatus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    Translation item = phrasesList.get(position);

                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, item.getAudioResourceID());
                    mediaPlayer.start();

                    // set audio completion listener on newly created MediaPlayer instance
                    setAudioCompletionListener();
                }
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
                    audioManager.abandonAudioFocus(PhrasesActivity.this);
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
