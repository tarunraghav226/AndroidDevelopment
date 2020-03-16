package com.example.notetoself;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private boolean showDividers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences("Note To Self",MODE_PRIVATE);
        editor = preferences.edit();
        showDividers = preferences.getBoolean("dividers",true);

        Switch switch1 = (Switch)findViewById(R.id.switch1);
        switch1.setChecked(showDividers);

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    editor.putBoolean("dividers",true);
                    showDividers=true;
                }
                else{
                    editor.putBoolean("dividers",false);
                    showDividers=false;
                }
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();

        editor.commit();
    }
}
