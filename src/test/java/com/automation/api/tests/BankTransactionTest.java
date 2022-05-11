package com.automation.api.tests;

import com.automation.api.pojo.BankTransaction;
import com.automation.api.steps.BankTransactionService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;

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
    public void checkAllEmailsAreNotDuplicatedTest(){
        transactionSteps.log.info("Getting all transactions");
        transactionSteps.getTransactions();
        Assert.assertEquals(transactionSteps.getStatusCode(), 200, "Status is not correct");
        transactionSteps.log.info("Asserting that emails are not duplicated");
        Assert.assertFalse(transactionSteps.checkIfEmailsAreDuplicated(), "Endpoint contains duplicated emails");
    }

    @Test(description = "Verify if update request works")
    public void updateAccountNumberTest(){
        transactionSteps.log.info("Generating random id and random account number");
        Random random = new Random();
        int randomId = random.nextInt(10 - 1) + 1;
        String randomAccountNumber = "" + (random.nextInt(99999999 - 11111111) + 11111111);
        transactionSteps.getTransactionById(randomId);
        transactionSteps.log.info("Checking that requested transaction exists");
        Assert.assertEquals(transactionSteps.getStatusCode(), 200, "Status is not correct");
        BankTransaction transactionToUpdate = transactionSteps.getResponseObject();
        transactionToUpdate.setAccountNumber(randomAccountNumber);
        transactionSteps.updateTransaction(randomId, transactionToUpdate);
        transactionSteps.log.info("Verifying update request status code");
        Assert.assertEquals(transactionSteps.getStatusCode(), 200, "Status is not correct");
        transactionSteps.getTransactionById(randomId);
        BankTransaction updatedTransaction = transactionSteps.getResponseObject();
        transactionSteps.log.info("Asserting that updated transaction account number and the previously generated account number match");
        Assert.assertEquals(updatedTransaction.getAccountNumber(), randomAccountNumber, "Account numbers don't match");
    }
}
