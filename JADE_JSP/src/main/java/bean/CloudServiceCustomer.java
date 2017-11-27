/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import jade.core.AID;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author couli
 */
public class CloudServiceCustomer implements Serializable{
    
    public ArrayList<AID> listeProviders = null;
public String name;
public String[] listeChoix;
private ArrayList services = new ArrayList() ;
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
  public void setServices(String service){
      this.services.add(service);
  }
  public ArrayList getServices()
  {
      return this.services;
  }
}
