package com.slashqa;
import com.slashqa.context.Context;
import com.slashqa.utils.SessionUtils;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;


public class StepDefinitionsPartTwo extends Context {
    public StepDefinitionsPartTwo(SessionUtils session) {
        super(session);
    }

    @Then("the user verifies that the string stored in the session is {string}")
    public void verifyVariable(String expectedValue){

        if(null!=System.getProperty("prop.one")) {
            System.out.println("The property passed in was: " + System.getProperty("prop.one"));
        }
        assertEquals(expectedValue, getSession().get(SessionUtils.TEST_KEY));
    }
}