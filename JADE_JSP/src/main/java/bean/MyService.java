/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import jade.domain.FIPAAgentManagement.Property;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author couli
 */
public class MyService implements Serializable{
    private String type;
    private ArrayList<Property> properties;
    private String name;

    public MyService() {
        
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the properties
     */
    public ArrayList<Property> getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
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
    
    public void addPropertyService(Property prop){
        this.properties.add(prop);
    }
    
    
    
}
