package com.example.nikita.androidhomework;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MealsAdapter extends RecyclerView.Adapter<MealsHolder> {

    private ArrayList<Meal> mealsList;

    public MealsAdapter(ArrayList<Meal> list) {
        this.mealsList = list;
    }

    @Override
    public MealsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        return new MealsHolder(v);
    }

    @Override
    public void onBindViewHolder(MealsHolder holder, int position) {
        holder.bind(mealsList.get(position).name, mealsList.get(position).description, mealsList.get(position).id);
    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }
}
