package com.automation.api.tests;

import com.automation.api.steps.Transactions;
import com.automation.api.steps.Users;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TransactionTest
{
    private static String STATUS_MSG = "Status is not correct";
    private Transactions transactions_steps;

    @BeforeMethod
    @Parameters({"uri"})
    public void test(String uri){
        this.transactions_steps = new Transactions(uri);
    }

    @Test(description = "Get list of users", enabled = false)
    public void getTransactionTest(){
        transactions_steps.getTransactionApiEndpoint();
        transactions_steps.getTransactions();
        Assert.assertEquals(transactions_steps.getStatusCode(), 200, STATUS_MSG);
        transactions_steps.showActualTransactionList();
    }

    @Test(description = "Verify that there is no transactions here")
    public void verifyEndpointIsEmpty()
    {
        transactions_steps.getTransactionApiEndpoint();
        transactions_steps.getTransactions();

        boolean isEmpty = transactions_steps.isTransactionsEmpty();

            while (!isEmpty) {
                Assert.assertEquals(transactions_steps.getStatusCode(), 200, STATUS_MSG);
                String id = transactions_steps.getLastId();
                System.out.println("Deleting transaction with id : " + id);
                transactions_steps.deleteTransaction(id);
                Assert.assertEquals(transactions_steps.getStatusCode(), 200, STATUS_MSG);
                transactions_steps.getTransactions();
                isEmpty = transactions_steps.isTransactionsEmpty();
            }

        Assert.assertTrue(isEmpty, "Is not empty");
    }
}
