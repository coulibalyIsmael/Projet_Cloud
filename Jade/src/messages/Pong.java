/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

/**
 *
 * @author couli
 */
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;


public class Pong extends Agent 
{
	
    protected void setup() 
    {
		addBehaviour(new CyclicBehaviour(this) 
		{
			 public void action() 
			 {
				ACLMessage msg = receive();
				if (msg!=null) {
					System.out.println( " - " +
					   myAgent.getLocalName() + " received: " +
					   msg.getContent() );
					   
					ACLMessage reply = msg.createReply();
					reply.setPerformative( ACLMessage.INFORM );
					reply.setContent(" Pong" );
					send(reply);
				 }
				 block();
			 }
		});
	}
}
