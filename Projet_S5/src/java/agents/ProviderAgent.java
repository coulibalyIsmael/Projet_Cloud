/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAAgentManagement.Register;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import bean.*;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.Property;
import jade.lang.acl.*;
import java.io.Serializable;
import java.util.Random;
//import jade.domain.introspection.ACLMessage;

/**
 *
 * @author couli
 */
public class ProviderAgent extends Agent implements CloudMarketVocabulary {

    private Object[] args;
    private CloudServiceProvider csp;

    protected void setup() {
        args = getArguments();
        if (args != null) {
            csp = (CloudServiceProvider) args[0];
            System.out.println(csp);
            
             AID providerName = new AID(csp.getName(), AID.ISLOCALNAME);

        SequentialBehaviour sb = new SequentialBehaviour();
        sb.addSubBehaviour(new RegisterInDF(this));
        addBehaviour(sb);
        }
        
        //create the provider with the appropriate name
       System.out.println(args +" Agent ");

    }

    //Register the agent services into the yellow page of the DF
    class RegisterInDF extends OneShotBehaviour {

        RegisterInDF(Agent agent) {
            super(agent);
            //System.out.println(csc.getAID());

        }

        @Override
        public void action() {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            
            //for(A)
            //ServiceDescription sd = new ServiceDescription();
           DFAgentDescription dfd = new DFAgentDescription();
           dfd.setName(getAID());
            
            for (MyService srv : csp.getProviderServices()) {
                ServiceDescription sd = new ServiceDescription();
                sd.setName(srv.getName());
                sd.setType(srv.getType());
                sd.setOwnership(csp.getName());
                dfd.addServices(sd);
                
            }
            
            try {
            DFAgentDescription[] dfds = DFService.search(myAgent, dfd);
            if (dfds.length > 0 ) {
               DFService.deregister(myAgent, dfd);
            }
            DFService.register(myAgent, dfd);
            System.out.println(getLocalName() + " is ready.");
         }
         catch (Exception ex) {
            System.out.println("Failed registering with DF! Shutting down...");
            ex.printStackTrace();
            doDelete();
         }

        }
    }
    //SecureNotification message from Cloud service Customer
    class SecureNotification implements Serializable{
    
    
    }
    
    
    //Handle messages 
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
                
                    case ACLMessage.PROPOSE:
                        System.out.println("Request from "+ msg.getSender().getLocalName());
                        if(content instanceof SecureNotification)
                            addBehaviour(new HandleSecureNotificationMessage(myAgent, msg));
                        break;
                        
                        
                
                
                
                }
            } catch (Exception e) {
            }
        }
    }
    
    
    //Handle SecureNotification message 
    
    class HandleSecureNotificationMessage extends OneShotBehaviour{
        private ACLMessage request;

        public HandleSecureNotificationMessage(Agent a, ACLMessage request) {
            super(a);
            this.request = request;
            
        }
        
        @Override
        public void action() {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            try {
                SecureOfferNotification notif = (SecureOfferNotification) request.getContentObject();
                Offer offer = new Offer();
                offer.setPrice(new  Random().nextFloat());
                offer.setMsg("Provider offer");
                ACLMessage reply = request.createReply();
                reply.setPerformative(ACLMessage.INFORM);
                reply.setContentObject(offer);
                send(reply);
                
                
            } catch (Exception e) {
            }
        }
    }
    
    
        
    

}
