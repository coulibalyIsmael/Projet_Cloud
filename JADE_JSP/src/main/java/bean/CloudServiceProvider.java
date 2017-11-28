/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

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
        providerServices = new ArrayList<>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
      
        this.name = name;
      
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
    public void setProviderServices(ArrayList<MyService> providerServices) {
     
        this.providerServices = providerServices;
       
    }
  
    
    
}
