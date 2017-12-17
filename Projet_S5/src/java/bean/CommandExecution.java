/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import agents.CloudMarketVocabulary;
import static agents.CloudMarketVocabulary.CREATE_CONSUMER;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author couli
 */
public class CommandExecution implements Serializable, CloudMarketVocabulary {

  
    
    
    public HashMap<String, Object> command(String cmd, Object obj)
    {
        HashMap<String, Object> command = new HashMap<>();
        switch (cmd) {
            case CREATE_CONSUMER:
                System.out.println("Create Consumer");
                command.put(cmd, obj);
                return command;
            case CREATE_PROVIDER:
                System.out.println("Create Provider");
                command.put(cmd, obj);
                return command;
               
                
            case CONTACT_PROVIDER:
                command.put(cmd, null);
                return command;
                           
    }
        return null;
        
        
    }

}
