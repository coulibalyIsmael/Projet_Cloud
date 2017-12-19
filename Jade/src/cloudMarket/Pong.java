/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudMarket;

/**
 *
 * @author couli
 */
import jade.content.lang.Codec;
import jade.content.onto.OntologyException;
import messages.*;
import jade.core.Agent;
import jade.core.behaviours.*;
import jade.lang.acl.*;
import java.util.logging.Level;
import java.util.logging.Logger;


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
					   myAgent.getLocalName() + " received: "
					    );
                                    try {
                                        getContentManager().extractAbsContent(msg);
                                    } catch (Codec.CodecException ex) {
                                        Logger.getLogger(Pong.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (OntologyException ex) {
                                        Logger.getLogger(Pong.class.getName()).log(Level.SEVERE, null, ex);
                                    }
				 }
				 block();
			 }
		});
	}
}
