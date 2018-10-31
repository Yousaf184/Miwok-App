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

public class ColorsFragment extends Fragment implements AudioManager.OnAudioFocusChangeListener {

    private ArrayList<Translation> colorsList;
    private ListView listView;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_colors, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        populateColorsList();
        populateColorsListView();
        setListViewListener();
    }

    private void populateColorsList() {
        this.colorsList = new ArrayList<>();
        this.colorsList.add(new Translation("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red, R.color.purple));
        this.colorsList.add(new Translation("green", "chokokki", R.drawable.color_green, R.raw.color_green, R.color.purple));
        this.colorsList.add(new Translation("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown, R.color.purple));
        this.colorsList.add(new Translation("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray, R.color.purple));
        this.colorsList.add(new Translation("black", "kululli", R.drawable.color_black, R.raw.color_black, R.color.purple));
        this.colorsList.add(new Translation("white", "kalelli", R.drawable.color_white, R.raw.color_white, R.color.purple));
        this.colorsList.add(new Translation("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow, R.color.purple));
        this.colorsList.add(new Translation("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow, R.color.purple));
    }

    private void populateColorsListView() {
        listView = getActivity().findViewById(R.id.colors_listview);
        TranslationAdapter colorsAdapter = new TranslationAdapter(getActivity(), this.colorsList);
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

                // request audio focus
                audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

                int requestStatus = audioManager.requestAudioFocus(ColorsFragment.this,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                // if audio focus request granted
                if(requestStatus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    Translation item = colorsList.get(position);

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
                    audioManager.abandonAudioFocus(ColorsFragment.this);
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
