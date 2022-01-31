package com.example.larios.comidasybebidas;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class PlatosHandler extends DefaultHandler {
    private StringBuilder currentValue = new StringBuilder();
    private Plato platoActual;
    private List<String> ingredientes;
    private List<List<String>> listaDeIngredientes;
    private List<Plato> listaPlatos;

    @Override
    public void startDocument() {
        listaPlatos = new ArrayList<>();
        listaDeIngredientes = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentValue.setLength(0);
        if (qName.equalsIgnoreCase("plato")) {
            platoActual = new Plato();
        }
        if (qName.equalsIgnoreCase("ingredientes")) {
            ingredientes = new ArrayList<>();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("nombre")) {
            platoActual.setNombre(currentValue.toString());
        }
        if (qName.equalsIgnoreCase("categoria")) {
            platoActual.setCategoria((currentValue.toString()));
        }
        if (qName.equalsIgnoreCase("tipo")) {
            platoActual.setTipo((currentValue.toString()));
        }
        if (qName.equalsIgnoreCase("precio")) {
            platoActual.setPrecio((currentValue.toString()));
        }

        //Cuando el nombre del paramentro sea "verduras" y el valor sea "1" añade el nombre a los ingredientes
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
            platoActual.setIngredientes(ingredientes);
            listaPlatos.add(platoActual);
        }
    }
    public void characters(char ch[], int start, int length){
        currentValue.append(ch, start, length);
    }
    public List<Plato> getListaPlatos(){
        return listaPlatos;
    }
    public List<List<String>> getListaDeIngredientes(){
        return listaDeIngredientes;
    }
}
