package com.example.nikita.androidhomework;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class CharactersAdapter extends RecyclerView.Adapter<CharactersHolder> {

    public ArrayList<Character> charList;

    public CharactersAdapter(ArrayList<Character> list) {
        this.charList = list;
    }

    @Override
    public CharactersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char, parent, false);
        return new CharactersHolder(v);
    }

    @Override
    public void onBindViewHolder(CharactersHolder holder, int position) {
        holder.bind(charList.get(position).name, charList.get(position).description, charList.get(position).id);
    }

    @Override
    public int getItemCount() {
        return charList.size();
    }

    public void updateList(ArrayList<Character> newList) {
        final DiffCallback diffCallback = new DiffCallback(this.charList, newList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.charList.clear();
        this.charList.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }
}