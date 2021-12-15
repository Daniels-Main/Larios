package com.example.larios.comidasybebidas;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class IngredientesHandler extends DefaultHandler {
    private StringBuilder currentValue = new StringBuilder();
    private List<String> ingredientes;
    private List<List<String>> listaIngredientes;

    @Override
    public void startDocument() {
        listaIngredientes = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //Cada vez que empieza un elemento "vacia" el valor actual
        currentValue.setLength(0);
        //Cuando encuentre el elemento Cancion
        if (qName.equalsIgnoreCase("ingredientes")) {
            //Inicia la canion actual
            ingredientes = new ArrayList<>();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("verduras") && currentValue.toString().equals("1")) {
            ingredientes.add(qName);
        }
        if (qName.equalsIgnoreCase("aceitunas") && currentValue.toString().equals("1")) {
            ingredientes.add(qName);
        }
        if (qName.equalsIgnoreCase("harina") && currentValue.toString().equals("1")) {
            ingredientes.add(qName);
        }
        if (qName.equalsIgnoreCase("lacteos") && currentValue.toString().equals("1")) {
            ingredientes.add(qName);
        }
        if (qName.equalsIgnoreCase("carne") && currentValue.toString().equals("1")) {
            ingredientes.add(qName);
        }
        if (qName.equalsIgnoreCase("pescado") && currentValue.toString().equals("1")) {
            ingredientes.add(qName);
        }
        if (qName.equalsIgnoreCase("marisco") && currentValue.toString().equals("1")) {
            ingredientes.add(qName);
        }
        if (qName.equalsIgnoreCase("arroz") && currentValue.toString().equals("1")) {
            ingredientes.add(qName);
        }
        if (qName.equalsIgnoreCase("pasta") && currentValue.toString().equals("1")) {
            ingredientes.add(qName);
        }
        if (qName.equalsIgnoreCase("azúcar") && currentValue.toString().equals("1")) {
            ingredientes.add(qName);
        }
        if (qName.equalsIgnoreCase("chocolate") && currentValue.toString().equals("1")) {
            ingredientes.add(qName);
        }
        if (qName.equalsIgnoreCase("huevos") && currentValue.toString().equals("1")) {
            ingredientes.add(qName);
        }
        if (qName.equalsIgnoreCase("frutos_secos") && currentValue.toString().equals("1")) {
            ingredientes.add(qName);
        }

        if (qName.equalsIgnoreCase("verduras")){
            listaIngredientes.add(ingredientes);
        }
    }
    public void characters(char ch[], int start, int length){
        currentValue.append(ch, start, length);
    }
    public List<List<String>> getListaIngredientes(){
        return listaIngredientes;
    }
}
