/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import jade.core.AID;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author couli
 */
public class CloudServiceCustomer implements Serializable{
    
public ArrayList<AID> listeProviders = null;
public String name;
public String[] listeChoix;
private ArrayList<MyService> services = new ArrayList() ;

//constructionn 
public CloudServiceCustomer(String name){
    this.name = name;
    listeProviders = new  ArrayList<>();
}
    public CloudServiceCustomer(String name, String[] services) {
        this.name  = name;
        listeChoix = services;
        listeProviders = new  ArrayList<>();
    }
    
  //getters and setters
    public CloudServiceCustomer(){
        
    }
  public void setNom(String name){
      this.name = name;
  }
  
  public String getNom(){
      return this.name;
  }
  public void addServices(String serviceName, int level){
      MyService srv = new MyService();
      srv.addPropertyService(serviceName, level);
      this.services.add(srv);
  }
  public ArrayList getServices()
  {
      return this.services;
  }
  
  
   public void createXmlFile() {

        try {
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("offer");
            root.addElement("id")
                    .addAttribute("company", "Ferrai");

            root.addElement("compute")
                    .addText("");

            root.addElement("storage")
                    .addText("");

            root.addElement("network")
                    .addText("");

            // Pretty print the document to System.out
            // lets write to a file
            XMLWriter writer = new XMLWriter(new FileWriter("offer.xml"));
            writer.write(document);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
