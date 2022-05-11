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
        Assert.assertEquals(transactions_steps.getStatusCode(), 201, "Transaction can not " +
                "be created");
        transactions_steps.getRequest();
        Assert.assertEquals(transactions_steps.getStatusCode(), 200,
                "Status code is not correct");
        Assert.assertEquals(transactions_steps.actualTransactionsList().size(), 10,
                "Left transactions to create");
        Assert.assertFalse(transactions_steps.areDuplicateEmails());
    }
}
