package com.automation.api.tests;

import com.automation.api.pojo.UserTransaction;
import com.automation.api.steps.Users2;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class BankTransactionTest {

    private Users2 transactions_steps;

    @BeforeMethod
    @Parameters({"uri"})
    public void test(String uri){
        transactions_steps = new Users2(uri);
    }

    @Test(description = "Get list of Transactions")
    public void getTransactionsTest(){
        transactions_steps.getAPIEndpoint();
        transactions_steps.showAllTransactions();
        Assert.assertEquals(transactions_steps.getStatusCode(), 200, "Status is not correct");
    }

}
