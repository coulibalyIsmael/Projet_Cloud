/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import bean.CloudServiceCustomer;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.util.leap.Iterator;
import jade.wrapper.gateway.*;
import java.util.Properties;

/**
 *
 * @author couli
 */

public class MonAgent extends GatewayAgent {

    public AID[] resultatFinal = null;
    private boolean finished = false;

    @Override
    protected void processCommand(Object obj) {
        System.out.println(this.getLocalName() + "--------******************----------");

        if (obj instanceof CloudServiceCustomer) {
            CloudServiceCustomer csc = (CloudServiceCustomer) obj;
            String catService = "";
            if (csc.listeChoix.length > 0) {
                for (String ser : csc.listeChoix) {
                    catService = catService + " " + ser;
                }
            }

            final String CAT_SERVICE = catService;
            System.out.println(catService);
            
            DFAgentDescription[] result = searchDF("provider", (CloudServiceCustomer) obj);
                    int count = 0;
                    for(int i=0; i<result.length; i++)  
                        csc.listeProviders.add(result[i].getName()); 
                    
                    releaseCommand(csc);
                    /* Debut:
                    for (int i = 0; i < result.length; i++) {
                        Iterator prop = result[i].getAllServices();
                        LesServices:
                        while (prop.hasNext()) {
                            Iterator it = ((ServiceDescription) prop.next()).getAllProperties();
                            System.out.println(((Properties) it).get("compute"));
                            if ((CAT_SERVICE.contains("compute") && ((Properties) it).get("compute") != null)
                                    || (CAT_SERVICE.contains("storage") && ((Properties) it).get("storage") != null)
                                    || (CAT_SERVICE.contains("network") && ((Properties) it).get("network") != null)) {
                                csc.listeProviders.add(result[i].getName());
                                System.out.println("11");
                            }
                        }
                    }*/
            
            

            /*addBehaviour(new SimpleBehaviour() {
                @Override
                public void action() {
                    //System.out.println("hhhhhhhisshjhgfdfgdfhfgdsfg");
                    DFAgentDescription[] result = searchDF("provicer", (CloudServiceCustomer) obj);
                    int count = 0;
                    Debut:
                    for (int i = 0; i < result.length; i++) {
                        Iterator prop = result[i].getAllServices();
                        LesServices:
                        while (prop.hasNext()) {
                            Iterator it = ((ServiceDescription) prop.next()).getAllProperties();
                            System.out.println(((Properties) it).get("compute"));
                            if ((CAT_SERVICE.contains("compute") && ((Properties) it).get("compute") != null)
                                    || (CAT_SERVICE.contains("storage") && ((Properties) it).get("storage") != null)
                                    || (CAT_SERVICE.contains("network") && ((Properties) it).get("network") != null)) {
                                csc.listeProviders.add(result[i].getName());
                                System.out.println("11");
                            }
                        }
                    }

                }

                @Override
                public boolean done() {
                    return finished;
                }

            });*/

        }

    }

    @Override
    protected void setup() {
        super.setup();
        System.out.println("Le monde");
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
