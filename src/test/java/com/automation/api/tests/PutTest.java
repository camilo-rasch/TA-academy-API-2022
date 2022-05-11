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
        log.info("Get request status code: " + transactions_steps.getStatusCode());
        Assert.assertEquals(transactions_steps.getStatusCode(), 200,
                "Status code is not correct");

        String idLastTransaction = transactions_steps.getLastId();
        log.info("Id of transaction to update: " + idLastTransaction);
        Assert.assertNotNull(idLastTransaction, "There are no transactions");
        transactions_steps.getRequest(idLastTransaction);
        log.info("Get request by id status code: " + transactions_steps.getStatusCode());
        Assert.assertEquals(transactions_steps.getStatusCode(), 200,
                "Status code is not correct");

        Transaction transactionToUpdate = transactions_steps.getObjectResponse();
        log.info("Transaction to update: " + transactionToUpdate.toString());
        log.info("Actual account number: " + transactionToUpdate.getAccountNumber());
        transactionToUpdate.setAccountNumber(randomAccountNumber);
        log.info("New account number: " + randomAccountNumber);
        transactions_steps.updateRequest(idLastTransaction, transactionToUpdate);
        log.info("Put request status code: " + transactions_steps.getStatusCode());

        Assert.assertEquals(transactions_steps.getObjectResponse().getAccountNumber(),
                transactionToUpdate.getAccountNumber(), "Accounts number was not updated");
    }
}
