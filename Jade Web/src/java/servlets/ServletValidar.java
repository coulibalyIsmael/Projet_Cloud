/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;


/**
 *
 * @author couli
 */
import agents.*;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.gateway.JadeGateway;
 
import java.io.IOException;
import java.io.PrintWriter;
import jade.util.leap.Properties;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import users.Usuario;
 
@WebServlet(name = "ServletValidar", urlPatterns = {"/ServletValidar"})
public class ServletValidar extends HttpServlet{
 
    static final long serialVersionUID = 1L;
 
    //la peticiones get son tratadas como las post
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        doPost(request,response);
    }
 
 
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        //obtenemos la acción a realizar
        String nombreAccion = request.getParameter("accion");
        System.out.println(nombreAccion);
 
        if (!nombreAccion.equals("validar"))    {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }else{
            //creamos el objeto que enviaremos a JADE
            Usuario usuario = new Usuario();
            usuario.setUsuario(request.getParameter("nombre"));
            usuario.setPass(request.getParameter("pass"));
           users.Teste tester = new users.Teste();

 
            //mandamos el usuario al agente GateWayAgent de JADE
            try    {
         
                //JadeGateway.execute(usuario);
                JadeGateway.execute(tester);
            } catch(Exception e) { e.printStackTrace();
                //System.out.println("error");
            }
 
            //creamos la salida del servlet
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
 
            out.print("El usuario es:"+usuario.getUsuario()+"<br>");
 
            out.print("Contestación:    "+usuario.getMensaje()+"<br>");
 
            out.print("<br/><a href='index.html'> Volver a validar </a>");
 
            out.flush();
            out.close();
 
        }
    }
 
    //método que se invoca al cargarse el servlet en el contenedor de servlets
    
   
    public void init() throws ServletException {
        //Establecemos qué clase será la GateGayAgent
       Properties pp = new Properties();
       pp.setProperty(Profile.MAIN_HOST, "localhost");
       pp.setProperty(Profile.MAIN_PORT, "1099");
       JadeGateway.init("agents.MiAgenteGateWay", pp);
       
    }
}
 