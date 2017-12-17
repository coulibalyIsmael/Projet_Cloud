/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudServiceX;

import jade.core.AID;
import jade.core.Agent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

/**
 *
 * @author couli
 */
public class Essai extends Agent {
    
    protected void setup(){
        String name = "esssai";
        AgentContainer c = getContainerController();
        try {
            AgentController a = c.createNewAgent(name, "Pong", null);
            a.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
      
        System.out.println("Teste");
    
    }
    
    
    
    
}
