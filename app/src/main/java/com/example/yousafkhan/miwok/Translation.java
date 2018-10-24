package com.example.yousafkhan.miwok;

public class Translation {

    private String englishTranslation;
    private String miwokTranslation;

    Translation(String englishTranslation,  String miwokTranslation) {
        this.englishTranslation = englishTranslation;
        this.miwokTranslation = miwokTranslation;
    }

    public String getEnglishTranslation() {
        return this.englishTranslation;
    }

    public String getMiwokTranslation() {
        return this.miwokTranslation;
    }
}
