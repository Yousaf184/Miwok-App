package com.example.yousafkhan.miwok;

public class Translation {

    private String englishTranslation;
    private String miwokTranslation;
    private int imageResourceID;
    private int audioResourceID;

    Translation(String englishTranslation,  String miwokTranslation, int audioID) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
        this.audioResourceID = audioID;
    }

    Translation(String englishTranslation,  String miwokTranslation, int imageID, int audioID) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
        this.imageResourceID = imageID;
        this.audioResourceID = audioID;
    }


    public String getEnglishTranslation() {
        return this.englishTranslation;
    }

    public String getMiwokTranslation() {
        return this.miwokTranslation;
    }

    public int getImageResourceID() {
        return this.imageResourceID;
    }

    public int getAudioResourceID() {
        return this.audioResourceID;
    }
}
