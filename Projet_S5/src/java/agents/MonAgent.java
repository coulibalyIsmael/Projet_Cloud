/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import bean.CloudServiceConsumer;
import bean.CloudServiceProvider;
import bean.MyService;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.AMSService;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.MyFilterImage;
import jade.util.leap.Iterator;
import jade.wrapper.gateway.*;
import java.util.Properties;
import jade.domain.FIPAAgentManagement.Property;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author couli
 */
public class MonAgent extends GatewayAgent implements CloudMarketVocabulary {
    //Command to execute 

    //-----------------------------
    public AID[] resultatFinal = null;
    private boolean finished = false;
    CloudServiceConsumer csc;
    
    @Override
    protected void processCommand(Object obj) {
        System.out.println(this.getLocalName() + "--------******************----------");
        HashMap<String, Object> cmd = (HashMap<String, Object>) obj;
        if (cmd.containsKey(CREATE_CONSUMER)) {
            csc = (CloudServiceConsumer) cmd.get(CREATE_CONSUMER);
            System.out.println(csc.getName());
            //String catService = "";
            ArrayList<MyService> service = csc.getServices();
            for (MyService srv : csc.getServices()) {
                System.out.println(srv.getName() + srv.getType());
            }

            // final String CAT_SERVICE = catService;
            // System.out.println(catService);
            //--------------------------------------------------------------------------------
            //Creation of Cloud service Consumer
            try {
                AgentContainer container = getContainerController();
                AgentController a = container.createNewAgent(csc.getName(), "agents.ConsumerAgent", new Object[]{csc});
                a.start();
                ACLMessage sendMsg = new ACLMessage(ACLMessage.INFORM);
                sendMsg.addReceiver(new AID(csc.getName(), AID.ISLOCALNAME));
                sendMsg.setContentObject(csc);
                send(sendMsg);
                ACLMessage msg = blockingReceive();
                
                DFAgentDescription[] result = (DFAgentDescription[]) msg.getContentObject();
                
                csc.getListeProviders().add(result);
                //releaseCommand(csc);

            } catch (Exception e) {
            }
        } 
        
        else if (cmd.containsKey(CONTACT_PROVIDER)) {
            //CloudServiceProvider csp = (CloudServiceProvider) cmd.get(obj);
           System.out.println(csc.getListeProviders().get(0)[0].getName().getLocalName() + "+++++++++++++++++++++++//////////////////////+++++++++++");
            DFAgentDescription[] providers = csc.getListeProviders().get(0);
            try {
                System.out.println("Message sending ---------...------------");
                System.out.println(csc.getName());
                //AMSAgentDescription[] agents = AMSService.search(myAgent, new AMSAgentDescription());
                ACLMessage forward = newMsg(ACLMessage.INFORM, providers, new AID(csc.getName(), AID.ISLOCALNAME));
                send(forward);
                blockingReceive();
                
            } catch (Exception e) {
            }

            /*addBehaviour(new OneShotBehaviour(this) {
                    @Override
                    public void action() {
                       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        try {
                            System.out.println("Message sending ---------...------------");
                            System.out.println(csc.getName());
                            //AMSAgentDescription[] agents = AMSService.search(myAgent, new AMSAgentDescription());
                             ACLMessage forward = newMsg(ACLMessage.INFORM, providers, new AID(csc.getName(),AID.ISLOCALNAME));
                             send(forward);
                             blockingReceive();
                             
                        } catch (Exception e) {
                        }
                      
                       
                    }
                });*/
        } else if (cmd.containsKey(CREATE_PROVIDER)) {
            
            CloudServiceProvider csp = (CloudServiceProvider) cmd.get(CREATE_PROVIDER);
            // String catService = "";
            AgentContainer container = getContainerController();
            
            try {
                System.out.println(csp.getName() + "--------");
                AgentController a = container.createNewAgent(csp.getName(), "agents.ProviderAgent", new Object[]{csp});
                a.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else {
            System.out.println("Unknown command");
        }
        
        releaseCommand(obj);
        
    }
    
    @Override
    protected void setup() {
        super.setup();
        // System.out.println("Le monde");
        //block(20);
        //System.out.println(this.getLocalName() + "--------+++++++++++-----------");
    }
    
    DFAgentDescription[] searchDF(String service, CloudServiceConsumer obj) //  ---------------------------------
    {
        //supposition que tous les providers offrent les trois services storage compute et network
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setName(service);
        dfd.addServices(sd);
        
        SearchConstraints ALL = new SearchConstraints();
        ALL.setMaxResults(new Long(-1));
        
        try {
            DFAgentDescription[] result = DFService.search(this, dfd, ALL);
            
            return result;
            
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
        
        return null;
    }

    //////////////////////////////////////////////////////////----------------------------------------------------------------------------------
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

    //  --- Methods to initialize ACLMessages -------------------
    ACLMessage newMsg(int perf, DFAgentDescription[] content, AID dest) throws IOException {
        ACLMessage msg = newMsg(perf);
        if (dest != null) {
            msg.addReceiver(dest);
        }
        msg.setContentObject(content);
        return msg;
    }
    
    ACLMessage newMsg(int perf) {
        ACLMessage msg = new ACLMessage(perf);
        msg.setConversationId(genCID());
        return msg;
    }
    
}
