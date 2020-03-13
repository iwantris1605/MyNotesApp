package com.dicoding.picodiploma.consumerapp.helper;

import android.database.Cursor;

import com.dicoding.picodiploma.consumerapp.db.DatabaseContract;
import com.dicoding.picodiploma.consumerapp.entity.Note;

import java.util.ArrayList;

//kelas ini berfungsi untuk konfersi dari Cursor ke arraylist
public class MappingHelper {

    public static ArrayList<Note> mapCursorToArrayList(Cursor notesCursor) {
        ArrayList<Note> notesList = new ArrayList<>();

        while (notesCursor.moveToNext()) {
            int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID));
            String title = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE));
            String description = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION));
            String date = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE));
            notesList.add(new Note(id, title, description, date));
        }

        return notesList;
    }

    //menambahkan kelas sebagai fungsi dari cursor to object
    public static Note mapCursorToObject(Cursor notesCursor) {
        notesCursor.moveToFirst();
        int id = notesCursor.getInt(notesCursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns._ID));
        String title = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.TITLE));
        String description = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DESCRIPTION));
        String date = notesCursor.getString(notesCursor.getColumnIndexOrThrow(DatabaseContract.NoteColumns.DATE));
        return new Note(id, title, description, date);
    }
}
