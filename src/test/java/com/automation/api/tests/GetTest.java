package com.automation.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GetTest extends BaseTest{

    @Test(groups = {"WithoutDeleteTransactions"})
    public void testGetRequestWithoutDuplicateEmails(){
        transactions_steps.getTransactions();
        Assert.assertEquals(transactions_steps.getStatusCode(), 200,
                "Status code is not correct");
        Assert.assertFalse(transactions_steps.areDuplicateEmails(), "There are duplicate emails");
    }
}
