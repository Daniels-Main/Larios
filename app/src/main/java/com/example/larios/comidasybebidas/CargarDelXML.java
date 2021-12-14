package com.example.larios.comidasybebidas;

import androidx.appcompat.app.AppCompatActivity;

import com.example.larios.GlobalVariables;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class CargarDelXML extends AppCompatActivity {
    public void TodasBebidas() throws Exception {
        ArrayList<Bebida> listaBebidas = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse("src/main/res/raw/base_de_bebidas.xml");

        XPath xpath = XPathFactory.newInstance().newXPath();
        String xPathSentence = "//bebida";

        NodeList nodos = (NodeList) xpath.evaluate(xPathSentence, doc, XPathConstants.NODESET);
        for (int i = 0; i < nodos.getLength(); i++) {
            Node nodo = nodos.item(i);
            Bebida actual = new Bebida();
            NodeList hijos = nodo.getChildNodes();
            for (int j = 0; j < hijos.getLength(); j++) {
                Node hijo = hijos.item(j);
                switch (hijo.getNodeName()) {
                    case "nombre":
                        actual.setNombre(hijo.getTextContent());
                        break;
                    case "categoria":
                        actual.setCategoria(hijo.getTextContent());
                        break;
                    case "tipo":
                        actual.setTipo(hijo.getTextContent());
                        break;
                    case "precio":
                        actual.setPrecio(hijo.getTextContent());
                        break;
                }
            }
        }
        ((GlobalVariables) this.getApplication()).setBebidas(listaBebidas);
    }

    public void TodosPlatos() throws Exception {
        ArrayList<Plato> listaPlatos = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse("src/main/res/raw/base_de_platos.xml");

        XPath xpath = XPathFactory.newInstance().newXPath();
        String xPathSentence = "//plato";
        NodeList nodos = (NodeList) xpath.evaluate(xPathSentence, doc, XPathConstants.NODESET);
        for (int i = 0; i < nodos.getLength(); i++) {
            Node nodo = nodos.item(i);
            Plato actual = new Plato();
            NodeList hijos = nodo.getChildNodes();
            for (int j = 0; j < hijos.getLength(); j++) {
                Node hijo = hijos.item(j);
                switch (hijo.getNodeName()) {
                    case "nombre":
                        actual.setNombre(hijo.getTextContent());
                        break;
                    case "categoria":
                        actual.setCategoria(hijo.getTextContent());
                        break;
                    case "tipo":
                        actual.setTipo(hijo.getTextContent());
                        break;
                    case "precio":
                        actual.setPrecio(hijo.getTextContent());
                        break;
                    case "ingredientes":
                        ArrayList<String> ingredients = new ArrayList<>();
                        NodeList ingredientes = hijo.getChildNodes();
                        for (int x = 0; i < ingredientes.getLength(); i++) {
                            Node ingrediente = ingredientes.item(x);
                            if (ingrediente.getTextContent().equals("1")){
                                ingredients.add(ingrediente.getNodeName());
                            }
                        }
                        actual.setIngredientes(ingredients);
                        break;
                }
            }
        }
        ((GlobalVariables) this.getApplication()).setPlatos(listaPlatos);
    }
}
