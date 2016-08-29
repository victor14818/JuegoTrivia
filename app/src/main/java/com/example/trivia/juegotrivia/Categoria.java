package com.example.trivia.juegotrivia;

/**
 * Created by victor on 27/08/2016.
 */
public class Categoria {
    private String nombre, texto, audio;
    private Long rowid;

    public Categoria(String nombre, String texto, String audio, Long rowid) {
        this.nombre = nombre;
        this.texto = texto;
        this.audio = audio;
        this.rowid = rowid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }
}
