package com.example.soundapp;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int idFX1 = -1;
    int idFX2 = -1;
    int idFX3 = -1;
    int nowPlaying = -1;
    int repeat = 2;
    float volume = .1f;
    private SoundPool sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button FX1 = (Button) findViewById(R.id.FX1);
        Button FX2 = (Button) findViewById(R.id.FX2);
        Button FX3 = (Button) findViewById(R.id.FX3);
        Button stop = (Button) findViewById(R.id.btnStop);

        FX1.setOnClickListener(this);
        FX2.setOnClickListener(this);
        FX3.setOnClickListener(this);
        stop.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes =
                    new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build();
            sp = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        try {
            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("fx1.ogg");
            idFX1 = sp.load(descriptor, 0);

            descriptor = assetManager.openFd("fx2.ogg");
            idFX2 = sp.load(descriptor, 0);

            descriptor = assetManager.openFd("fx3.ogg");
            idFX3 = sp.load(descriptor, 0);

        } catch (IOException e) {
            Log.e("error", "failed to load sound files");
        }

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float volume = progress / 10f;
                sp.setVolume(nowPlaying, volume, volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                repeat = Integer.valueOf(String.valueOf(spinner.getSelectedItem()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.FX1:
                sp.stop(nowPlaying);
                nowPlaying = sp.play(idFX1, volume, volume, 0, repeat, 1);
                break;
            case R.id.FX2:
                sp.stop(nowPlaying);
                nowPlaying = sp.play(idFX2,
                        volume, volume, 0, repeat, 1);
                break;
            case R.id.FX3:
                sp.stop(nowPlaying);
                nowPlaying = sp.play(idFX3, volume, volume, 0, repeat, 1);
                break;
            case R.id.btnStop:
                sp.stop(nowPlaying);
                break;
        }
    }
}
