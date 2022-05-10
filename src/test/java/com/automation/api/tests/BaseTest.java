package com.automation.api.tests;

import com.automation.api.steps.Transactions;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {

    public Logger log = Logger.getLogger(BaseTest.class);
    protected Transactions transactions_steps;

    @BeforeClass(alwaysRun = true)
    @Parameters({"uri"})
    public void setUp(String uri){
        log.info("Uri: " + uri);
        transactions_steps = new Transactions(uri);

    }

    @BeforeMethod(groups = {"withDeleteTransactions"})
    public void deleteEndpoint(){
        transactions_steps.deleteEndpoint();
        log.info("Deleted endpoint");
    }
}
