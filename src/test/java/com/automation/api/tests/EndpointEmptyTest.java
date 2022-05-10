package com.automation.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EndpointEmptyTest extends BaseTest{

    @Test
    public void testEndpointIsEmpty(){
        transactions_steps.getTransactions();
        Assert.assertEquals(transactions_steps.getStatusCode(), 404,
                "Status code is not correct");
        Assert.assertNotNull(transactions_steps.getEndpointEmptyMessage());
    }
}
