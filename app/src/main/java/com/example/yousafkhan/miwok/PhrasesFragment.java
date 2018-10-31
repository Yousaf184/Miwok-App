package com.example.yousafkhan.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesFragment extends Fragment implements AudioManager.OnAudioFocusChangeListener {

    private ArrayList<Translation> phrasesList;
    private ListView listView;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phrases, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        populatePhrasesList();
        populatePhrasesListview();
        setListViewListener();
    }

    private void populatePhrasesList() {
        this.phrasesList = new ArrayList<>();
        this.phrasesList.add(new Translation("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going, R.color.darkBlue));
        this.phrasesList.add(new Translation("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name, R.color.darkBlue));
        this.phrasesList.add(new Translation("My name is...", "oyaaset...", R.raw.phrase_my_name_is, R.color.darkBlue));
        this.phrasesList.add(new Translation("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling, R.color.darkBlue));
        this.phrasesList.add(new Translation("I am feeling good", "kuchi achit?", R.raw.phrase_im_feeling_good, R.color.darkBlue));
        this.phrasesList.add(new Translation("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming, R.color.darkBlue));
        this.phrasesList.add(new Translation("Yes, i am coming", "hәә’ әәnәm", R.raw.phrase_yes_im_coming, R.color.darkBlue));
        this.phrasesList.add(new Translation("I am coming", "әәnәm", R.raw.phrase_im_coming, R.color.darkBlue));
        this.phrasesList.add(new Translation("Let's go", "yoowutis", R.raw.phrase_lets_go, R.color.darkBlue));
        this.phrasesList.add(new Translation("Come here", "әnni'nem", R.raw.phrase_come_here, R.color.darkBlue));
    }

    private void populatePhrasesListview() {
        listView = getActivity().findViewById(R.id.phrases_listview);
        TranslationAdapter phrasesAdapter = new TranslationAdapter(getActivity(), this.phrasesList);
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
                audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

                int requestStatus = audioManager.requestAudioFocus(PhrasesFragment.this,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(requestStatus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    Translation item = phrasesList.get(position);

                    mediaPlayer = MediaPlayer.create(getActivity(), item.getAudioResourceID());
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
                    audioManager.abandonAudioFocus(PhrasesFragment.this);
                }
            }
        });
    }

    @Override
    public void onStop() {
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
