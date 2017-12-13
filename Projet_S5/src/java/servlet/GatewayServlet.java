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

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agents.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//----------------------------------
import java.io.*;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.xml.parsers.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

/**
 *
 * @author couli
 */
@WebServlet(name = "GatewayServlet", urlPatterns = {"/GatewayServlet"})
public class GatewayServlet extends HttpServlet {

    static final long serialVersionUID = 1L;
    private JadeGateway gateway;

    //public JadeGateway gateway = null;
    public CloudServiceConsumer csc;
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
        
        
        response.setContentType("text/html");
        ServletContext servletContext = getServletContext();
        System.out.println(servletContext.getRealPath("/WEB-INF") + "ffffffffffffffff");
        

        //System.out.println("valider");
        PrintWriter out = response.getWriter();
        String title = "Test de la classe JadeGateway";
        nameCSC = request.getParameter("consumerName");
        //String[] listServices = request.getParameterValues("service");
        computeLevel = Integer.parseInt(request.getParameter("computeRadio"));
        networkLevel = Integer.parseInt(request.getParameter("networkRadio"));
        storageLevel = Integer.parseInt(request.getParameter("storageRadio"));

        System.out.println(storageLevel);

        csc = new CloudServiceConsumer(nameCSC);
        csc.setName(nameCSC);
        csc.addServices("Compute", computeLevel);
        csc.addServices("Storage", storageLevel);
        csc.addServices("Network", networkLevel);

        if (request.getParameter("valider") != null) {
            try {
                //creation du fichier XML offerConsumer
                new CreateOfferXML().createXmlFile(csc);
               
                //JadeGateway execution S
                
                    //gateway.execute(csc);
                    EntryPointJade.getInstance().execute(csc);
                   
               
            } catch (Exception e) {
                e.printStackTrace();

            }

            

            out.print("Cloud Service Consumer: " + csc.name + "<br>");
            for (int i = 0; i < csc.listeProviders.size(); i++) {
                out.print("Provider: " + i + "  " + csc.listeProviders.get(i).getName() + "<br>");
            }

            out.print("<br/><a href='index.html'> Retour</a>");

            out.flush();
            out.close();

        } 

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

    public void createXmlFile(Document doc) throws Exception {

        //System.out.println("Creatin du fichier offer");
        
        Element root = doc.createElement("offer");
        doc.appendChild(root);

        //ID tag
        Element id = doc.createElement("id");
        root.appendChild(id);
        Text textID = doc.createTextNode("" + hashCode() + new Random().nextInt());
        id.appendChild(textID);

        //Name tag
        Element name = doc.createElement("name");
        root.appendChild(name);
        Text textName = doc.createTextNode(csc.getName());
        name.appendChild(textName);

        for (MyService service : csc.getServices()) {
            Element tag = doc.createElement(service.getName());
            root.appendChild(tag);
            Text text = doc.createTextNode(service.getType());
            tag.appendChild(text);
        }

        //System.out.println("fin de l'écriture dans le fichier----------------------------------------------////*/8898*528");

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter sw = new StringWriter();
        StreamResult result = new StreamResult(sw);
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);
        String xmlString = sw.toString();
         //System.out.println("fichier creeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee**************");
        File file = new File("C:\\Users\\couli\\Documents\\NetBeansProjects\\Projet_S5\\web\\outputFiles\\offer.xml");
        //File file = new File("D:/offer.xml");
        System.out.println(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        bw.write(xmlString);
        bw.flush();
        bw.close();
        
    }

}
