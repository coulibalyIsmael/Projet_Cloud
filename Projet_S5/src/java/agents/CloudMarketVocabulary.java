/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import java.util.HashMap;

/**
 *
 * @author couli
 */
public interface CloudMarketVocabulary {
    public final String CREATE_CONSUMER="CREATE_CONSUMER";
    public final String CREATE_PROVIDER="CREATE_PROVIDER";
    public final String CONTACT_PROVIDER="CONTACT_PROVIDER";
    public final String ACK_MESSAGE="ACK";
    public final String NEED_COMPUTATION="NEED_COMPUTATION";
    
    //Securee offer class
    public final String SECURE_OFFER="SECURE_OFFER";
    public final String SECURE_OFFER_PRICE="price";
    public final String SECURE_OFFER_SERVICES="offer";
    
    //MyService class
    public final String MyService="myService";
    public final String MyService_NAME="name";
    public final String MyService_TYPE="type";
    
    
}
