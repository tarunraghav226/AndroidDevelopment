package com.example.agedatabase;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_result, container, false);

        DataManager dataManager = new DataManager(getActivity());
        TextView textResult = (TextView) v.findViewById(R.id.textResults);
        Cursor c = dataManager.selectAll();

        String list = "";

        while (c.moveToNext()) {
            list += (c.getString(1) + "-->" + c.getString(2) + "\n");
        }

        textResult.setText(list);

        return v;
    }
}
