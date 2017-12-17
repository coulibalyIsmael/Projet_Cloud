package agents;

import users.Usuario;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;
import jade.wrapper.gateway.*;
 
 
import jade.util.leap.Properties;
 
public class MiAgenteGateWay extends GatewayAgent {
 
    Usuario usuario = null;
 
 
 
    protected void processCommand(java.lang.Object obj) {
 
        //comprobamos que el objeto recibido sea un usuario
        if (obj instanceof Usuario)    {
            usuario = (Usuario)obj;
            System.out.println(usuario.getPass());
            //comprobamos que el usuario y contraseña las predefinidas
            if (usuario.getUsuario().equals("root") && usuario.getPass().equals("root")){
                usuario.setMensaje(" Válido ");
            }else{
                usuario.setMensaje(" Pas valide ");
            }
 
            //devolvemos el objeto usuario al servlet con la autentificación
            releaseCommand(usuario);
 
        }
 
    }
 
    public void setup()
    {
        super.setup();
    }
 
}