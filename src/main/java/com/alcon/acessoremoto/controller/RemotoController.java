package com.alcon.acessoremoto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

@RestController
public class RemotoController {

    @GetMapping("/rdp")
    public String executarAcessoRemoto() {
        try {
            File file = new File("C:\\Alcon\\rdp.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            System.out.println("Primeiro elemento do XML: " + doc.getDocumentElement().getNodeName());
            NodeList nodeList = doc.getElementsByTagName("App2Open");
            for (int itr = 0; itr < nodeList.getLength(); itr++)
            {
                Node node = nodeList.item(itr);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) node;
                    Runtime.getRuntime().exec(eElement.getElementsByTagName("exec_path").item(0).getTextContent());
                    Runtime.getRuntime().exec(eElement.getElementsByTagName("exec_path").item(0).getTextContent());
                    //Runtime.getRuntime().exec(eElement.getElementsByTagName("abreJanelaMinizada").item(0).getTextContent());
                   // Runtime.getRuntime().exec(eElement.getElementsByTagName("parametros").item(0).getTextContent());

                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    return "acessoRemoto/remoto";
    }
}