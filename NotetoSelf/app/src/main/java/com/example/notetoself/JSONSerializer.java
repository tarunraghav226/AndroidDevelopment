package com.example.notetoself;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
}
