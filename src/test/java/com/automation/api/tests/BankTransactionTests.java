package com.automation.api.tests;

import com.automation.api.data.DataProv;
import com.automation.api.pojo.BankTransaction;
import com.automation.api.steps.Transactions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BankTransactionTests {

    private Transactions transactionsSteps;

    @BeforeMethod
    @Parameters({"uri"})
    public void test(String uri) {
        transactionsSteps = new Transactions(uri);
    }

    /**
     * Verify the endpoint is empty
     */
    @Test(description = "Verify Endpoint is Empty")
    public void endpointEmptyTest() {
        transactionsSteps.getTransactionsAPIEndpoint();
        transactionsSteps.getTransactions();
        transactionsSteps.showActualTransactionsList();
        transactionsSteps.cleanTransactionsEndpoint();
        transactionsSteps.getTransactions();
        Assert.assertEquals(transactionsSteps.getStatusCode(), 404, "Status is not correct");
        transactionsSteps.showActualTransactionsList();
    }

    /**
     * Initialize 10 random data using DataProvider and util.RandomData class
     *
     * @param bankTransaction object from DataProvider
     */
    @Test(description = "Initialize 10 random data", dataProviderClass = DataProv.class, dataProvider = "dataInfo")
    public void initialize10RandomDataTest(BankTransaction bankTransaction) {
        transactionsSteps.getTransactionsAPIEndpoint();
        transactionsSteps.createTransaction(bankTransaction);
        Assert.assertEquals(transactionsSteps.getStatusCode(), 201, "Status is not correct");
    }

    /**
     * Make GET Request, asserting there are not duplicate emails
     */
    @Test(description = "Get request, checking not duplicate email accounts")
    public void getRequestNoDuplicateEmailTest() {
        transactionsSteps.getTransactionsAPIEndpoint();
        transactionsSteps.getTransactions();
        Assert.assertFalse(transactionsSteps.checkDuplicateEmails(), "Duplicated email found");
    }

    /**
     * Update an existing transaction (the last one) and change its accountNumber (to "7777777")
     */
    @Test(description = "Update Existing AccountNumber")
    public void updateExistingAccountNumberTest() {
        transactionsSteps.getTransactionsAPIEndpoint();
        transactionsSteps.getTransactions();
        Assert.assertEquals(transactionsSteps.getStatusCode(), 200, "Status is not correct");
        transactionsSteps.showActualTransactionsList();
        BankTransaction lastBT = transactionsSteps.getLastTransaction();
        String newAccountNumber = "777777";
        transactionsSteps.updateTransaction(lastBT, newAccountNumber);
        Assert.assertEquals(transactionsSteps.getStatusCode(), 200, "Status is not correct");


    }

}
