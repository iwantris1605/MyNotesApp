package com.dicoding.picodiploma.mynotesapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static com.dicoding.picodiploma.mynotesapp.db.DatabaseContract.TABLE_NAME;

public class NoteHelper {

    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper dataBaseHelper;
    private static NoteHelper INSTANCE;

    private static SQLiteDatabase database;

    //constructor
    private NoteHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    //metode digunakan untuk inisiasi database
    public static NoteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NoteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    //metode untuk membuka dan menutup koneksi ke database
    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    //metode CRUD mengambil data
    public Cursor queryAll() {
        return database.query(DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " DESC");
    }

    //metode CRUD mengambil data dengan id
    public Cursor queryById(String id) {
        return database.query(DATABASE_TABLE, null
                , _ID + " = ?" //import base colom id
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

    //metode CRUD menyimpan data
    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    //metode CRUD memperbaharui data
    public int update(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    //metode CRUD menghapus data
    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }
}
