/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudServiceX;

import jade.util.leap.ArrayList;
import java.io.Serializable;

/**
 *
 * @author couli
 */
public class CloudServiceConsumer implements Serializable {

        private String name;
        private ArrayList services;
        private int price;
      
    
    /**
     * @return the services
     */
    public ArrayList getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setService(String service) {
        services.add(service);
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
    
    public void setPrrice(int price){
    
        this.price = price;
    }
    
    public int getPrice(){
        return this.price;
    }
}
