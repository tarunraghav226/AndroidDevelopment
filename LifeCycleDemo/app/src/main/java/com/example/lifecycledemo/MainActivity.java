package com.example.lifecycledemo;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

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
        Toast.makeText(this, "In onCreate", Toast.LENGTH_SHORT).show();
        Log.i("info", "In onCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(this, "In onStart", Toast.LENGTH_SHORT).show();
        Log.i("info", "In onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(this, "In onResume", Toast.LENGTH_SHORT).show();
        Log.i("info", "In onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(this, "In onPause", Toast.LENGTH_SHORT).show();
        Log.i("info", "In onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(this, "In onStop", Toast.LENGTH_SHORT).show();
        Log.i("info", "In onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "In onDestroy", Toast.LENGTH_SHORT).show();
        Log.i("info", "In onDestroy");
    }
}
