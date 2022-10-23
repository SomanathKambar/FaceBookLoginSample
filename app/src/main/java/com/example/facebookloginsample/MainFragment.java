package com.example.facebookloginsample;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.facebookloginsample.databinding.FragmentMainBinding;
import com.example.facebookloginsample.home.HomeViewPagerAdapter;
import com.example.facebookloginsample.home.Repository.HomePageRepository;
import com.example.facebookloginsample.util.FaceBookLoginSharedPreferenceManager;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import navigation.ActivityRouter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentMainBinding binding;
    private TabLayout tabs;
    private ViewPager viewpager;

    @Inject
    ActivityRouter activityRouter;

    @Inject
    FaceBookLoginSharedPreferenceManager sharedPreferenceManager;

    @Inject
    HomePageRepository homePageRepository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(getLayoutInflater(),container, false);
        ((FaceBookLoginSampleApp)(getContext().getApplicationContext())).getComponent().inject(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupTabs();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binding.tabs.selectTab(binding.tabs.getTabAt(0));
            }
        }, 500);
    }

    private void setupTabs() {
        tabs = binding.tabs;
        viewpager = binding.viewpager;
        viewpager.setAdapter(new HomeViewPagerAdapter(homePageRepository, getChildFragmentManager()));
        tabs.setupWithViewPager(viewpager);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.view.setSelected(true);
                tab.view.setBackgroundResource(R.drawable.tabs_background);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.view.setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.view.setSelected(true);
                tab.view.setBackgroundResource(R.drawable.tabs_background);
            }
        });

}
}