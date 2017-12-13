/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import jade.wrapper.gateway.DynamicJadeGateway;
import jade.wrapper.gateway.GatewayAgent;
import jade.wrapper.gateway.JadeGateway;

/**
 *
 * @author couli
 */
public class EntryPointJade {
    
    private static DynamicJadeGateway entryJade  = null;
    
    private EntryPointJade(){
        
    }
    
    public static DynamicJadeGateway getInstance()
	{			
		if (entryJade == null)
		{ 	entryJade= new DynamicJadeGateway();	
		}
		return entryJade;
	}
    
    
    
}
