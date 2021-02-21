package ruleengine.autosuggest.service;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;
import ruleengine.autosuggest.model.Product;

/*JUnit To Test Label for a Product based on Rules specified in DRL file*/
public class ProductServiceTest {

    private ProductService productService;

    @Before
    public void setup() {
        productService = new ProductService();
    }


    @Test
    public void whenProductTypeElectronicThenLabelBarcode() {
        Product product = new Product("Television", "Electronic");
        product = productService.applyLabelToProduct(product);
        assertEquals("BarCode", product.getLabel());
    }

    @Test
    public void whenProductTypeBookThenLabelIsbn() {
        Product product = new Product("Fictional", "Book");
        product = productService.applyLabelToProduct(product);
        assertEquals("ISBN", product.getLabel());
    }
}