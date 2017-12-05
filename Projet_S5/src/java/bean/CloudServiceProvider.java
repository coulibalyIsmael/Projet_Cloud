/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.Property;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author couli
 */
public class CloudServiceProvider implements Serializable{
    private String name;
    private ArrayList<MyService> providerServices;

    public CloudServiceProvider() {
        providerServices = new ArrayList();
        
    }

   
    public void setName(String name) {
      
        this.name = name;
      
    }
    public String  getName(){
        return this.name;
    }

    /**
     * @return the providerServices
     */
    public ArrayList<MyService> getProviderServices() {
        return providerServices;
    }

    /**
     * @param providerServices the providerServices to set
     */
   
     public void addServices(String service, int level){
      MyService srv = new MyService();
        srv.setName(service);
        srv.setType(String.valueOf(level));
     
        this.providerServices.add(srv);
  }
  
    
    
}
