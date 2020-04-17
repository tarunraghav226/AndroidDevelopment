package com.example.randomname;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class StudentAlertFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.new_student_layout, null);

        final EditText name = (EditText) view.findViewById(R.id.name);
        final EditText section = (EditText) view.findViewById(R.id.section);
        final EditText year = (EditText) view.findViewById(R.id.year);

        Button btnSave = (Button) view.findViewById(R.id.button3);

        builder.setView(view).setMessage("Insert Data");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager manager = new DataManager(getContext());
                manager.insert(name.getText().toString(), section.getText().toString(), year.getText().toString());
                dismiss();
            }
        });

        return builder.create();
    }
}
