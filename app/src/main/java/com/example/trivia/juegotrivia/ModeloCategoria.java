package com.example.trivia.juegotrivia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by victor on 27/08/2016.
 */
public class ModeloCategoria {

    DBHandler manejador;
    SQLiteDatabase db;
    public ModeloCategoria(Context context){
        manejador = new DBHandler(context,"trivia",null,1);
    }

    public void destruir()
    {
        try {
            manejador.close();
            db.close();
        }catch (Exception e)
        {

        }
    }

    public boolean IsEmpty(String tableName)
    {
        boolean respuesta = false;
        try {
            db = manejador.getReadableDatabase();
            Cursor crs = db.query(tableName,null, null, null, null, null, null, null);
            if(crs.getCount() == 0)
            {
                respuesta = true;
            }
            crs.close();
            db.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public Long addCategoria(Categoria ct)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put( "nombre" , ct.getNombre());
        contentValues.put( "texto" , ct.getTexto());
        contentValues.put( "imagen1" , ct.getImgp());
        contentValues.put( "imagen2" , ct.getImgs());
        contentValues.put( "audio" , ct.getAudio());
        return ingresar("categoria",contentValues);
    }

    public int updateCategoria(Categoria ct)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put( "nombre" , ct.getNombre());
        contentValues.put( "texto" , ct.getTexto());
        contentValues.put( "imagen1" , ct.getImgp());
        contentValues.put( "imagen2" , ct.getImgs());
        contentValues.put( "audio" , ct.getAudio());
        return modificar("categoria",contentValues," rowid = ?", new String[]{String.valueOf(ct.getRowid())});
    }

    public int deleteCategoria(Categoria ct)
    {
        System.out.println("eliminando " + ct.getRowid());
        return eliminar("categoria", " rowid = ?", new String[]{String.valueOf(ct.getRowid())});
    }

    public int deleteCategoria(Long ct)
    {
        System.out.println("eliminando " + ct);
        return eliminar("categoria", " rowid = ?", new String[]{String.valueOf(ct)});
    }

