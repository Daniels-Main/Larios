package com.example.larios.comidasybebidas;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class BebidasHandler extends DefaultHandler {
    private StringBuilder currentValue = new StringBuilder();
    //Genero la cancion actual
    private Bebida bebidaActual;
    //Genero la lista de canciones
    private List<Bebida> listaBebidas;

    @Override
    public void startDocument() {
        //Al empezar el documento inicializo la lista
        listaBebidas = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //Cada vez que empieza un elemento "vacia" el valor actual
        currentValue.setLength(0);
        //Cuando encuentre el elemento Cancion
        if (qName.equalsIgnoreCase("bebida")) {
            //Inicia la canion actual
            bebidaActual = new Bebida();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("nombre")) {
            bebidaActual.setNombre(currentValue.toString());
        }
        //Y asi con el resto de parametros
        if (qName.equalsIgnoreCase("categoria")) {
            bebidaActual.setCategoria((currentValue.toString()));
        }
        if (qName.equalsIgnoreCase("tipo")) {
            bebidaActual.setTipo((currentValue.toString()));
        }
        if (qName.equalsIgnoreCase("precio")) {
            bebidaActual.setPrecio((currentValue.toString()));
        }
        if (qName.equalsIgnoreCase("nombre")){
            listaBebidas.add(bebidaActual);
        }
    }
    //Te crea la string
    public void characters(char ch[], int start, int length){
        currentValue.append(ch, start, length);
    }
    //Return de la lista
    public List<Bebida> getListaBebidas(){
        return listaBebidas;
    }
}
