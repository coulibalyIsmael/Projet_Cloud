/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import bean.CloudServiceConsumer;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.gateway.GatewayAgent;
import jade.wrapper.gateway.JadeGateway;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author couli
 */
public class ContactProvidersAgent extends GatewayAgent {

    ACLMessage msg;
    CloudServiceConsumer csc;

    @Override
    protected void processCommand(Object command) {
        
        csc =(CloudServiceConsumer) command;
        
        DFAgentDescription[] providers = csc.getListeProviders().get(0);
          //msg = newMsg(ACLMessage.INFORM);

      /*  MessageTemplate template = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchConversationId(msg.getConversationId()));*/
        
        //SequentialBehaviour seq = new SequentialBehaviour();
		///addBehaviour( seq );
                
       // ParallelBehaviour par = new ParallelBehaviour( ParallelBehaviour.WHEN_ALL );
		//seq.addSubBehaviour( par );
                
                addBehaviour(new OneShotBehaviour(this) {
                    @Override
                    public void action() {
                       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        try {
                            System.out.println("Message sending ...");
                            AMSAgentDescription[] agents = AMSService.search(myAgent, new AMSAgentDescription());
                             ACLMessage forward = newMsg(ACLMessage.INFORM, providers, new AID(csc.getName(),AID.ISLOCALNAME));
                             send(forward);
                             releaseCommand(csc);
                        } catch (Exception e) {
                        }
                      
                       
                    }
                });
        
    }
    

    protected void setup() {
        
      

    }

    //  --- generating Conversation IDs -------------------
    protected static int cidCnt = 0;
    String cidBase;

    String genCID() {
        if (cidBase == null) {
            cidBase = getLocalName() + hashCode()
                    + System.currentTimeMillis() % 10000 + "_";
        }
        return cidBase + (cidCnt++);
    }


    //  --- Methods to initialize ACLMessages -------------------
    ACLMessage newMsg(int perf, DFAgentDescription[] content, AID dest) throws IOException {
        ACLMessage msg = newMsg(perf);
        if (dest != null) {
            msg.addReceiver(dest);
        }
        msg.setContentObject((Serializable)content);
        return msg;
    }

    ACLMessage newMsg(int perf) {
        ACLMessage msg = new ACLMessage(perf);
        msg.setConversationId(genCID());
        return msg;
    }

}
