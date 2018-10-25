package com.example.yousafkhan.miwok;

public class Translation {

    private String englishTranslation;
    private String miwokTranslation;
    private int imageResourceID;

    Translation(String englishTranslation,  String miwokTranslation) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
    }

    Translation(String englishTranslation,  String miwokTranslation, int imageID) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
        this.imageResourceID = imageID;
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
}
