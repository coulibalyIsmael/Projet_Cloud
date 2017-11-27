/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;

/**
 *
 * @author couli
 */
public class AMSSearch  extends Agent{
    
    protected void setup(){
        AMSAgentDescription[] agents= null;
        try {
            
            SearchConstraints c = new SearchConstraints();
            c.setMaxResults(new Long(-1));
            agents = AMSService.search(this, new AMSAgentDescription(), c);
            
        } catch (Exception e) {
            System.out.println("Problem searching AMS: "+ e);
            e.printStackTrace();
        }
        
        AID myID = getAID();
        for (int i = 0; i < agents.length; i++) {
            AID agentID = agents[i].getName();
            System.out.println((agentID.equals(myID)? "**** ":"   ")+ i+ ": "+ agentID.getName());
            
        }
        
        doDelete();
        System.exit(0);
    }

   
    
}
