/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudMarket;

import jade.content.AgentAction;
import jade.util.leap.ArrayList;
import jade.util.leap.List;
import java.io.Serializable;



/**
 *
 * @author couli
 */
public class SecureOfferTest implements AgentAction {

    private String name;
    private String id;
    private int price;
    private List offer;

    public SecureOfferTest() {
        offer = new ArrayList();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the offer
     */
    public List getOffer() {
        return offer;
    }

    /**
     * @param offer the offer to set
     */
    public void setOffer(List offer) {
        this.offer = offer;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    public void createOffer(String name, int type) {
        MyService srv = new MyService();
        srv.setName(name);
        srv.setType("" + type);
        offer.add(srv);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
}
