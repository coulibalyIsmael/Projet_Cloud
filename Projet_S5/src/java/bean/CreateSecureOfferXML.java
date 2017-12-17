/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import interfaces.CloudServicex;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author couli
 */
public class CreateSecureOfferXML {
    
    public void createXmlFile(CloudServicex obj, String path) throws Exception {

                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
                Document doc= docBuilder.newDocument();
        //System.out.println("Creatin du fichier offer");
        if (obj instanceof CloudServiceConsumer) {
            CloudServiceConsumer csc = (CloudServiceConsumer) obj;
            Element root = doc.createElement("offer");
            doc.appendChild(root);

            //ID tag
            Element id = doc.createElement("id");
            root.appendChild(id);
            Text textID = doc.createTextNode(csc.getID());
            id.appendChild(textID);

            //Name tag
            Element name = doc.createElement("name");
            root.appendChild(name);
            Text textName = doc.createTextNode(csc.getName());
            name.appendChild(textName);
            
            //price tag
            Element price = doc.createElement("price");
            root.appendChild(price);
            Text priceText = doc.createTextNode(""+csc.getSecureOffer().getPrice());
            price.appendChild(priceText);

            for (MyService service : csc.getSecureOffer().getServices()) {
                Element tag = doc.createElement(service.getName());
                root.appendChild(tag);
                Text text = doc.createTextNode(service.getType());
                tag.appendChild(text);
            }

            //System.out.println("fin de l'écriture dans le fichier----------------------------------------------////*/8898*528");
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
            String xmlString = sw.toString();
            //System.out.println("fichier creeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee**************");

            //File file = new File("D:/offer.xml");
            // OutputStreamWriter outStream = new OutputStreamWriter( new FileOutputStream());
            // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("C:\\Users\\couli\\Documents\\NetBeansProjects\\Projet_S5\\web\\outputFiles\\offer.xml"))));
            // BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\couli\\Documents\\NetBeansProjects\\Projet_S5\\web\\outputFiles\\offer.xml")); 
            System.out.println("le monde ++++");
            File file = new File(path);
            FileWriter filewritter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(filewritter);
            //filewritter = null;

            bw.write(xmlString);
            bw.flush();
            bw.close();

        } else {

            CloudServiceProvider csp = (CloudServiceProvider) obj;
            Element root = doc.createElement("offer");
            doc.appendChild(root);
            

            //ID tag
            Element id = doc.createElement("id");
            root.appendChild(id);
            Text textID = doc.createTextNode(csp.getID());
            id.appendChild(textID);

            //Name tag
            Element name = doc.createElement("name");
            root.appendChild(name);
            Text textName = doc.createTextNode(csp.getName());
            name.appendChild(textName);

            //price tag
            Element price = doc.createElement("price");
            root.appendChild(textID);
            Text priceText = doc.createTextNode(""+csp.getSecureOffer().getPrice());
            price.appendChild(priceText);
            
            for (MyService service : csp.getServices()) {
                Element tag = doc.createElement(service.getName());
                root.appendChild(tag);
                Text text = doc.createTextNode(service.getType());
                tag.appendChild(text);
            }

            //System.out.println("fin de l'écriture dans le fichier----------------------------------------------////*/8898*528");
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);
            String xmlString = sw.toString();
            //System.out.println("fichier creeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee**************");

            //File file = new File("D:/offer.xml");
            // OutputStreamWriter outStream = new OutputStreamWriter( new FileOutputStream());
            // BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("C:\\Users\\couli\\Documents\\NetBeansProjects\\Projet_S5\\web\\outputFiles\\offer.xml"))));
            // BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\couli\\Documents\\NetBeansProjects\\Projet_S5\\web\\outputFiles\\offer.xml")); 
            System.out.println("le monde ++++");
            File file = new File(path);
            FileWriter filewritter = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(filewritter);
            //filewritter = null;

            bw.write(xmlString);
            bw.flush();
            bw.close();

        }

    }
    
}
