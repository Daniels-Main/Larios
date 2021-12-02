package com.example.larios.comidasybebidas;

import java.util.List;

public class Plato {
    private String nombre;
    private String categoria;
    private String tipo;
    private String precio;
    private List<String> ingredientes;

    public Plato(String nombre, String categoria, String tipo, String precio, List<String> ingredientes) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.tipo = tipo;
        this.precio = precio;
        this.ingredientes = ingredientes;
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

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
