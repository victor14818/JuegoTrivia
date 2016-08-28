package com.example.trivia.juegotrivia;

/**
 * Created by victor on 27/08/2016.
 */
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ModeloPregunta {

    DBHandler manejador;
    SQLiteDatabase db;
    public ModeloPregunta(Context context){
        manejador = new DBHandler(context,"trivia",null,1);
    }

    public boolean addPregunta(Pregunta prg)
    {
        try {
            db = manejador.getWritableDatabase();
            db.execSQL(String.format("INSERT INTO pregunta VALUES ( %s, %s);",prg.getTexto(),prg.getCategoria()));
            db.close();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }


/*    public Pregunta[] getPreguntasCategoria(String categoria)
    {
        Pregunta respuesta [];
        String comando = String.format("SELECT rowid,texto FROM pregunta WHERE categoria = %s ORDER BY rowid",categoria);
        try {
            db = manejador.getReadableDatabase();
            db.rawQuery(comando,null);
            db.close();
            return respuesta;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }*/
}
