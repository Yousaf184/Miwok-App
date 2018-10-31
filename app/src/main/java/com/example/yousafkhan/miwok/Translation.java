package com.example.yousafkhan.miwok;

public class Translation {

    private String englishTranslation;
    private String miwokTranslation;
    private int imageResourceID;
    private boolean hasImage = true;
    private int audioResourceID;
    private int backgroundColor;

    Translation(String englishTranslation,  String miwokTranslation, int audioID, int bgColor) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
        this.audioResourceID = audioID;
        this.hasImage = false;
        this.backgroundColor = bgColor;
    }

    Translation(String englishTranslation,  String miwokTranslation, int imageID, int audioID, int bgColor) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
        this.imageResourceID = imageID;
        this.audioResourceID = audioID;
        this.backgroundColor = bgColor;
    }


    public String getEnglishTranslation() {
        return this.englishTranslation;
    }

    public String getMiwokTranslation() {
        return this.miwokTranslation;
    }

    public int getImageResourceID() {

        if(hasImage) {
            return this.imageResourceID;
        }

        return -1;
    }

    public int getAudioResourceID() {
        return this.audioResourceID;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }
}
