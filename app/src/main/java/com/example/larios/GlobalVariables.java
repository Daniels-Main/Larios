package com.example.larios;

import android.app.Application;

import com.example.larios.admin.Mensaje;
import com.example.larios.comidasybebidas.Bebida;
import com.example.larios.comidasybebidas.Plato;

import java.util.ArrayList;
import java.util.HashMap;

//Clase de variables grobales
public class GlobalVariables extends Application {
    private ArrayList<ObjetoMesa> someVariable;
    private ArrayList<Mensaje> mensajes;
    private ArrayList<Bebida> bebidas;
    private ArrayList<Plato> platos;
    private HashMap<Integer,ArrayList<Object>> cocina;


    public void inicializar(){
        someVariable = new ArrayList<>();
    }
    public void inicializarMensajes(){
        mensajes = new ArrayList<>();
    }
    public void iniciarCocina(){
        cocina = new HashMap<>();
    }


    public void add(ObjetoMesa om){
        someVariable.add(om);
    }
    public void remove(ObjetoMesa om){
        someVariable.remove(om);
    }

    public void addM(Mensaje m){
        mensajes.add(m);
    }
    public void removeM(Mensaje m){
        mensajes.remove(m);
    }

    public void addPedido(int idMesa, ArrayList<Object> pedido){
        if (cocina.containsKey(idMesa)){
            ArrayList<Object> arrayList = cocina.get(idMesa);
            arrayList.addAll(pedido);
            cocina.put(idMesa,arrayList);
        }else{
            cocina.put(idMesa,pedido);
        }


    }

    public void remove(int idMesa){
        cocina.remove(idMesa);
    }



    public ArrayList<ObjetoMesa> getObjMesa() {
        return someVariable;
    }

    public void setObjMesa(ArrayList<ObjetoMesa> someVariable) {
        this.someVariable = someVariable;
    }

    public ArrayList<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(ArrayList<Mensaje> mensajes) {
        this.mensajes = mensajes;
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

    public HashMap<Integer, ArrayList<Object>> getCocina() {
        return cocina;
    }

    public void setCocina(HashMap<Integer, ArrayList<Object>> cocina) {
        this.cocina = cocina;
    }
}
