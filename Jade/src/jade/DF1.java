/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.DFService;
import jade.core.behaviours.*;
import jade.domain.FIPAException;

/**
 *
 * @author couli
 */
public class DF1 extends Agent
{
    protected void setup(){
        
        DFAgentDescription dfd = new  DFAgentDescription();
        dfd.setName(getAID());
        register(dfd);
    }
    
   void register(DFAgentDescription dfd){
        try{
            DFService.register(this, dfd);
        }
        catch(FIPAException e)
        {
            e.printStackTrace();
        }
    }
}
