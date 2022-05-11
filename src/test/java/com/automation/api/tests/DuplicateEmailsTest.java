package com.automation.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DuplicateEmailsTest extends BaseTest{

    @Test(groups = {"WithoutDeleteTransactions"})
    public void testGetRequestWithoutDuplicateEmails(){
        transactions_steps.getRequest();
        log.info("Get request status code: " + transactions_steps.getStatusCode());
        Assert.assertEquals(transactions_steps.getStatusCode(), 200,
                "Status code is not correct");
        log.info("There are duplicate emails: " + transactions_steps.areDuplicateEmails());
        Assert.assertFalse(transactions_steps.areDuplicateEmails(), "There are duplicate emails");
    }
}
