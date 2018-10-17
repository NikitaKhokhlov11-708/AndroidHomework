package com.example.nikita.androidhomework;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class DiffCallback extends DiffUtil.Callback {

    private final List<Character> mOldEmployeeList;
    private final List<Character> mNewEmployeeList;

    public DiffCallback(List<Character> oldEmployeeList, List<Character> newEmployeeList) {
        this.mOldEmployeeList = oldEmployeeList;
        this.mNewEmployeeList = newEmployeeList;
    }

    @Override
    public int getOldListSize() {
        return mOldEmployeeList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewEmployeeList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldEmployeeList.get(oldItemPosition).id == mNewEmployeeList.get(
                newItemPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Character oldEmployee = mOldEmployeeList.get(oldItemPosition);
        final Character newEmployee = mNewEmployeeList.get(newItemPosition);

        return oldEmployee.name.equals(newEmployee.name);
    }
}