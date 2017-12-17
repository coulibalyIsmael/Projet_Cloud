/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import jade.wrapper.gateway.GatewayAgent;
import cloudServiceX.*;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
/**
 *
 * @author couli
 */
public class CSCAgenteGateWay extends GatewayAgent{
    
    public CloudServiceConsumer csc = null;
    
   /* protected void processCommand(java.lang.Object obj) {
     if (obj instanceof CloudServiceConsumer)    {
            csc = (CloudServiceConsumer)obj;
            if(csc != null)
            System.out.println("merde");
            //comprobamos que el usuario y contraseña las predefinidas
           
 
            //devolvemos el objeto usuario al servlet con la autentificación
            releaseCommand(csc);
 
        }
     if(obj instanceof Essai)
     {
         System.out.println(((Essai)obj).getLocalName());
     }
    }
*/

    @Override
    protected void setup() {
        super.setup(); //To change body of generated methods, choose Tools | Templates.
    }

    
  

}
    

