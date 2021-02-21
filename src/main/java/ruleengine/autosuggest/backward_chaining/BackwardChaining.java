package ruleengine.autosuggest.backward_chaining;

import org.kie.api.runtime.KieSession;
import ruleengine.autosuggest.config.DroolsConfigFactory;
import ruleengine.autosuggest.model.Fact;
import ruleengine.autosuggest.model.Result;

public class BackwardChaining {

    public static void main(String[] args) {
        Result result = new BackwardChaining().backwardChaining();
        System.out.println(result.getValue());
        result.getFacts()
                .stream()
                .forEach(System.out::println);
    }

    public Result backwardChaining() {
        Result result = new Result();
        KieSession ksession = new DroolsConfigFactory().getKieSession();
        ksession.setGlobal("result", result);
        ksession.insert(new Fact("Asia", "Planet Earth"));
        ksession.insert(new Fact("China", "Asia"));
        ksession.insert(new Fact("Great Wall of China", "China"));
        ksession.fireAllRules();
        return result;
    }
}