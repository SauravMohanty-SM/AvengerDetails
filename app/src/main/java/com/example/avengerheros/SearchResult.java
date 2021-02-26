package com.example.avengerheros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class SearchResult extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TabAccessAdepter mTabAccesserAdepter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

         /*   mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle("Avengers"); */

        mViewPager = (ViewPager) findViewById(R.id.main_tab_page);
        mTabAccesserAdepter = new TabAccessAdepter(getSupportFragmentManager());
        mViewPager.setAdapter(mTabAccesserAdepter);

        mTabLayout = (TabLayout) findViewById(R.id.main_tab);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}