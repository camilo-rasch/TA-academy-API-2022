package com.automation.api.tests;

import com.automation.api.steps.BankTransactionService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BankTransactionTest {

    private BankTransactionService transactionSteps;

    @BeforeMethod
    @Parameters({"uri"})
    public void test(String uri){
        this.transactionSteps = new BankTransactionService(uri);
    }

    @Test(description = "Verify if transactions endpoint is empty")
    public void verifyTheEndpointIsEmptyTest(){
        transactionSteps.log.info("Getting all transactions");
        transactionSteps.getTransactions();
        Assert.assertTrue((transactionSteps.getStatusCode() == 200 || transactionSteps.getStatusCode() == 404), "Status is not correct");
        String deletionMessage = transactionSteps.deleteEndpoint();
        transactionSteps.log.info(deletionMessage);
        Assert.assertTrue(deletionMessage.equals("All transactions deleted. Endpoint is empty") || deletionMessage.equals("Endpoint is already empty"), "Endpoint isn't empty");
    }

    @Test(description = "Check that emails are not duplicated")
    public void checkAllEmailsAreNotDuplicated(){
        transactionSteps.log.info("Getting all transactions");
        transactionSteps.getTransactions();
        transactionSteps.log.info("Asserting that emails are not duplicated");
        Assert.assertFalse(transactionSteps.checkIfEmailsAreDuplicated());
    }
}
