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
import jade.core.behaviours.*;
import jade.core.AID;
import jade.lang.acl.*;

import java.util.*;
import jade.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class CSC extends Agent {

    Random rnd = newRandom();
    int bestPrice = 9999;

    ACLMessage msg,
            bestOffer;
    Behaviour flusher;

    protected void setup() {
        if (flusher == null) {
            flusher = new GCAgent(this, 2000);
            // addBehaviour( flusher);
        }
        bestPrice = 9999;
        bestOffer = null;

        msg = newMsg(ACLMessage.QUERY_REF);

        MessageTemplate template = MessageTemplate.and(
                MessageTemplate.MatchPerformative(ACLMessage.INFORM),
                MessageTemplate.MatchConversationId(msg.getConversationId()));

        System.out.println("CSC " + getLocalName() + " gets prices.");

        SequentialBehaviour seq = new SequentialBehaviour();
        addBehaviour(seq);

        ParallelBehaviour par = new ParallelBehaviour(ParallelBehaviour.WHEN_ALL);
        seq.addSubBehaviour(par);
        
        //initialisation du service et recherche des fournisseur de service cloud
        ServiceDescription sd  = new ServiceDescription();
        sd.setType( "buyer" );
        sd.setName( getLocalName() );
        register( sd );
        
      
        
        AID[] agents = searchDF("CSP");
        if(agents.length>0){
            for(int i= 0; i< agents.length; i++)
            {
                msg.addReceiver(agents[i]);
            par.addSubBehaviour(new myReceiver(this, 1000, template) {
                public void handle(ACLMessage msg) {
                    if (msg != null) {
                        int offer = Integer.parseInt(msg.getContent());
                        System.out.println("Got quote $" + offer
                                + " from " + msg.getSender().getLocalName());
                        if (offer < bestPrice) {
                            bestPrice = offer;
                            bestOffer = msg;
                        }
                    }
                }
            });
            }
        
        }
        

        /* for (int i = 1; i <= 3; i++) {
        msg.addReceiver(new AID("s" + i, AID.ISLOCALNAME));
        par.addSubBehaviour(new myReceiver(this, 1000, template) {
        public void handle(ACLMessage msg) {
        if (msg != null) {
        int offer = Integer.parseInt(msg.getContent());
        System.out.println("Got quote $" + offer
        + " from " + msg.getSender().getLocalName());
        if (offer < bestPrice) {
        bestPrice = offer;
        bestOffer = msg;
        }
        }
        }
        });
        }*/

        seq.addSubBehaviour(new DelayBehaviour(this, rnd.nextInt(2000)) {
            public void handleElapsedTimeout() {
                if (bestOffer == null) {
                    System.out.println("Got no quotes");
                } else {
                    System.out.println("\nBest Price $" + bestPrice
                            + " from " + bestOffer.getSender().getLocalName());
                    ACLMessage reply = bestOffer.createReply();
                    if (bestPrice <= 80) {
                        reply.setPerformative(ACLMessage.REQUEST);
                        reply.setContent("" + rnd.nextInt(80));
                        System.out.print(" ORDER at " + reply.getContent());
                        send(reply);
                    }
                }
            }
        });

        seq.addSubBehaviour(new myReceiver(this, 1000,
                MessageTemplate.and(
                        MessageTemplate.MatchConversationId(msg.getConversationId()),
                        MessageTemplate.or(
                                MessageTemplate.MatchPerformative(ACLMessage.AGREE),
                                MessageTemplate.MatchPerformative(ACLMessage.REFUSE)))) {
            public void handle(ACLMessage msg) {
                if (msg != null) {
                    System.out.println("Got "
                            + ACLMessage.getPerformative(msg.getPerformative())
                            + " from " + msg.getSender().getLocalName());

                    if (msg.getPerformative() == ACLMessage.AGREE) {
                        System.out.println("  --------- Finished ---------\n");
                    } else {
                        setup();
                    }
                } else {
                    System.out.println("==" + getLocalName()
                            + " timed out");
                    setup();
                }
            }
        });

        send(msg);
    }

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
                    System.out.println("==== Flushing message:");
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
    
    AID getService( String service )
//  ---------------------------------
	{
		DFAgentDescription dfd = new DFAgentDescription();
   		ServiceDescription sd = new ServiceDescription();
   		sd.setType( service );
		dfd.addServices(sd);
		try
		{
			DFAgentDescription[] result = DFService.search(this, dfd);
			if (result.length>0)
				return result[0].getName() ;
		}
        catch (FIPAException fe) { fe.printStackTrace(); }
      	return null;
	}

	AID [] searchDF( String service )
//  ---------------------------------
	{
		DFAgentDescription dfd = new DFAgentDescription();
   		ServiceDescription sd = new ServiceDescription();
   		sd.setType( service );
		dfd.addServices(sd);
		
		SearchConstraints ALL = new SearchConstraints();
		ALL.setMaxResults(new Long(-1));

		try
		{
			DFAgentDescription[] result = DFService.search(this, dfd, ALL);
			AID[] agents = new AID[result.length];
			for (int i=0; i<result.length; i++) 
				agents[i] = result[i].getName() ;
			return agents;

		}
        catch (FIPAException fe) { fe.printStackTrace(); }
        
      	return null;
	}

}
