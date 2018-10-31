package com.example.yousafkhan.miwok;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(new NumbersFragment());
        fragmentList.add(new FamilyFragment());
        fragmentList.add(new ColorsFragment());
        fragmentList.add(new PhrasesFragment());

        TranslationPagerAdapter tpAdapter = new TranslationPagerAdapter(getSupportFragmentManager(), fragmentList);

        //set up viewpager
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(tpAdapter);

        // set up tab layout
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
