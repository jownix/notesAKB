package com.uts_akb_10120142;

import android.database.Cursor;

import com.uts_akb_10120142.model.Note;
// 10120142_JhonathanKenzo_IF4
public interface NoteInterface {

    public Cursor read();
    public boolean create(Note note);
    public boolean update(Note note);
    public boolean delete(String id);
}


