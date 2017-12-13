/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author couli
 */
@WebServlet(name = "GenerateXMLFile", urlPatterns = {"/GenerateXMLFile"})
public class GenerateXMLFile extends HttpServlet {

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
        response.setContentType("text/xml");
        String reportName =  "GenerateXML_Report_"
                +System.currentTimeMillis()+".xml";     
        response.setHeader("Content-disposition", "attachment; " +
                "filename=" + reportName);   

        ArrayList<String> rows = new ArrayList<String>();
        rows.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        rows.add("<Details>");       
        for (int i = 0; i < 4; i++) {
            int j = i+1;
            rows.add("<Person"+(i+1)+">");
            rows.add("<FirstName>");
            rows.add("Java");
            rows.add("</FirstName>");
            rows.add("<LastName>");
            rows.add("Honk");
            rows.add("</LastName>");
            rows.add("<Test>");
            rows.add("Success");
            rows.add("</Test>");
            rows.add("</Person"+(i+1)+">");

        }
        rows.add("</Details>"); 

        Iterator<String> iter = rows.iterator();
        while (iter.hasNext()){
            String outputString = (String) iter.next();
            response.getOutputStream().println(outputString);
        }

        response.getOutputStream().flush();
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

}
