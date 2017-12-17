/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;
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
import cloudServiceX.*;

/**
 *
 * @author couli
 */
@WebServlet(name = "CSPServlet", urlPatterns = {"/CSCServlet"})
public class CSCServlet extends HttpServlet{
 
    static final long serialVersionUID = 1L;
 
    //la peticiones get son tratadas como las post
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        doPost(request,response);
    }
 
 
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        //obtenemos la acción a realizar
        String nombreAccion = request.getParameter("action");
        System.out.println(nombreAccion);
 
        if (!nombreAccion.equals("valider"))    {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }else{
            //creamos el objeto que enviaremos a JADE
            CloudServiceConsumer csc = new CloudServiceConsumer();
            csc.setName(request.getParameter("nom"));
            
            System.out.println(request.getParameter("service"));
            //request.getParameter("service");
 
            //mandamos el usuario al agente GateWayAgent de JADE
            try    {
                //JadeGateway.execute(csc);
                JadeGateway.execute(new Essai());
            } catch(Exception e) { //e.printStackTrace();
                System.out.println("error");
            }
 
            //creamos la salida del servlet
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
 
            out.print("Cloud service consumer:"+csc.getName()+"<br>");
            
            out.print("Services looking for: "+"--"+"<br>");
 
            out.print("<br/><a href='index.html'> retour </a>");
 
            out.flush();
            out.close();
 
        }
    }
 
    
   
    public void init() throws ServletException {
       Properties pp = new Properties();
       pp.setProperty(Profile.MAIN_HOST, "localhost");
       pp.setProperty(Profile.MAIN_PORT, "1099");
       JadeGateway.init("agents.CSCAgenteGateWay", pp);
       
    }}