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
    private MyService providerServices;

    public CloudServiceProvider() {
        providerServices = new MyService();
        
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
    public MyService getProviderServices() {
        return providerServices;
    }

    /**
     * @param providerServices the providerServices to set
     */
    public void setProviderServices(String service, int level) {
     
        this.providerServices.addPropertyService(service, level);
       
    }
     public void addServices(String serviceName, int level){
      this.providerServices.addPropertyService(serviceName, level);
  }
  
    
    
}
