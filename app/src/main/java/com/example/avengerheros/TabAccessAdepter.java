package com.example.avengerheros;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAccessAdepter extends FragmentPagerAdapter {


    public TabAccessAdepter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)
        {
            case 0:
                Appearance appearance = new Appearance();
                return appearance;
            case 1:
                powerStarts PowerStarts = new powerStarts();
                return PowerStarts;
            case 2:
                Biography biography = new Biography();
                return biography;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:
                return "Appearance";
            case 1:
                return "Super Power";
            case 2:
                return "Biography";
            default:
                return null;
        }
    }
}
