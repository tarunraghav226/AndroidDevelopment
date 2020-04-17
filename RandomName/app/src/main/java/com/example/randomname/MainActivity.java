package com.example.randomname;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Student> arrayList;
    private DataManager dataManager;

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

        arrayList = new ArrayList<>();
        getAllStudents(arrayList);

        final TextView textView = (TextView) findViewById(R.id.textView);
        final Button btnNextStudent = (Button) findViewById(R.id.button);
        Button btnInsertNewStudent = (Button) findViewById(R.id.button2);

        if (arrayList.size() == 0) {
            btnNextStudent.setVisibility(View.INVISIBLE);
        }

        btnNextStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = arrayList.size();
                if (n != 0) {
                    Random random = new Random();
                    int i = random.nextInt(n);
                    textView.setText(arrayList.get(i).toString());
                    arrayList.remove(i);
                }
            }
        });

        btnInsertNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentAlertFragment fragment = new StudentAlertFragment();
                fragment.show(getSupportFragmentManager(), "new Student");
                getAllStudents(arrayList);
                if (arrayList.size() > 0)
                    btnNextStudent.setVisibility(View.VISIBLE);
            }
        });

    }

    public void getAllStudents(ArrayList<Student> arrayList) {
        dataManager = new DataManager(this);
        Cursor c = dataManager.getAllStudents();
        while (c.moveToNext()) {
            Student student = new Student(c.getString(1), c.getString(2), c.getString(3));
            arrayList.add(student);
        }
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
}
