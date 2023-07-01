package com.slashqa;
import com.slashqa.context.Context;
import com.slashqa.utils.SessionUtils;

import io.cucumber.java.en.Given;

public class StepDefinitionsPartOne extends Context {
    public StepDefinitionsPartOne(SessionUtils session) {
        super(session);
    }

    @Given("I have {string} stored in my session")
    public void iHaveStringStoredInMySession(String stepValue){
        getSession().put(SessionUtils.TEST_KEY, stepValue);
    }
}


