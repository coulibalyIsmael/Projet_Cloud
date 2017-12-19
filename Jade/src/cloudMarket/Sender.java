/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudMarket;

import jade.content.ContentManager;
import jade.content.lang.*;
import jade.content.lang.xml.XMLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.*;

/**
 *
 * @author couli
 */
public class Sender  extends Agent{
    private Codec xmlCodec = new XMLCodec();
    private Ontology onto = CloudMarketOntology.getInstance();
    private ContentManager manager =  (ContentManager) getContentManager();
    protected void setup()
    {
     manager.registerLanguage(xmlCodec);
     manager.registerOntology(onto);

        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
// Fill message receiver and other message slots
        msg.setOntology(onto.getName());
        msg.setLanguage(XMLCodec.NAME);
        msg.addReceiver(new AID("Pong",AID.ISLOCALNAME));

SecureOfferTest fo = new SecureOfferTest();
fo.setId(""+hashCode());
fo.setName("AMAZON-1");
fo.setPrice(1000);
fo.createOffer("secureStorage", 1);
fo.createOffer("secureCompute", 2);
fo.createOffer("secureNetwork",3);

MyService sr = new MyService();
sr.setName("lol");
sr.setType("10");
/*Man m = new Man();
m.setName("John");
fo.setFather(m);
List children = new ArrayList();
m = new Man();
m.setName("Bill");
children.add(m);
Woman w = new Woman();
w.setName("Mary");
children.add(w);
fo.setChildren(children);*/
try {
manager.fillContent(msg, new Action(new AID("Pong",AID.ISLOCALNAME),fo));
send(msg);
}
catch (Exception e) {
e.printStackTrace();
}      
    }
    
}
