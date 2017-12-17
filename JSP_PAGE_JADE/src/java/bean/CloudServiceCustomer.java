/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import jade.core.AID;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author couli
 */
public class CloudServiceCustomer implements Serializable{
    
    public ArrayList<AID> listeProviders = null;
public String name;
public String[] listeChoix;
    public CloudServiceCustomer(String name, String[] services) {
        this.name  = name;
        listeChoix = services;
        listeProviders = new  ArrayList<>();
    }
    
}
