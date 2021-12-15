package com.example.larios.comidasybebidas;

import android.app.slice.Slice;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.example.larios.GlobalVariables;
import com.example.larios.R;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class CargarDelXML extends AppCompatActivity {
    public ArrayList<Bebida> TodasBebidas(Context c) throws Exception {
        InputStream stream = c.getAssets().open("base_de_bebidas.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        BebidasHandler handler = new BebidasHandler();
        saxParser.parse(stream, handler);
        ArrayList<Bebida> result = (ArrayList<Bebida>) handler.getListaBebidas();
        return  result;
    }

    public ArrayList<Plato> TodosPlatos(Context c) throws Exception {
        InputStream stream = c.getAssets().open("base_de_platos.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        PlatosHandler handler = new PlatosHandler();
        saxParser.parse(stream, handler);
        ArrayList<Plato> platos = (ArrayList<Plato>) handler.getListaPlatos();

        stream.close();
        return platos;
    }


}
