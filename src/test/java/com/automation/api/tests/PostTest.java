package com.automation.api.tests;

import com.automation.api.data.Data;
import com.automation.api.pojo.Transaction;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PostTest extends BaseTest{

    @Test(dataProvider = "transactions", dataProviderClass = Data.class,
            groups = {"withDeleteTransactions"})
    public void testPostRequest(List<Transaction> transactions){
        transactions_steps.postRequest(transactions);
        log.info("Post request status code: " + transactions_steps.getStatusCode());
        Assert.assertEquals(transactions_steps.getStatusCode(), 201, "Transaction can not " +
                "be created");
        transactions_steps.getRequest();
        log.info("Get request status code: " + transactions_steps.getStatusCode());
        Assert.assertEquals(transactions_steps.getStatusCode(), 200,
                "Status code is not correct");
        log.info("Amount of registers created: " + transactions_steps.actualTransactionsList().size());
        Assert.assertEquals(transactions_steps.actualTransactionsList().size(), 10,
                "Left transactions to create");
        log.info("There are duplicate emails: " + transactions_steps.areDuplicateEmails());
        Assert.assertFalse(transactions_steps.areDuplicateEmails());
    }
}
