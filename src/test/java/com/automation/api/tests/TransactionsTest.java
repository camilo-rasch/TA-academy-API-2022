package com.automation.api.tests;

import com.automation.api.data.Data;
import com.automation.api.pojo.Transaction;
import com.automation.api.steps.Transactions;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionsTest {

    private Transactions transactions;
    public Logger log = Logger.getLogger(TransactionsTest.class);

    @BeforeTest
    @Parameters({"uri"})
    public void setTest(String uri){
        transactions = new Transactions(uri);
    }


    @Test(description = "Add transactions", dataProviderClass = Data.class, dataProvider = "transactions", priority = 1)
    public void addTransactions(HashMap<String, Transaction> transactionHashMap){
        transactions.getTransactionEndpoint();
        transactions.checkIfDuplicates(new ArrayList<>(transactionHashMap.values()));
        log.info("check for duplicates emails ");
        Assert.assertFalse(transactions.isCheckDuplicates());
        for (Transaction item:transactionHashMap.values()) {
            log.info("Create user with email: "+ item.getEmail());
            transactions.createTransaction(item);
            log.info("Validate the status code");
            Assert.assertEquals(transactions.getStatusCode(), 201, "Status incorrect");
        }
    }

    @Test(description = "Check no duplicates emails", priority = 2)
    public void checkNoDuplicates(){
        transactions.getTransactionEndpoint();
        transactions.getTransactions();
        log.info("Getting all transactions to check duplicates");
        List<Transaction> transactionList = transactions.getAllTransactions();
        log.info("Check the status of code");
        Assert.assertEquals(transactions.getStatusCode(), 200, "Status incorrect");
        transactions.checkIfDuplicates(transactionList);
        log.info("Check duplicates");
        Assert.assertFalse(transactions.isCheckDuplicates());
    }

    @Test(description = "Update an account number",priority = 3)
    public void updateAccountNumber(){
        transactions.getTransactions();
        log.info("Check the status of code");
        Assert.assertEquals(transactions.getStatusCode(), 200, "Status incorrect");
        List<Transaction> transactionList = transactions.getAllTransactions();
        log.info("Getting transactions to update one transaction");
        String fakeNewString = transactions.fakeNumber();
        log.info("Update one of the transactions");
        transactions.updateTransaction(transactionList.get(0).getId(),fakeNewString);
        log.info("Check status code of the update operation");
        Assert.assertEquals(transactions.getStatusCode(), 200, "Status is not correct");
        log.info("Validate new account number");
        Assert.assertEquals(transactions.getTransactionResponse().getAccountNumber(),  fakeNewString, "Account Number is not correct");
    }

    @Test(description = "Verify endpoint is empty", priority = 4)
    public void verifyEndPointIsEmpty(){
        transactions.getTransactionEndpoint();
        transactions.getTransactions();
        log.info("Validate if the endpoint is empty, if not delete transactions");
        if(transactions.getStatusCode() != 404){
            log.info("Delete transactions");
            transactions.deleteAllTransactions();
        }
        transactions.getTransactions();
        log.info("Validate that the endpoint is empty with the status code");
        Assert.assertEquals(transactions.getStatusCode(), 404, "Status is not correct");
        log.info("second validation enpoint is empyt");
        Assert.assertEquals(transactions.toString(), "\"Not found\"");
    }
}
