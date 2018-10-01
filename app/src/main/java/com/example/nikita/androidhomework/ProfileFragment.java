package com.example.nikita.androidhomework;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    public TextView tvName;
    public TextView tvEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tvName = view.findViewById(R.id.tv_name);
        tvEmail = view.findViewById(R.id.tv_mail);
        tvName.setText(NavigationActivity.name);
        tvEmail.setText(NavigationActivity.email);
        view.findViewById(R.id.btn_edit).setOnClickListener(v -> {
            EditFragment dialog = new EditFragment();
            dialog.show(getFragmentManager(), "dialog");
        });
        return view;
    }

    public static Fragment newInstance()
    {
        ProfileFragment myFragment = new ProfileFragment();
        return myFragment;
    }
}
