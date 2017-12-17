/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudServiceX;

/**
 *
 * @author couli
 */

import java.io.Serializable;
import java.util.ArrayList;
public class CloudServiceProvider implements Serializable{
    private String name;
    private ArrayList<Service> servicesP;
    
}

class Service{
    private String name;
    private String description;
    Service(String name, String description){
    
        this.name = name ;
        this.description = description;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    


}
