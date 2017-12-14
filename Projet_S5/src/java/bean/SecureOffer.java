/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author couli
 */
public class SecureOffer implements Serializable{
    
    private int price;
    private ArrayList<MyService> offer;
    
    public SecureOffer()
    {
        offer = new ArrayList<>();
    }
    
    public void setPrice(int price)
    {
        this.price = price;
    }
    
    
    public int getPrice()
    {
        return this.price;
    }
    
    public void addServices(String name, String type)
    {
        MyService service = new MyService();
        service.setName(name);
        service.setType(type);
        this.offer.add(service);
    }
    
    
    public ArrayList<MyService> getServices()
    {
        return this.offer;
    }
    
   
    
    
    
}
