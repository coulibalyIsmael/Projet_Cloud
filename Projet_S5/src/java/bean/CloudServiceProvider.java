/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import interfaces.CloudServicex;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.Property;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import org.w3c.dom.Document;

/**
 *
 * @author couli
 */
public class CloudServiceProvider implements Serializable, CloudServicex {

    private String name;
    private ArrayList<MyService> providerServices;
    private String idProvider;

    public CloudServiceProvider() {
        providerServices = new ArrayList();

    }

    @Override
    public void setName(String name) {

        this.name = name;

    }

    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return the providerServices
     */
    @Override
    public ArrayList<MyService> getServices() {
        return providerServices;
    }

    /**
     * @param providerServices the providerServices to set
     */
    @Override
    public void addServices(String service, int level) {
        MyService srv = new MyService();
        srv.setName(service);
        srv.setType(String.valueOf(level));

        this.providerServices.add(srv);
    }

    @Override
    public void setID(String id) {
        this.idProvider = id;
    }

    @Override
    public String getID() {
        return this.idProvider;
    }

}
