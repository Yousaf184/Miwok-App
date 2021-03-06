package com.example.yousafkhan.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class NumbersFragment extends Fragment implements AudioManager.OnAudioFocusChangeListener {

    private ArrayList<Translation> numbersList;
    private ListView listView;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = null;
        try {
            rootView = inflater.inflate(R.layout.fragment_numbers, container, false);
            // ... rest of body of onCreateView() ...
        } catch (Exception e) {
            Log.d("yousafcheckfragment", "onCreateView", e);
            throw e;
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        populateNumbersList();
        populateNumbersListView();
        setListViewListener();
    }

    private void populateNumbersList() {
        this.numbersList = new ArrayList<>();
        this.numbersList.add(new Translation("one", "lutti", R.drawable.number_one, R.raw.number_one, R.color.darkOrange));
        this.numbersList.add(new Translation("two", "otiiko", R.drawable.number_two, R.raw.number_two, R.color.darkOrange));
        this.numbersList.add(new Translation("three", "tolookosu", R.drawable.number_three, R.raw.number_three, R.color.darkOrange));
        this.numbersList.add(new Translation("four", "oyyisa", R.drawable.number_four, R.raw.number_four, R.color.darkOrange));
        this.numbersList.add(new Translation("five", "massokka", R.drawable.number_five, R.raw.number_five, R.color.darkOrange));
        this.numbersList.add(new Translation("six", "temokka", R.drawable.number_six, R.raw.number_six, R.color.darkOrange));
        this.numbersList.add(new Translation("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven, R.color.darkOrange));
        this.numbersList.add(new Translation("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight, R.color.darkOrange));
        this.numbersList.add(new Translation("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine, R.color.darkOrange));
        this.numbersList.add(new Translation("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten, R.color.darkOrange));
    }

    private void populateNumbersListView() {
        listView = getActivity().findViewById(R.id.numbers_listview);
        TranslationAdapter numbersAdapter = new TranslationAdapter(getActivity(), this.numbersList);
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
                audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

                int requestStatus = audioManager.requestAudioFocus(NumbersFragment.this,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(requestStatus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    Translation item = numbersList.get(position);

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
                    audioManager.abandonAudioFocus(NumbersFragment.this);
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
