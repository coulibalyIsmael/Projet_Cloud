/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import agents.CloudMarketVocabulary;
import bean.CloudServiceConsumer;
import bean.CommandExecution;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author couli
 */
@WebServlet(name = "ContactProvidersServlet", urlPatterns = {"/ContactProviders"})
public class ContactProvidersServlet extends HttpServlet  implements CloudMarketVocabulary{

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
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        CloudServiceConsumer csc = (CloudServiceConsumer) session.getAttribute("CSCObject");
        System.out.println(csc.getName());
        try {
            System.out.println(csc.getName()+"-*-/-/-/-/------------------/-*");
            JadeGateway.execute(new CommandExecution().command(CONTACT_PROVIDER, csc));
        } catch (Exception e) {
        }
        
        
        
        
        /*try (PrintWriter out = response.getWriter()) {
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet ContactProvidersServlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet ContactProvidersServlet at " + request.getContextPath() + "</h1>");
        out.println("</body>");
        out.println("</html>");
    } */
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
        JadeGateway.init("agents.MonAgent", pp);
    }

}
