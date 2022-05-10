package com.automation.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EndpointEmptyTest extends BaseTest{

    @Test
    public void testEndpointIsEmpty(){
        transactions_steps.getTransactions();
        Assert.assertEquals(transactions_steps.getStatusCode(), 200,
                "Status code is not correct");
        Assert.assertEquals(transactions_steps.amountOfTransactionsInEndpoint(), 0 ,
                "There are at lest one transaction in endpoint");
    }
}
