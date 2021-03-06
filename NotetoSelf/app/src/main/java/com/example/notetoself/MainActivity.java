package com.example.notetoself;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private JSONSerializer serializer;
    private List<Note> noteList;
    private RecyclerView recyclerView;
    private NoteAdapter mAdapter;
    private boolean showDividers;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogNewNote dialog = new DialogNewNote();
                dialog.show(getSupportFragmentManager(), "");
            }
        });


        serializer=new JSONSerializer("Note to Self.json",getApplicationContext());
        try{
            noteList=serializer.load();
        }
        catch (Exception e){
            noteList=new ArrayList<Note>();
            Log.i("Cannot load","noteList",e);
        }

        preferences=getSharedPreferences("Note To Self",MODE_PRIVATE);
        showDividers=preferences.getBoolean("dividers",true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        mAdapter = new NoteAdapter(this, noteList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Add a neat dividing line between items in the list
        if(showDividers)
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        else{
            if(recyclerView.getItemDecorationCount()>0)
                recyclerView.removeItemDecorationAt(0);
        }

        // set the adapter
        recyclerView.setAdapter(mAdapter);

    }

    public void createNewNote(Note n){
        noteList.add(n);
        mAdapter.notifyDataSetChanged();
    }

    public void showNote(int noteToShow){
        DialogShowNote dialog = new DialogShowNote();
        dialog.sendNoteSelected(noteList.get(noteToShow));
        dialog.show(getSupportFragmentManager(), "");
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
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume(){
        super.onResume();
        preferences=getSharedPreferences("Note To Self",MODE_PRIVATE);
        showDividers = preferences.getBoolean("dividers",true);

        if(showDividers)
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        else{
            if(recyclerView.getItemDecorationCount()>0)
                recyclerView.removeItemDecorationAt(0);
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        saveNotes();
    }

    public void saveNotes(){
        try{
            serializer.save(noteList);
        }
        catch (Exception e){
            Log.i("Error while saving","notes",e);
        }
    }
}
