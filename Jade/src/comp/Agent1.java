/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp;

import jade.core.Agent;

/**
 *
 * @author couli
 */
public class Agent1 extends Agent {
    
    protected void setup(){
        addBehaviour(new Looper(this, 300 ));
        addBehaviour(new Looper(this, 500 ));
    }
    
}
