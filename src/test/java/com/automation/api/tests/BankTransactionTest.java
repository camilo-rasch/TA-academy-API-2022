package com.automation.api.tests;

import com.automation.api.data.DataGenerator;
import com.automation.api.pojo.UserTransaction;
import com.automation.api.steps.TransactionsController;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BankTransactionTest {

    private TransactionsController transactions_steps;

    @BeforeMethod
    @Parameters({"uri"})
    public void test(String uri){
        transactions_steps = new TransactionsController(uri);
    }

    @Test(description = "Verify Endpoint is Empty, if not Clean All Data")
    public void checkEmptyEndpoint(){
        transactions_steps.getAPIEndpoint();
        transactions_steps.getTransactions();
        Assert.assertTrue(transactions_steps.checkStatusTransactions(),"Status is wrong");
        transactions_steps.deleteAllTransactions();
        transactions_steps.getTransactions();
        Assert.assertTrue(transactions_steps.checkEmptiness(),"There's some Data");
    }

    @Test(description = "Create Transaction with Random Data",dataProviderClass = DataGenerator.class, dataProvider = "transactions")
    public void createRandomTransaction(UserTransaction transaction){
        transactions_steps.getAPIEndpoint();
        boolean response = transactions_steps.createTransactionVerified(transaction);
        Assert.assertEquals(transactions_steps.getStatusCode(),201,"Status is not correct");
        Assert.assertTrue(response,"Transaction goes with repeated email");
    }

    @Test(description = "Verify if exist Duplicated Emails")
    public void checkEmailsDuplicated(){
        transactions_steps.getAPIEndpoint();
        transactions_steps.getTransactions();
        Assert.assertTrue(transactions_steps.anyDuplicatedEmail(),"There's some Email(s) duplicated");
    }

    @Parameters({"newAccount"})
    @Test(description = "Update an existing Account Number")
    public void updateAccountNumber(int newAccount){
        transactions_steps.getAPIEndpoint();
        transactions_steps.getTransactions();
        String lastId = transactions_steps.getLastId();
        transactions_steps.updateTransaction(lastId,newAccount);
        Assert.assertEquals(transactions_steps.getTransactionById(lastId).getAccountNumber(),newAccount,"Account Updating goes wrong");

    }

}
