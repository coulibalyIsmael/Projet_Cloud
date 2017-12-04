/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import jade.wrapper.gateway.GatewayAgent;
import bean.CloudServiceCustomer;
import bean.CloudServiceProvider;
import bean.MyService;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.MyFilterImage;
import jade.util.leap.Iterator;
import jade.wrapper.gateway.*;
import java.util.Properties;
import jade.domain.FIPAAgentManagement.Property;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

/**
 *
 * @author couli
 */
public class ProviderGateway extends GatewayAgent{
    
    public AID[] resultatFinal = null;
    private boolean finished = false;

    @Override
    protected void processCommand(Object obj) {
        System.out.println(this.getLocalName() + "--------******************----------");

        if (obj instanceof CloudServiceProvider) {
            CloudServiceProvider csp = (CloudServiceProvider) obj;
           // String catService = "";
            AgentContainer container = getContainerController();

            try {
                System.out.println(csp.getName() + "--------");
                AgentController a = container.createNewAgent(csp.getName(), "agents.ProviderAgent",new Object[] {csp});
                a.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
         

                    releaseCommand(csp);
                    
            
            

         }

    }

    @Override
    protected void setup() {
        super.setup();
        System.out.println("Provider Gateway");
        //block(20);
        System.out.println(this.getLocalName() + "--------+++++++++++-----------");
    }

    DFAgentDescription[] searchDF(String service, CloudServiceCustomer obj) //  ---------------------------------
    {
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(service);
        dfd.addServices(sd);

        SearchConstraints ALL = new SearchConstraints();
        ALL.setMaxResults(new Long(-1));

        try {
            DFAgentDescription[] result = DFService.search(this, dfd, ALL);
            //AID[] agents = new AID[result.length];

            /*for (int i=0; i<result.length; i++) 
				agents[i] = result[i].getName() ;*/
            return result;

        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        return null;
    }
    
}
