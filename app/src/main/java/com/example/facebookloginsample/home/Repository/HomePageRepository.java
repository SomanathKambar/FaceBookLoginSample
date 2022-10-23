package com.example.facebookloginsample.home.Repository;

import java.util.ArrayList;
import java.util.List;

public class HomePageRepository {
    private int tabCount = 4;
    private List<String> titles = new ArrayList<>();

   public HomePageRepository() {
       initData();
   }

    private void initData() {
       titles.add("ALL");
       titles.add("Grammar");
       titles.add("Spelling");
       titles.add("Times in English");
    }

    public int getTabCount() {
        return tabCount;
    }

    public List<String> getTitles() {
       return titles;
    }
}
