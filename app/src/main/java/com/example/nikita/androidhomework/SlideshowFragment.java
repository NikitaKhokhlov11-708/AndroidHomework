package com.example.nikita.androidhomework;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class SlideshowFragment extends Fragment {
	
	public static Fragment newInstance() {
        SlideshowFragment myFragment = new SlideshowFragment();
        return myFragment;
    }
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);
        return view;
    }
}
