package com.example.notetoself;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JSONSerializer {

    public String fileName;
    public Context context;

    public JSONSerializer(String fileName, Context context){
        this.fileName=fileName;
        this.context=context;
    }

    public void save(List<Note> notes) throws IOException, JSONException {

        JSONArray jsonArray = new JSONArray();

        for(Note note : notes)
            jsonArray.put(note.getJSONObject());

        Writer writer =null;
        try{
            OutputStream outputStream = context.openFileOutput(fileName,context.MODE_PRIVATE);
            writer= new OutputStreamWriter(outputStream);
            writer.write(jsonArray.toString());
        }
        finally {
            if(writer!=null)
                writer.close();
        }
    }

    public ArrayList<Note> load() throws IOException, JSONException{
        ArrayList<Note> list = new ArrayList<>();
        BufferedReader reader = null;
        try{
            InputStream in = context.openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;

            while((line = reader.readLine())!=null)
                jsonString.append(line);

            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

            for(int i=0;i<jsonArray.length();i++)
                list.add(new Note(jsonArray.getJSONObject(i)));
        }
        catch (FileNotFoundException e){

        }
        finally {
            if(reader!=null)
                reader.close();
        }
        return list;
    }
}
