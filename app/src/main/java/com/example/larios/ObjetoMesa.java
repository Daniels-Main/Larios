package com.example.larios;

public class ObjetoMesa {
    private int numeroMesa;
    private String empleado;

    public ObjetoMesa(int numeroMesa, String empleado) {
        this.numeroMesa = numeroMesa;
        this.empleado = empleado;
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
}
