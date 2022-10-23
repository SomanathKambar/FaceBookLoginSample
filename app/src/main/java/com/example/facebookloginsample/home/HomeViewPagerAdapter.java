package com.example.facebookloginsample.home;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.facebookloginsample.home.Repository.HomePageRepository;

public class HomeViewPagerAdapter extends FragmentPagerAdapter {

    HomePageRepository homePageRepository;
    public  HomeViewPagerAdapter(HomePageRepository homePageRepository, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.homePageRepository = homePageRepository;
    }


    @Override
    public int getCount() {
        return homePageRepository.getTabCount();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return homePageRepository.getTitles().get(position);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new ItemFragment();
    }
}
