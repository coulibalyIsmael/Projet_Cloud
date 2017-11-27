/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.Random;

/**
 *
 * @author couli
 */
public class Acheteur1  extends Agent{
    
    MessageTemplate template;
    Random rnd = new Random(hashCode());
    
    
    protected void setup(){
        ACLMessage  msg = newMsg(ACLMessage.QUERY_REF, "", new AID("s1", AID.ISLOCALNAME));
        template = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM), 
                MessageTemplate.MatchConversationId(msg.getConversationId()));
        
        addBehaviour(new myReceiver(this, 1000, template)
        {
            public void handle(ACLMessage msg) {
                if(msg == null)
                    System.out.println("Acheteur: Timeout");
                else
                    System.out.println("Acheteur a reçu: "+ msg);
                 
            }
        });
        
        send(msg);
        
    }
   
    
    //--------------Initialize the ACL messages-----
    ACLMessage newMsg(int perf, String content, AID dest){
        
        ACLMessage msg = new ACLMessage(perf);
        if(dest != null)
            msg.addReceiver(dest);
        msg.setContent(content);
        return msg;
    }
    
    
    ACLMessage newMsg(int perf){
        ACLMessage msg = new ACLMessage(perf);
        msg.setConversationId(genCID());
        return msg;
    }
    
    //--------------generating conservation ID---------------
    protected static int cidCnt =0;
    String cidBase;
    String genCID(){
    
        if(cidBase == null)
            cidBase = getLocalName() + hashCode() + System.currentTimeMillis()+"_";
        return cidBase + (cidCnt++);
    }
    
}
