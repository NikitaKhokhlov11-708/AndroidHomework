package com.example.nikita.androidhomework;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class EditFragment extends DialogFragment {
    private EditText email;
    private EditText name;
    private OnFieldsFill listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_edit, null);
        email = view.findViewById(R.id.et_email);
        name = view.findViewById(R.id.et_name);
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
        adb.setTitle("Title")
                .setView(view)
                .setPositiveButton("Apply", (dialog, which) -> {
                    listener.onOkClick(email.getText().toString(), name.getText().toString());
                })
                .setNegativeButton("No, Thanks", (dialog, which) -> {
                    dismiss();
                });
        return adb.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NavigationActivity) {
            listener = (OnFieldsFill) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFieldsFillListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    interface OnFieldsFill {
        void onOkClick(String email, String name);
    }
}
