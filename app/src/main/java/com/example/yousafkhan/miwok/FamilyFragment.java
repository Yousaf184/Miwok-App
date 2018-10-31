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


public class FamilyFragment extends Fragment implements AudioManager.OnAudioFocusChangeListener {

    private ArrayList<Translation> familyList;
    private ListView listView;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_family, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        populateFamilyList();
        populateFamilyListview();
        setListViewListener();
    }

    private void populateFamilyList() {
        this.familyList = new ArrayList<>();
        this.familyList.add(new Translation("father", "әpә", R.drawable.family_father, R.raw.family_father, R.color.darkGreen));
        this.familyList.add(new Translation("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother, R.color.darkGreen));
        this.familyList.add(new Translation("son", "angsi", R.drawable.family_son, R.raw.family_son, R.color.darkGreen));
        this.familyList.add(new Translation("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter, R.color.darkGreen));
        this.familyList.add(new Translation("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother, R.color.darkGreen));
        this.familyList.add(new Translation("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother, R.color.darkGreen));
        this.familyList.add(new Translation("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister, R.color.darkGreen));
        this.familyList.add(new Translation("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister, R.color.darkGreen));
        this.familyList.add(new Translation("grandmother", "ama", R.drawable.family_grandmother, R.raw.family_grandmother, R.color.darkGreen));
        this.familyList.add(new Translation("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather, R.color.darkGreen));
    }

    private void populateFamilyListview() {
        listView = getActivity().findViewById(R.id.family_listview);
        TranslationAdapter familyAdapter = new TranslationAdapter(getActivity(), this.familyList);
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

                // request audio focus
                audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

                int requestStatus = audioManager.requestAudioFocus(FamilyFragment.this,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(requestStatus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    Translation item = familyList.get(position);

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
                    audioManager.abandonAudioFocus(FamilyFragment.this);
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
