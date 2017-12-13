/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import bean.MyService;
import java.util.ArrayList;
import org.w3c.dom.Document;

/**
 *
 * @author couli
 */
public interface CloudServicex {
    
    public void addServices(String service, int level);
    public ArrayList<MyService> getServices();
    public String getName();
    public void setName(String name);
    public void setID(String id);
    public String getID();
    //public void createXmlFile(Document doc);
    
    
}
