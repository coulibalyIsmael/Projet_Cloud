/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ontology;

import agents.CloudMarketVocabulary;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.schema.ConceptSchema;

/**
 *
 * @author couli
 */
public class CloudMarketOntology extends Ontology implements CloudMarketVocabulary{
    
    
    // ----------> The name identifying this ontology
   public static final String ONTOLOGY_NAME = "Bank-Ontology";

   // ----------> The singleton instance of this ontology
   private static Ontology instance = new CloudMarketOntology();
   

   // ----------> Method to access the singleton ontology object
   public static Ontology getInstance() { return instance; }
    
    private CloudMarketOntology() {
        super(ONTOLOGY_NAME, BasicOntology.getInstance());
        
        try {
            //Secure Offer
            ConceptSchema cs = new ConceptSchema(SECURE_OFFER);
            add(cs, bean.SecureOfferTest.class);
            
            
            
            //Create Provider
            
        } catch (Exception e) {
        }
    }
    
}
