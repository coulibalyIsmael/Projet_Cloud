/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.util.Properties;

/**
 *
 * @author couli
 */
public class JavaApplication3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Properties test = new Properties();
        test.setProperty("aide", "aide");
        test.setProperty("storage", "Storage");
        
        System.out.println(test.getProperty("ddd"));
    }
    
}
