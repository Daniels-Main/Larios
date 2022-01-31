package com.example.larios.comidasybebidas;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class BebidasHandler extends DefaultHandler {
    private StringBuilder currentValue = new StringBuilder();
    //Genero la bebida actual
    private Bebida bebidaActual;
    //Genero la lista de bebidas
    private List<Bebida> listaBebidas;

    @Override
    public void startDocument() {
        //Al empezar el documento inicializo la lista
        listaBebidas = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //Cada vez que empieza un elemento vacia el valor actual
        currentValue.setLength(0);
        //Cuando encuentre el elemento bebida
        if (qName.equalsIgnoreCase("bebida")) {
            //Inicia la bebida actual
            bebidaActual = new Bebida();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //Cuando encuentra un nombre, mientras el nombre del parametro sea este, añade el valor a la bebida actual
        if (qName.equalsIgnoreCase("nombre")) {
            bebidaActual.setNombre(currentValue.toString());
        }
        //Repite lo mismo con el resto de parametros
        if (qName.equalsIgnoreCase("categoria")) {
            bebidaActual.setCategoria((currentValue.toString()));
        }
        if (qName.equalsIgnoreCase("tipo")) {
            bebidaActual.setTipo((currentValue.toString()));
        }
        if (qName.equalsIgnoreCase("precio")) {
            bebidaActual.setPrecio((currentValue.toString()));
        }

        //Cuando encuentra otro nombre, guarda la bebida en la array
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
