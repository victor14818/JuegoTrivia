package com.example.trivia.juegotrivia;

/**
 * Created by victor on 27/08/2016.
 */
public class Pregunta {
    private int rowid;
    private String texto;
    private String categoria;

    public Pregunta(int rowid, String txt, String categoria)
    {
        this.rowid = rowid;
        this.texto = txt;
        this.categoria = categoria;
    }

    public String getTexto() {
        return texto;
    }

    public int getRowid() {
        return rowid;
    }

    public void setRowid(int rowid) {
        this.rowid = rowid;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
