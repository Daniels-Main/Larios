package com.example.larios.comidasybebidas;

public class ObjetoLista {
    private String nombre;
    private String categoria;


    public ObjetoLista() {
    }

    public ObjetoLista(String nombre, String categoria) {
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
