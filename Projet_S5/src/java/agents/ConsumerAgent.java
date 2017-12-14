/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import bean.*;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author couli
 */
public class ConsumerAgent  extends Agent implements CloudMarketVocabulary{
    
    private Object[] args;
    private CloudServiceConsumer csc;
    protected static int cidCnt = 0;
    MessageTemplate templateSecureNotif;
    
    protected void setup() {
        args = getArguments();
        if (args != null) {
            csc= (CloudServiceConsumer) args[0];
            //System.out.println(csc);
            
             AID providerName = new AID(csc.getName(), AID.ISLOCALNAME);

        SequentialBehaviour sb = new SequentialBehaviour();
        sb.addSubBehaviour(new ReceivedMessages(this));
        addBehaviour(sb);
        }
        
        //create the provider with the appropriate name
       System.out.println(args +" Agent ");

    }
    
    //------------------
    class ReceivedMessages extends CyclicBehaviour{
        
        public  ReceivedMessages(Agent a){
            super(a);
        }

        @Override
        public void action() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            ACLMessage msg = receive();
            if(msg == null)
            {block();
            return;
            }
            try {
                Object content = msg.getContentObject();
                switch(msg.getPerformative()){
                
                    case ACLMessage.REQUEST:
                        System.out.println("Request from "+ msg.getSender().getLocalName());
                        if(content instanceof CloudServiceConsumer)
                            addBehaviour(new SearchInDF(myAgent, msg));
                        else if(content instanceof DFAgentDescription)
                            
                        break;
                        
                        
                
                
                
                }
            } catch (Exception e) {
            }
        }
    }
    //-----------------
    
    //-------------------------------------Search services-------------------------------------------
    class SearchInDF extends OneShotBehaviour{
        private ACLMessage request;

        public SearchInDF(Agent a, ACLMessage msg) {
            super(a);
            request = msg;
            
        }

        @Override
        public void action() {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           
            DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Provider");
        dfd.addServices(sd);

        SearchConstraints ALL = new SearchConstraints();
        ALL.setMaxResults(new Long(-1));
        try {
            DFAgentDescription[] result = DFService.search(myAgent, dfd, ALL);
            
            ACLMessage reply = request.createReply();
            reply.setPerformative(ACLMessage.INFORM);
            reply.setContentObject(result);
            send(reply);
        } 
        catch (Exception fe) {
            fe.printStackTrace();
        }
        }
    
    }
    
    //-----send secure notification---------------------------------
    
    class sendSecureNotification extends OneShotBehaviour{
        private ACLMessage request;
        public sendSecureNotification(Agent agent, ACLMessage msg) {
            super(agent);
            request = msg;
            
        }
        

        @Override
        public void action() {
            try {
                ACLMessage msg = newMsg(ACLMessage.INFORM, new SecureOfferNotification("Notification") );
                templateSecureNotif = MessageTemplate.and( 
            MessageTemplate.MatchPerformative( ACLMessage.INFORM ),
            MessageTemplate.MatchConversationId( msg.getConversationId() )); 
                
                 for(DFAgentDescription agentDescr: csc.getListeProviders().get(0))
             {  
                 msg.addReceiver( new AID( agentDescr.getName().getLocalName(),  AID.ISLOCALNAME ));
                 send(msg);
                
             
             }
            
            } catch (Exception e) {
            }
            
             
             
            
            
        }
        
        //  --- generating Conversation IDs -------------------
    
    String cidBase;

    String genCID() {
        if (cidBase == null) {
            cidBase = getLocalName() + hashCode()
                    + System.currentTimeMillis() % 10000 + "_";
        }
        return cidBase + (cidCnt++);
    }

//  --- generating distinct Random generator -------------------
    Random newRandom() {
        return new Random(hashCode() + System.currentTimeMillis());
    }

    //  --- Methods to initialize ACLMessages -------------------
    ACLMessage newMsg(int perf, Object content) throws IOException {
        ACLMessage msg = newMsg(perf);
        msg.setContentObject((Serializable) content);
        return msg;
    }

    ACLMessage newMsg(int perf) {
        ACLMessage msg = new ACLMessage(perf);
        msg.setConversationId(genCID());
        return msg;
    }
    
    
    
    }
    
    
    
    
    
}
