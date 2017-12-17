/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import agents.CloudMarketVocabulary;
import bean.*;
import jade.core.AID;
import jade.core.Profile;
import jade.util.leap.Properties;
import jade.wrapper.gateway.JadeGateway;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author couli
 */
@WebServlet(name = "ProviderGatewayServlet", urlPatterns = {"/ProviderGatewayServlet"})
public class ProviderGatewayServlet extends HttpServlet implements CloudMarketVocabulary{

   private String nameCSP;
   private int computeLevel, networkLevel, storageLevel;
           private String secureComputeLevel, secureNetworkLevel, secureStorageLevel;
   private JadeGateway gateway;
   public CloudServiceProvider csp;
   private String pathFile;
   
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       ServletContext servletContext = getServletContext();
        String title = "Test de la classe JadeGateway";
        nameCSP = request.getParameter("providerName");
        //String[] listServices = request.getParameterValues("service");
        //service level
        computeLevel = Integer.parseInt(request.getParameter("computeRadio"));
        networkLevel = Integer.parseInt(request.getParameter("networkRadio"));
        storageLevel = Integer.parseInt(request.getParameter("storageRadio"));
        
        //security level
        secureComputeLevel =request.getParameter("computeSecureRadio");
        secureStorageLevel =request.getParameter("storageSecureRadio");
        secureNetworkLevel = request.getParameter("networkSecureRadio");
        
        
        
        System.out.println(storageLevel);

        csp = new CloudServiceProvider();
        csp.setName(nameCSP);
        System.out.println("/**********************************"+csp.getName()+"*******************************");
        csp.addServices("Compute", computeLevel);
        csp.addServices("Storage", storageLevel);
        csp.addServices("Network", networkLevel);
        
        //secure offer
        csp.getSecureOffer().addServices("secureCompute", secureComputeLevel);
        csp.getSecureOffer().addServices("secureNetwork", secureNetworkLevel);
        csp.getSecureOffer().addServices("secureStorage", secureStorageLevel);
        csp.getSecureOffer().setPrice(Integer.parseInt(request.getParameter("price")));
        
        //ID du provider
        csp.setID(""+new Random().nextInt(Integer.MAX_VALUE));
        try {
            pathFile = servletContext.getRealPath("/WEB-INF/outputXML")+"\\secureOfferClient.xml";
            new CreateSecureOfferXML().createXmlFile(csp,pathFile);
            new CreateOfferXML().createXmlFile(csp, pathFile);
                
                gateway.execute(new CommandExecution().command(CREATE_PROVIDER, csp));
            
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
        JadeGateway.init("agents.MonAgent", pp);
    }  
    
    
    
    public void createXmlFile(Document doc, ServletContext servletContext) throws Exception {

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
        Text textName = doc.createTextNode(csp.getName());
        name.appendChild(textName);

        for (MyService service : csp.getServices()) {
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
       
        

        //return document;
        //XMLWriter writer = new XMLWriter(new FileWriter(this.getName()+"comsumer_offer.xml"));
        /*} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
