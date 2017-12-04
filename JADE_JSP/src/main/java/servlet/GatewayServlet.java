/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.*;
import jade.core.AID;
import jade.core.AID;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.behaviours.SimpleBehaviour;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.gateway.JadeGateway;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import agents.*;

//----------------------------------
import java.io.IOException;


/**
 *
 * @author couli
 */
@WebServlet(name = "GatewayServlet", urlPatterns = {"/GatewayServlet"})
public class GatewayServlet extends HttpServlet {
    
    static final long serialVersionUID = 1L;
    private JadeGateway gateway ;

    //public JadeGateway gateway = null;
    public CloudServiceCustomer csc;
    private int computeLevel;
    private int networkLevel;
    private int storageLevel;
    private String nameCSC;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Test de la classe JadeGateway";
        nameCSC = request.getParameter("consumerName");
        //String[] listServices = request.getParameterValues("service");
        computeLevel = Integer.parseInt(request.getParameter("computeRadio"));
        networkLevel = Integer.parseInt(request.getParameter("networkRadio"));
        storageLevel = Integer.parseInt(request.getParameter("storageRadio"));
        
        System.out.println(storageLevel);

        csc = new CloudServiceCustomer(nameCSC);
        csc.addServices("Compute", computeLevel);
        csc.addServices("Storage", storageLevel);
        csc.addServices("Network", networkLevel);
        try {
            gateway.execute(csc);
        } catch (Exception e) {
            e.printStackTrace();
                    
        }

        response.setContentType("text/html");

        out.print("Cloud Service Consumer: " + csc.name + "<br>");
        for (int i = 0; i < csc.listeProviders.size(); i++) {
            out.print("Provider: " + i + "  " + csc.listeProviders.get(i).getName() + "<br>");
        }

        out.print("<br/><a href='index.html'> Retour</a>");

        out.flush();
        out.close();

    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
     @Override
    public void init() throws ServletException {
        Properties pp = new Properties();
        pp.setProperty(Profile.MAIN_HOST, "localhost");
        pp.setProperty(Profile.MAIN_PORT, "1099");
        JadeGateway.init("agents.MonAgent", pp);
    }  

   

}
