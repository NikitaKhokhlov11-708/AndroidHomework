package com.example.nikita.androidhomework;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class CharActivity extends AppCompatActivity {

    String name;
    String description;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        description = intent.getStringExtra("description");
        id = intent.getIntExtra("id", 0);
        TextView tvFname = findViewById(R.id.tv_fname);
        TextView tvFdescription = findViewById(R.id.tv_fdescription);
        TextView tvFid = findViewById(R.id.tv_fid);
        ImageView ivFphoto = findViewById(R.id.iv_fphoto);
        tvFname.setText("Name: " + name);
        tvFdescription.setText("Description: " + description);
        tvFid.setText("ID: " + id);
        String uri = "android.resource://com.example.nikita.androidhomework/drawable/m" + id;
        ivFphoto.setImageURI(Uri.parse(uri));
    }
}