/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import interfaces.CloudServicex;
import jade.core.AID;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author couli
 */
public class CloudServiceConsumer implements Serializable, CloudServicex {

    private ArrayList<DFAgentDescription[]> listeProviders = null;
    public String name;
    private ArrayList<MyService> consumerServices;
    private String ID;
    private SecureOffer secureOffer ;

//constructionn 
    public CloudServiceConsumer() {
        listeProviders = new ArrayList<>();
        consumerServices = new ArrayList();
        secureOffer = new SecureOffer();
    }

    public CloudServiceConsumer(String name, String[] services) {
        this.name = name;
        listeProviders = new ArrayList<>();
    }

    //getters and setters
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addServices(String service, int level) {
        MyService srv = new MyService();
        srv.setName(service);
        srv.setType(String.valueOf(level));

        this.consumerServices.add(srv);

    }

    /**
     * @return the secureOffer
     */
    public SecureOffer getSecureOffer() {
        return secureOffer;
    }

    /**
     * @param secureOffer the secureOffer to set
     */
    public void setSecureOffer(SecureOffer secureOffer) {
        this.secureOffer = secureOffer;
    }

    @Override
    public ArrayList<MyService> getServices() {
        return this.consumerServices;
    }

    public void createXmlFile(String path) {

        try {
            System.out.println("Creatin du fichier offer");
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("offer");
            root.addElement("id")
                    .addText(this.getID());
            root.addElement("name")
                    .addText(this.getName());

            for (MyService srv : consumerServices) {
                root.addElement(srv.getName())
                        .addText(srv.getType());
            }

            // Pretty print the document to System.out
            // lets write to a file
            FileOutputStream fos = new FileOutputStream(path + "offer.xml");
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(fos, format);
            writer.write(document);
            System.out.println("fin de l'écriture dans le fichier");
            writer.flush();

            //XMLWriter writer = new XMLWriter(new FileWriter(this.getName()+"comsumer_offer.xml"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return the listeProviders
     */
    public ArrayList<DFAgentDescription[]> getListeProviders() {
        return listeProviders;
    }

    /**
     * @param listeProviders the listeProviders to set
     */
    public void setListeProviders(ArrayList<DFAgentDescription[]> listeProviders) {
        this.listeProviders = listeProviders;
    }

}
