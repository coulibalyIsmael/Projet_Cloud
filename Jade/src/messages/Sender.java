/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;


/**
 *
 * @author couli
 */
public class Sender extends Agent{
    
    protected void setup(){
    
        addBehaviour(new CyclicBehaviour(this){
            public void action(){
            
                ACLMessage msg = receive();
                if(msg != null){
                    System.out.println("== Answer"+ " <- "+ msg.getContent() + " from "+ msg.getSender().getLocalName());
                    block();
                }
            
            }
        });
        
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setContent("Ping");
        for(int i = 1; i <= 2; i++){
            msg.addReceiver(new AID("a"+i, AID.ISLOCALNAME));
        }
        send(msg);
    }
    
}
