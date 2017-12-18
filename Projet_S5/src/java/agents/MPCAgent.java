/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import bean.CloudServiceProvider;
import bean.SecureOfferNotification;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.*;

/**
 *
 * @author couli
 */
public class MPCAgent extends Agent implements CloudMarketVocabulary{
    private Object[] args;
    protected void setup() {
        args = getArguments();
        if (args != null) {
            //csp = (CloudServiceProvider) args[0];
            //System.out.println(csp);
            
             //AID providerName = new AID(csp.getName(), AID.ISLOCALNAME);

        SequentialBehaviour sb = new SequentialBehaviour();
       // sb.addSubBehaviour(new ProviderAgent.RegisterInDF(this));
        sb.addSubBehaviour(new MPCAgent.ReceivedMessages(this));
        addBehaviour(sb);
        }
        
        //create the provider with the appropriate name
       System.out.println(args +" Agent ");

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
                
                    case ACLMessage.INFORM:
                        System.out.println("Request from  Consumer"+ msg.getSender().getLocalName());
                        if(content instanceof String & content == NEED_COMPUTATION )
                           // addBehaviour(new ));
                        
                        break;
                        
                        
                
                
                
                }
            } catch (Exception e) {
            }
        }
    }
    
    
    
    class NeedComputationBehaviour extends OneShotBehaviour{

        private ACLMessage request;
        public NeedComputationBehaviour(Agent agent, ACLMessage msg) {
            super(agent);
            request = msg;
        }
        
        

        @Override
        public void action() {
           try {
            Process proc = Runtime.getRuntime().exec("/home/projets5/Desktop/Projet_S5/client.sh /"); //Whatever you want to execute
            BufferedReader read = new BufferedReader(new InputStreamReader(
                    proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            while (read.ready()) {
                System.out.println(read.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        }
    }
    

    
}
