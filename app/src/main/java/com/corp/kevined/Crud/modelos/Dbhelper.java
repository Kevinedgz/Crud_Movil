package com.corp.kevined.Crud.modelos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Dbhelper extends SQLiteOpenHelper {
    public static final int VERSION=1;
    public static final String NOMBRE_DB="KEVIN.db";

    public Dbhelper(@Nullable Context context) {
        super(context, NOMBRE_DB, null, VERSION);
    }

    public static abstract class PersonEntry implements BaseColumns {
        public static final String TABLE_NAME="Personas";
        public static final String ID="id";
        public static final String NOMBRE="nombre";
        public static final String APELLIDO="apellido";
        public static final String CORREO="correo";
        public static final String FECHA="fecha";
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PersonEntry.TABLE_NAME + " ("
                + PersonEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PersonEntry.NOMBRE + " TEXT NOT NULL,"
                + PersonEntry.APELLIDO + " TEXT NOT NULL,"
                + PersonEntry.FECHA + " TEXT NOT NULL,"
                + PersonEntry.CORREO + " TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insertar(Persona person){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PersonEntry.NOMBRE, person.getNombre());
        cv.put(PersonEntry.APELLIDO, person.getApellido());
        cv.put(PersonEntry.FECHA, person.getFecha());
        cv.put(PersonEntry.CORREO, person.getCorreo());
        long result = DB.insert(PersonEntry.TABLE_NAME, null, cv);
        if (result==-1){return false;}
        else{return true;}
    }
    public List<Persona> listAll() {
        List<Persona> people = new ArrayList<>();
        String query = "SELECT * FROM " + PersonEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                Persona person = new Persona(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));

                people.add(person);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return people;
    }
}
