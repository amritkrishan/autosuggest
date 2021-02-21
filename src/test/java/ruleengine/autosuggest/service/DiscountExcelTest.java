package ruleengine.autosuggest.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import ruleengine.autosuggest.config.DroolsConfigFactory;
import ruleengine.autosuggest.model.Customer;

/*JUnit To Test Discount for a Customer based on Rules specified in Excel file*/
public class DiscountExcelTest {

    private KieSession kSession;

    @Before
    public void setup() {
        Resource resource = ResourceFactory.newClassPathResource("ruleengine/autosuggest/rules/Discount.xls", getClass());
        kSession = new DroolsConfigFactory().getKieSession(resource);
    }

    @Test
    public void giveIndvidualLongStandingWhenFireRuleThenCorrectDiscount() throws Exception {
        Customer customer = new Customer(Customer.CustomerType.INDIVIDUAL, 5);
        kSession.insert(customer);
        kSession.fireAllRules();
        assertEquals(customer.getDiscount(), 15);
    }

    @Test
    public void giveIndvidualRecentWhenFireRuleThenCorrectDiscount() throws Exception {
        Customer customer = new Customer(Customer.CustomerType.INDIVIDUAL, 1);
        kSession.insert(customer);
        kSession.fireAllRules();
        assertEquals(customer.getDiscount(), 5);
    }

    @Test
    public void giveBusinessAnyWhenFireRuleThenCorrectDiscount() throws Exception {
        Customer customer = new Customer(Customer.CustomerType.BUSINESS, 0);
        kSession.insert(customer);
        kSession.fireAllRules();
        assertEquals(customer.getDiscount(), 20);
    }

}