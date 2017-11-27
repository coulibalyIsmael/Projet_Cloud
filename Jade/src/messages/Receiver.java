/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author couli
 */
public class Receiver extends Agent {
    
    protected void setup(){
        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if(msg!=null)
                    System.out.println(" - " + myAgent.getLocalName()+ " <- "+msg.getContent());
                block();
                    
            }
        } );
     
    }
    
    
}
