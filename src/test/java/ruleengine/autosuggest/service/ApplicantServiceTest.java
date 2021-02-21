package ruleengine.autosuggest.service;


import ruleengine.autosuggest.model.Applicant;
import ruleengine.autosuggest.model.SuggestedRole;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;

/*JUnit To Test Suggested Role for an Applicant based on Rules specified in DRL file*/
public class ApplicantServiceTest {


    private ApplicantService applicantService;

    @Before
    public void setup() {
        applicantService = new ApplicantService();
    }

    @Test
    public void whenCriteriaMatchingThenSuggestManagerRole() throws IOException {
        Applicant applicant = new Applicant("Amrit", 37, 1600000.0, 13);
        SuggestedRole suggestedRole = new SuggestedRole();
        applicantService.suggestARoleForApplicant(applicant, suggestedRole);
        assertEquals("Manager", suggestedRole.getRole());
    }

    @Test
    public void whenCriteriaMatchingThenSuggestSeniorDeveloperRole() throws IOException {
        Applicant applicant = new Applicant("Akash", 37, 1200000.0, 7);
        SuggestedRole suggestedRole = new SuggestedRole();
        applicantService.suggestARoleForApplicant(applicant, suggestedRole);
        assertEquals("Senior developer", suggestedRole.getRole());
    }

    @Test
    public void whenCriteriaMatchingThenSuggestDeveloperRole() throws IOException {
        Applicant applicant = new Applicant("Avin", 37, 800000.0, 3);
        SuggestedRole suggestedRole = new SuggestedRole();
        applicantService.suggestARoleForApplicant(applicant, suggestedRole);
        assertEquals("Developer", suggestedRole.getRole());
    }

    @Test
    public void whenCriteriaNotMatchingThenNoRole() throws IOException {
        Applicant applicant = new Applicant("Naveen", 37, 1200000.0, 5);
        SuggestedRole suggestedRole = new SuggestedRole();
        applicantService.suggestARoleForApplicant(applicant, suggestedRole);
        assertNull(suggestedRole.getRole());
    }
}