package com.example.agedatabase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class DeleteFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_delete, container, false);

        final DataManager dataManager = new DataManager(getActivity());

        Button btnDelete = (Button) v.findViewById(R.id.btnDelete);

        final EditText editDelete = (EditText) v.findViewById(R.id.editDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataManager.delete(editDelete.getText().toString());
            }
        });

        return v;
    }
}