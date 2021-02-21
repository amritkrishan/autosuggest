package ruleengine.autosuggest.service;

import org.kie.api.runtime.KieSession;
import ruleengine.autosuggest.config.DroolsConfigFactory;
import ruleengine.autosuggest.model.Applicant;
import ruleengine.autosuggest.model.SuggestedRole;
import java.io.IOException;

public class ApplicantService {

    KieSession kieSession=new DroolsConfigFactory().getKieSession();

    public SuggestedRole suggestARoleForApplicant(Applicant applicant, SuggestedRole suggestedRole) throws IOException {
        kieSession.insert(applicant);
        kieSession.setGlobal("suggestedRole",suggestedRole);
        kieSession.fireAllRules();
        System.out.println(suggestedRole.getRole());
        return  suggestedRole;

    }
}