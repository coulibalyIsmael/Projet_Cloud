/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;

/**
 *
 * @author couli
 */
public class Looper extends SimpleBehaviour {
    static String offset="";
    static long t0 = System.currentTimeMillis();
    String tab = "";
    int n = 1;
    long dt;
    public Looper(Agent agent, long dt){
        super(agent);
        this.dt = dt;
        offset +="   ";
        tab = new String(offset);
    }

    @Override
    public void action() {
       System.out.println(tab + (System.currentTimeMillis()-t0)/10*10 + "; "+ myAgent.getLocalName());
        block(dt);
        n++;
    }

    @Override
    public boolean done() {
        return n > 6;
    }
    
}
