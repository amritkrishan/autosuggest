package ruleengine.autosuggest.backward_chaining;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieSession;
import ruleengine.autosuggest.config.DroolsConfigFactory;
import ruleengine.autosuggest.model.Fact;
import ruleengine.autosuggest.model.Result;
import static junit.framework.TestCase.assertEquals;

/*Using Conclusion validate Facts*/
public class BackwardChainingTest {
    private Result result;
    private KieSession ksession;

    @Before
    public void before() {
        result = new Result();
        ksession = new DroolsConfigFactory().getKieSession();
    }

    @Test
    public void whenWallOfChinaIsGivenThenItBelongsToPlanetEarth() {
        ksession.setGlobal("result", result);
        ksession.insert(new Fact("Asia", "Planet Earth"));
        ksession.insert(new Fact("China", "Asia"));
        ksession.insert(new Fact("Great Wall of China", "China"));
        ksession.fireAllRules();
        assertEquals(result.getValue(), "Decision one taken: Great Wall of China BELONGS TO Planet Earth");
    }
}