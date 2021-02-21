package ruleengine.autosuggest.service;

import ruleengine.autosuggest.config.DroolsConfigFactory;
import ruleengine.autosuggest.model.Product;
import org.kie.api.runtime.KieSession;

public class ProductService {

    private KieSession kieSession=new DroolsConfigFactory().getKieSession();

    public Product applyLabelToProduct(Product product){
        kieSession.insert(product);
        kieSession.fireAllRules();
        System.out.println(product.getLabel());
        return  product;

    }

}