    private Long ingresar(String tableName, ContentValues contentValues)
    {
        Long respuesta = null;
        try {
            db = manejador.getWritableDatabase();
            respuesta = db.insert( tableName , null, contentValues );
            db.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public Categoria getCategoria(Long rowid)
    {
        Categoria respuesta = null;
        try {
            db = manejador.getReadableDatabase();
            Cursor crs = db.query(true,"categoria",new String[] { "rowid", "*" }, " rowid = ? ", new String[]{String.valueOf(rowid)}, null, null, null, null);
            if(crs != null)
            {
                crs.moveToFirst();
                respuesta = new Categoria(crs.getString(1),crs.getString(2),crs.getString(5),crs.getString(3),crs.getString(4),crs.getLong(0));
            }
            crs.close();
            db.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public Categoria[] getAllCategorias()
    {
        Categoria [] respuesta = null;
        try {
            db = manejador.getReadableDatabase();
            Cursor crs = db.query("categoria",new String[] { "rowid", "*" }, null, null, null, null, null, null);
            String [] a = crs.getColumnNames();

            if(crs != null)
            {
                respuesta = new Categoria[crs.getCount()];
                crs.moveToFirst();
                for (int i = 0; i < crs.getCount(); i++) {
                    respuesta[i] = new Categoria(crs.getString(1),crs.getString(2),crs.getString(5),crs.getString(3),crs.getString(4),crs.getLong(0));
                    crs.moveToNext();
                }
            }

            crs.close();
            db.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    private int modificar(String tableName, ContentValues contentValues, String where, String[] whereArg)
    {
        int respuesta = 0;
        try {
            db = manejador.getWritableDatabase();
            respuesta = db.update( tableName, contentValues, where, whereArg);
            db.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    private int eliminar(String tableName, String where, String[] whereArg)
    {
        int respuesta = 0;
        try {
            db = manejador.getWritableDatabase();
            respuesta = db.delete( tableName, where, whereArg);
            db.close();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public Pregunta[] getAllPreguntas(Categoria ct)
    {
        Pregunta [] respuesta = null;
        try {
            db = manejador.getReadableDatabase();
            Cursor crs = db.query("pregunta",new String[] { "rowid", "*" }, " categoria = ? ", new String[] {String.valueOf(ct.getRowid())}, null, null, null, null);

            if(crs != null)
            {
                respuesta = new Pregunta[crs.getCount()];
                crs.moveToFirst();
                for (int i = 0; i < crs.getCount(); i++) {
                    respuesta[i] = new Pregunta(crs.getString(1),crs.getLong(2),crs.getInt(3), crs.getString(4), crs.getLong(0));
                    crs.moveToNext();
                }
            }

            crs.close();
            db.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public Pregunta getPrgunta(Long rowid)
    {
        Pregunta respuesta = null;
        try {
            db = manejador.getReadableDatabase();
            Cursor crs = db.query(true,"pregunta",new String[] { "rowid", "*" }, " rowid = ? ", new String[]{String.valueOf(rowid)}, null, null, null, null);
            if(crs != null)
            {
                crs.moveToFirst();
                respuesta = new Pregunta(crs.getString(1),crs.getLong(2),crs.getInt(3),crs.getString(4),crs.getLong(0));
            }
            crs.close();
            db.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public Long addPregutna(Pregunta ct)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put( "texto" , ct.getTexto());
        contentValues.put( "categoria" , ct.getCategoria());
        contentValues.put( "puntos" , ct.getPuntos());
        contentValues.put( "audio" , ct.getAudio());
        return ingresar("pregunta",contentValues);
    }

    public int updatePregunta(Pregunta ct)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put( "texto" , ct.getTexto());
        contentValues.put( "categoria" , ct.getCategoria());
        contentValues.put( "puntos" , ct.getPuntos());
        contentValues.put( "audio" , ct.getAudio());
        return modificar("pregunta",contentValues," rowid = ?", new String[]{String.valueOf(ct.getRowid())});
    }

    public int deletePregunta(Categoria ct)
    {
        System.out.println("eliminando pregunta" + ct.getRowid());
        return eliminar("pregunta", " rowid = ?", new String[]{String.valueOf(ct.getRowid())});
    }

    public int deletePregunta(Long ct)
    {
        System.out.println("eliminando pregunta " + ct);
        return eliminar("pregunta", " rowid = ?", new String[]{String.valueOf(ct)});
    }

    //Respuesta
    public Respuesta[] getAllRespuestas(Pregunta ct)
    {
        Respuesta [] respuesta = null;
        try {
            db = manejador.getReadableDatabase();
            Cursor crs = db.query("respuesta",new String[] { "rowid", "*" }, " pregunta = ? ", new String[] {String.valueOf(ct.getRowid())}, null, null, null, null);

            if(crs != null)
            {
                respuesta = new Respuesta[crs.getCount()];
                crs.moveToFirst();
                for (int i = 0; i < crs.getCount(); i++) {
                    respuesta[i] = new Respuesta(crs.getString(1),crs.getInt(2),crs.getLong(3),crs.getLong(0));
                    crs.moveToNext();
                }
            }

            crs.close();
            db.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public Respuesta[] getAllRespuestasFilter(Pregunta ct, int filter)
    {
        Respuesta [] respuesta = null;
        try {
            db = manejador.getReadableDatabase();
            Cursor crs = db.query("respuesta",new String[] { "rowid", "*" }, " pregunta = ? AND escorrecta = ? ", new String[] {String.valueOf(ct.getRowid()),String.valueOf(filter)}, null, null, null, null);

            if(crs != null)
            {
                respuesta = new Respuesta[crs.getCount()];
                crs.moveToFirst();
                for (int i = 0; i < crs.getCount(); i++) {
                    respuesta[i] = new Respuesta(crs.getString(1),crs.getInt(2),crs.getLong(3),crs.getLong(0));
                    crs.moveToNext();
                }
            }

            crs.close();
            db.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public int deleteRespuesta(Pregunta ct)
    {
        System.out.println("eliminando respuesta " + ct.getRowid());
        return eliminar("respuesta", " rowid = ?", new String[]{String.valueOf(ct.getRowid())});
    }

    public int deleteRespuesta(Long ct)
    {
        System.out.println("eliminando respuesta " + ct);
        return eliminar("respuesta", " rowid = ?", new String[]{String.valueOf(ct)});
    }

    public Respuesta getRespuesta(Long rowid)
    {
        Respuesta respuesta = null;
        try {
            db = manejador.getReadableDatabase();
            Cursor crs = db.query(true,"respuesta",new String[] { "rowid", "*" }, " rowid = ? ", new String[]{String.valueOf(rowid)}, null, null, null, null);
            if(crs != null)
            {
                crs.moveToFirst();
                respuesta = new Respuesta(crs.getString(1),crs.getInt(2),crs.getLong(3),crs.getLong(0));
            }
            crs.close();
            db.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }

    public Long addRespuesta(Respuesta ct)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put( "texto" , ct.getTexto());
        contentValues.put( "escorrecta" , ct.isCorrecta());
        contentValues.put( "pregunta" , ct.getPregunta());
        contentValues.put( "audio" , "");
        return ingresar("respuesta",contentValues);
    }

    public int updateRespuesta(Respuesta ct)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put( "texto" , ct.getTexto());
        contentValues.put( "escorrecta" , ct.isCorrecta());
        contentValues.put( "pregunta" , ct.getPregunta());
        contentValues.put( "audio" , "");
        return modificar("respuesta",contentValues," rowid = ?", new String[]{String.valueOf(ct.getRowid())});
    }
}
