package com.example.trivia.juegotrivia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by victor on 27/08/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    String create_categoria = "CREATE TABLE categoria " +
            "( " +
            "nombre TEXT PRIMARY KEY NOT NULL, " +
            "texto TEXT NOT NULL, " +
            "imagen TEXT NOT NULL, " +
            "imagen2 TEXT NOT NULL, " +
            "audio TEXT " +
            ")";

    String create_pregunta = "CREATE TABLE pregunta " +
            "texto TEXT NOT NULL, " +
            "categoria TEXT NOT NULL." +
            "audio TEXT " +
            ")";

    String create_respuesta = "CREATE TABLE respuesta " +
            "texto TEXT NOT NULL, " +
            "escorrecta BOOLEAN NOT NULL, " +
            "pregunta INT NOT NULL, " +
            "audio TEXT " +
            ")";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_categoria);
        db.execSQL(create_pregunta);
        db.execSQL(create_respuesta);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS categoria");
        db.execSQL("DROP TABLE IF EXISTS pregunta");
        db.execSQL("DROP TABLE IF EXISTS respuesta");

        db.execSQL(create_categoria);
        db.execSQL(create_pregunta);
        db.execSQL(create_respuesta);
    }


}
