/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

/**
 *
 * @author couli
 */
public class SearchDF1 extends Agent{
    
    protected void setup(){
    
           ServiceDescription sd = new ServiceDescription();
           sd.setType("buyer");
           sd.setName(getLocalName());
           register(sd);
          
           
           try {
               DFAgentDescription dfd = new DFAgentDescription();
               DFAgentDescription[] result= DFService.search(this, dfd);
               System.out.println("Search returns: "+ result.length + " elements");
               
               if(result.length>0)
                   System.out.println(" "+ result[0].getName());
               
               sd = new ServiceDescription();
               sd.setType("buyer");
               dfd.addServices(sd);
               result = DFService.search(this, dfd);
               System.out.println(" " + result[0].getName());
               
               sd.setType("seller");
               result = DFService.search(this, dfd);
               if (result==null)
                   System.out.println("Search1 returns null");
               else{
                   System.out.println(" Search for SELLER: "+  result.length + " elements");
                   if(result.length>0)
                       System.out.println(" "+ result[0].getName());
               
               }
        } catch (FIPAException e) { e.printStackTrace();
        }
           //System.exit(0);
    }
    
    void register(ServiceDescription sd){
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        
    }
       AID getService(String service){
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(service);
        dfd.addServices(sd);
       
           try {
               DFAgentDescription[] result = DFService.search(this, dfd);
               if(result.length > 0)
                   return result[0].getName();
               
           } catch (Exception e) {
               e.printStackTrace();
           }
          return null;
    }
        
    
    
}
