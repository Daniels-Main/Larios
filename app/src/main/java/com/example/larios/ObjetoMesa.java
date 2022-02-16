package com.example.larios;

import java.util.ArrayList;

public class ObjetoMesa {
    private int numeroMesa;
    private String empleado;
    private ArrayList<Object> comida;

    public ObjetoMesa(int numeroMesa, String empleado) {
        this.numeroMesa = numeroMesa;
        this.empleado = empleado;
        this.comida = new ArrayList<>();
    }

    public ObjetoMesa() {
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public ArrayList<Object> getComida() {
        return comida;
    }

    public void setComida(ArrayList<Object> comida) {
        this.comida = comida;
    }

    public void addComida(Object obj){
        this.comida.add(obj);
    }

    public void removeComida(int i){
        this.comida.remove(i);
    }
}
