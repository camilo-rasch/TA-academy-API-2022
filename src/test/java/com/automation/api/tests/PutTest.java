package com.automation.api.tests;

import com.automation.api.data.Data;
import com.automation.api.pojo.Transaction;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PutTest extends BaseTest{

    @Test(groups = {"WithoutDeleteTransactions"}, dataProvider = "accountNumber",
            dataProviderClass = Data.class)
    public void testUpdateAccountNumber(long randomAccountNumber){
        transactions_steps.getRequest();
        Assert.assertEquals(transactions_steps.getStatusCode(), 200,
                "Status code is not correct");
        String idLastTransaction = transactions_steps.getLastId(new Transaction());
        Assert.assertNotNull(idLastTransaction, "There are no transactions");

        transactions_steps.getRequest(idLastTransaction);
        Assert.assertEquals(transactions_steps.getStatusCode(), 200,
                "Status code is not correct");
        Transaction transactionToUpdate = transactions_steps.getObjectResponse();
        transactionToUpdate.setAccountNumber(randomAccountNumber);
        transactions_steps.updateRequest(idLastTransaction, transactionToUpdate);

        Assert.assertEquals(transactions_steps.getObjectResponse().getAccountNumber(),
                transactionToUpdate.getAccountNumber(), "Accounts number was not updated");

    }
}
