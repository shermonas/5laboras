package com.example.a5laboras;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

public class Parser {
    static ArrayList<String> cList = new ArrayList<>();
    public static void parsing() {
        try {
            SAXParserFactory parserFactory = SAXParserFactory.newInstance();
            SAXParser parser = parserFactory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (localName.equals("Cube") && attributes.getLength() == 2) {
                        HashMap<String, String> currenyNode = new HashMap<>();
                        for (int i = 0; i < attributes.getLength(); i++) {
                            String attrName = attributes.getQName(i);
                            String attrVal = attributes.getValue(i);
                            currenyNode.put(attrName, attrVal);
                        }
                        cList.add(String.format("%s: %s", currenyNode.get("currency"), currenyNode.get("rate")));
                    }
                }
            };
            InputStream istream = DataLoader.dUrl("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
            parser.parse(istream, handler);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<String> getList(){
        return cList;
    }
}

