package com.example.notetoself;

import android.app.Dialog;
import android.os.Bundle;

public class DialogShowNote {
    private Note mNote;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

    }

    public void sendNoteSelected(Note noteSelected) {
        mNote = noteSelected;
    }
}
