/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.*;
import jade.core.Profile;
import jade.util.leap.Properties;
import jade.wrapper.gateway.JadeGateway;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author couli
 */
@WebServlet(name = "ProviderGatewayServlet", urlPatterns = {"/ProviderGatewayServlet"})
public class ProviderGatewayServlet extends HttpServlet {

   private String nameCSP;
   private int computeLevel, networkLevel, storageLevel;
   private JadeGateway gateway;
   public CloudServiceProvider csp;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String title = "Test de la classe JadeGateway";
        nameCSP = request.getParameter("providerName");
        //String[] listServices = request.getParameterValues("service");
        computeLevel = Integer.parseInt(request.getParameter("computeRadio"));
        networkLevel = Integer.parseInt(request.getParameter("networkRadio"));
        storageLevel = Integer.parseInt(request.getParameter("storageRadio"));
        
        System.out.println(storageLevel);

        csp = new CloudServiceProvider();
        csp.setName(nameCSP);
        System.out.println("/**********************************"+csp.getName()+"*******************************");
        csp.addServices("Compute", computeLevel);
        csp.addServices("Storage", storageLevel);
        csp.addServices("Network", networkLevel);
        try {
            if(!gateway.isGatewayActive())
                gateway.execute(csp);
            else
                System.out.println(gateway.isGatewayActive());
        } catch (Exception e) {
            e.printStackTrace();
                    
        }
        

       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        JadeGateway.init("agents.ProviderGateway", pp);
    }  
}
