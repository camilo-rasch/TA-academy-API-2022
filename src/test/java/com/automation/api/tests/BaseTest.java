package com.automation.api.tests;

import com.automation.api.steps.Transactions;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

    public Logger log = Logger.getLogger(BaseTest.class);
    protected Transactions transactions_steps;

    @BeforeMethod(alwaysRun = true)
    @Parameters({"uri"})
    public void deleteEndpoint(String uri){
        log.info("Uri: " + uri);
        transactions_steps = new Transactions(uri);
        transactions_steps.deleteEndpoint();
        log.info("Deleted endpoint");
    }
}
