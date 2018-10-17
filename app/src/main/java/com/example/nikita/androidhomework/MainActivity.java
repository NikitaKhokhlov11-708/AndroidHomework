package com.example.nikita.androidhomework;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements RecyclerFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView =
                findViewById(R.id.bottom_navigation);

        RecyclerFragment fragment = new RecyclerFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_favorites:
                                RecyclerFragment fragment = new RecyclerFragment();
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, fragment)
                                        .commit();
                            case R.id.action_schedules:

                            case R.id.action_music:
                        }
                        return true;
                    }
                });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //you can leave it empty
    }
}
