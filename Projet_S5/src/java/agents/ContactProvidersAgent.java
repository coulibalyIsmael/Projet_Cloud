/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import bean.CloudServiceConsumer;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.gateway.GatewayAgent;
import jade.wrapper.gateway.JadeGateway;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author couli
 */
public class ContactProvidersAgent extends GatewayAgent {

    Random rnd = newRandom();
    ACLMessage msg;
    ArrayList<DFAgentDescription> providers;

    @Override
    protected void processCommand(Object command) {
        
        providers =(ArrayList<DFAgentDescription>) command;
          msg = newMsg(ACLMessage.QUERY_REF);

        MessageTemplate template = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchConversationId(msg.getConversationId()));
        
        SequentialBehaviour seq = new SequentialBehaviour();
		addBehaviour( seq );
                
        ParallelBehaviour par = new ParallelBehaviour( ParallelBehaviour.WHEN_ALL );
		seq.addSubBehaviour( par );
        
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

//  --- generating distinct Random generator -------------------
    Random newRandom() {
        return new Random(hashCode() + System.currentTimeMillis());
    }

    //  --- Methods to initialize ACLMessages -------------------
    ACLMessage newMsg(int perf, String content, AID dest) {
        ACLMessage msg = newMsg(perf);
        if (dest != null) {
            msg.addReceiver(dest);
        }
        msg.setContent(content);
        return msg;
    }

    ACLMessage newMsg(int perf) {
        ACLMessage msg = new ACLMessage(perf);
        msg.setConversationId(genCID());
        return msg;
    }

}
