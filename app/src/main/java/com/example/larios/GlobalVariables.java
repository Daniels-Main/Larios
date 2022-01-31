package com.example.larios;

import android.app.Application;

import com.example.larios.admin.Mensaje;
import com.example.larios.comidasybebidas.Bebida;
import com.example.larios.comidasybebidas.Plato;

import java.util.ArrayList;

//Clase de variables grobales
public class GlobalVariables extends Application {
    private ArrayList<ObjetoMesa> someVariable;
    private ArrayList<Mensaje> mensajes;
    private ArrayList<Bebida> bebidas;
    private ArrayList<Plato> platos;

    public ArrayList<ObjetoMesa> getSomeVariable() {
        return someVariable;
    }
    public void inicializar(){
        someVariable = new ArrayList<>();
    }
    public void inicializarMensajes(){
        mensajes = new ArrayList<>();
    }

    public void setSomeVariable(ArrayList<ObjetoMesa> someVariable) {
        this.someVariable = someVariable;
    }
    public void add(ObjetoMesa om){
        someVariable.add(om);
    }
    public void remove(ObjetoMesa om){
        someVariable.remove(om);
    }


    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public void addM(Mensaje m){
        mensajes.add(m);
    }

    public void remove(Mensaje m){
        mensajes.remove(m);
    }

    public ArrayList<Bebida> getBebidas() {
        return bebidas;
    }

    public void setBebidas(ArrayList<Bebida> bebidas) {
        this.bebidas = bebidas;
    }

    public ArrayList<Plato> getPlatos() {
        return platos;
    }

    public void setPlatos(ArrayList<Plato> platos) {
        this.platos = platos;
    }
}
