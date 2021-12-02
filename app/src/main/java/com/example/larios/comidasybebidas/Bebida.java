package com.example.larios.comidasybebidas;

public class Bebida {
    private String nombre;
    private String categoria;
    private String tipo;
    private String precio;

    public Bebida(String nombre, String categoria, String tipo, String precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.tipo = tipo;
        this.precio = precio;
    }

    public Bebida() {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
