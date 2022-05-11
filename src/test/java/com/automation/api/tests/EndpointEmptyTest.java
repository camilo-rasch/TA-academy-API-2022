package com.automation.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EndpointEmptyTest extends BaseTest{

    @Test(groups = {"withDeleteTransactions"})
    public void testEndpointIsEmpty(){
        transactions_steps.getRequest();
        log.info("Status code: " + transactions_steps.getStatusCode());
        Assert.assertEquals(transactions_steps.getStatusCode(), 404,
                "Status code is not correct");
        log.info("Response: " + transactions_steps.getResponseAsString());
        Assert.assertNotNull(transactions_steps.getResponseAsString());
        Assert.assertTrue(transactions_steps.getResponseAsString().contains("Not found"));
    }
}
