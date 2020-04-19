package com.example.randomname;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentActivity extends AppCompatActivity {

    ArrayList<Student> arrayList;
    StudentAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_students);

        arrayList = new ArrayList<>();

        adapter = new StudentAdapter(this, arrayList);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(adapter);

        insertIntoAdapter();
    }

    public void insertIntoAdapter() {
        DataManager manager = new DataManager(this);
        Cursor c = manager.getAllStudents();
        while (c.moveToNext()) {
            Student student = new Student(c.getString(1), c.getString(2), c.getString(3));
            arrayList.add(student);
            adapter.notifyDataSetChanged();
        }
    }

}
