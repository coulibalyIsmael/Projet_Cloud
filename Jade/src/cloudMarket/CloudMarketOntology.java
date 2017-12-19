/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudMarket;

import cloudMarket.CloudMarketVocabulary;
import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PrimitiveSchema;

/**
 *
 * @author couli
 */
public class CloudMarketOntology extends Ontology implements CloudMarketVocabulary{
    
    
    // ----------> The name identifying this ontology
   public static final String ONTOLOGY_NAME = "Cloud-market-Ontology";

   // ----------> The singleton instance of this ontology
   private static Ontology instance = new CloudMarketOntology();
   

   // ----------> Method to access the singleton ontology object
   public static Ontology getInstance() { return instance; }
    
    private CloudMarketOntology() {
        super(ONTOLOGY_NAME, BasicOntology.getInstance());
        
        try {
            
            PrimitiveSchema intSchema = (PrimitiveSchema)getSchema(BasicOntology.INTEGER);
            PrimitiveSchema stringSchema = (PrimitiveSchema) getSchema(BasicOntology.STRING);
            
            //mySevice 
            ConceptSchema myServiceSchema = new ConceptSchema(MYSERVICE);
            myServiceSchema.add(MYSERVICE_NAME, stringSchema);
            myServiceSchema.add(MYSERVICE_TYPE,stringSchema);
            add(myServiceSchema, cloudMarket.MyService.class);
            
            
            //Secure Offer
            ConceptSchema secureOfferSchema = new ConceptSchema(SECURE_OFFER);
            secureOfferSchema.add(SECURE_OFFER_ID, stringSchema);
            secureOfferSchema.add(SECURE_OFFER_PRICE, intSchema);
            secureOfferSchema.add(SECURE_OFFER_NAME, stringSchema);
            secureOfferSchema.add(SECURE_OFFER_SERVICES, (ConceptSchema)getSchema(MYSERVICE),1,ObjectSchema.UNLIMITED);
            add(secureOfferSchema, cloudMarket.SecureOfferTest.class);
            
            
            
            
            //Create Provider
            
        } catch (Exception e) {
        }
    }
    
}
