package com.example.nikita.androidhomework;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MealsHolder extends RecyclerView.ViewHolder {

    public String name;
    public String description;
    public int id;
    TextView tvName;
    ImageView ivPhoto;

    MealsHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
        ivPhoto = itemView.findViewById(R.id.iv_photo);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, MealActivity.class);
                intent.putExtra("name", tvName.getText());
                intent.putExtra("description", description);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });
    }

    public void bind(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
        String uri = "android.resource://com.example.nikita.androidhomework/drawable/m" + id;
        ivPhoto.setImageURI(Uri.parse(uri));
        tvName.setText(name);
    }
}
