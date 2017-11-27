/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import jade.domain.FIPAAgentManagement.Property;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.util.*;



/**
 *
 * @author couli
 */
public class DF2 extends Agent{

protected void setup(){
    ServiceDescription sd = new ServiceDescription();
    sd.setType("provider");
    sd.setName(getLocalName());
    Property comp = new Property("compute", "compute");
    Property st = new Property("storage","storage");
    Property net = new Property("network", "network");
    sd.addProperties(comp);
    sd.addProperties(net);
    sd.addProperties(st);
    register(sd);
}  


void register(ServiceDescription sd){

    DFAgentDescription dfd = new DFAgentDescription();
    dfd.setName(getAID());
    dfd.addServices(sd);
    try {
        DFService.register(this, dfd);
    } catch (FIPAException e) {
    }
}

//Se désenregistrer de la page jaune DF, vu que lorsqu'un agent meurt il est  directement retiré par le MAS de la platform 
//mais l' entrée n' est pas supprimée.
protected void takeDown(){
    
    try {
        DFService.deregister(this);
    } catch (Exception e) {
    }
}

}
