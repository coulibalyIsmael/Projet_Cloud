/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

/**
 *
 * @author couli
 */
import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.lang.acl.*;

import java.util.*;

import jade.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class CSP extends Agent {

    Random rnd = newRandom();
    MessageTemplate query = MessageTemplate.MatchPerformative(ACLMessage.QUERY_REF);

    protected void setup() {
        ServiceDescription sd = new ServiceDescription();
        sd.setType("CSP");
        sd.setName(getLocalName());
        register(sd);
        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive(query);
                if (msg != null) {
                    addBehaviour(new Transaction(myAgent, msg));
                }
                block();
            }
        });
        // addBehaviour( new GCAgent( this, 5000));
    }

    class Transaction extends SequentialBehaviour {

        ACLMessage msg,
                reply;
        String ConvID;

        int price = rnd.nextInt(100);

        public Transaction(Agent a, ACLMessage msg) {
            super(a);
            this.msg = msg;
            ConvID = msg.getConversationId();
        }

        public void onStart() {
            int delay = delay = rnd.nextInt(2000);
            System.out.println(" - "
                    + myAgent.getLocalName() + " <- QUERY from "
                    + msg.getSender().getLocalName()
                    + ".  Will answer $" + price + " in " + delay + " ms");

            addSubBehaviour(new DelayBehaviour(myAgent, delay) {
                public void handleElapsedTimeout() {
                    reply = msg.createReply();
                    reply.setPerformative(ACLMessage.INFORM);
                    reply.setContent("" + price);
                    send(reply);
                }
            });

            MessageTemplate template = MessageTemplate.and(
                    MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
                    MessageTemplate.MatchConversationId(ConvID));

            addSubBehaviour(new myReceiver(myAgent, 2000, template) {
                public void handle(ACLMessage msg1) {
                    if (msg1 != null) {

                        int offer = Integer.parseInt(msg1.getContent());
                        System.out.println("Got proposal $" + offer
                                + " from " + msg1.getSender().getLocalName()
                                + " & my price is $" + price);

                        reply = msg1.createReply();
                        if (offer >= rnd.nextInt(price)) {
                            reply.setPerformative(ACLMessage.AGREE);
                        } else {
                            reply.setPerformative(ACLMessage.REFUSE);
                        }
                        send(reply);
                        System.out.println("  == "
                                + ACLMessage.getPerformative(reply.getPerformative()));
                    } else {
                        System.out.println("Timeout ! quote $" + price
                                + " from " + getLocalName()
                                + " is no longer valid");
                    }
                }
            });
        }

    }  // --- Answer class ---

// ========== Utility methods =========================
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

// ----- clean-up agent which takes old messages out of the queue
    class GCAgent extends TickerBehaviour {

        Set seen = new HashSet(),
                old = new HashSet();

        GCAgent(Agent a, long dt) {
            super(a, dt);
        }

        protected void onTick() {
            ACLMessage msg = myAgent.receive();
            while (msg != null) {
                if (!old.contains(msg)) {
                    seen.add(msg);
                } else {
                    System.out.print("+++ Flushing message: ");
                    dumpMessage(msg);
                }
                msg = myAgent.receive();
            }
            for (Iterator it = seen.iterator(); it.hasNext();) {
                myAgent.putBack((ACLMessage) it.next());
            }

            old.clear();
            Set tmp = old;
            old = seen;
            seen = tmp;
        }
    }

// ---------- Message print-out --------------------------------------
    static long t0 = System.currentTimeMillis();

    void dumpMessage(ACLMessage msg) {
        System.out.print("t=" + (System.currentTimeMillis() - t0) / 1000F + " in "
                + getLocalName() + ": "
                + ACLMessage.getPerformative(msg.getPerformative()));
        System.out.print("  from: "
                + (msg.getSender() == null ? "null" : msg.getSender().getLocalName())
                + " --> to: ");
        for (Iterator it = msg.getAllReceiver(); it.hasNext();) {
            System.out.print(((AID) it.next()).getLocalName() + ", ");
        }
        System.out.println("  cid: " + msg.getConversationId());
        System.out.println("  content: " + msg.getContent());
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

    
}  // ========== Seller ==========

