package com.automation.api.tests;

import com.automation.api.data.Data;
import com.automation.api.data.DataBT;
import com.automation.api.pojo.BankTransaction;
import com.automation.api.pojo.User;
import com.automation.api.steps.BankTransactions;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Bank transaction test class
 * @author luis.pineda@globant.com
 */
public class BankTransactionsTests {

    private BankTransactions bt_steps;
    private Logger log = Logger.getLogger(BankTransactionsTests.class);

    @BeforeMethod
    @Parameters({"base_url"})
    public void test(String base_url){
        bt_steps = new BankTransactions(base_url);
    }

    @Test(description = "Making post request with POJO data")
    public void postRequestPOJO(){
        bt_steps.getBankTransactionsAPIEndpoint();
        bt_steps.createTransactionPOJO();
        Assert.assertEquals(bt_steps.getStatusCode(), 201, "Status is not correct");
    }

    @Test(description = "Verify that there's no duplicate emails", dependsOnMethods = "postRequestPOJO")
    public void checkNoDuplicateEmails(){
        bt_steps.getBankTransactionsAPIEndpoint();
        bt_steps.getTransactions();
        log.info("Checking if there are duplicate emails");
        Assert.assertEquals(bt_steps.checkDuplicateEmails(bt_steps.getBankTransactionList()), false, "There aren't duplicate emails");
    }

    @Test(description = "Update transaction", dependsOnMethods = "checkNoDuplicateEmails",dataProviderClass = DataBT.class, dataProvider = "bankTransactionUpdate")
    public void putUpdateTransaction(BankTransaction bankTransaction){
        bankTransaction.setEmail("demo@demo.com");
        bankTransaction.setTelephone("555-555-55-55");
        bt_steps.getBankTransactionsAPIEndpoint();
        bt_steps.getTransactions();
        bt_steps.createTransaction();
        bt_steps.getTransactions();
        Assert.assertEquals(bt_steps.getStatusCode(), 200, "Status is not correct");
        String id = bt_steps.getTransactionID(bankTransaction.getAccount_number());
        Assert.assertNotEquals(id, "58892456", "User doesn't exists");
        bt_steps.getBankTransactionsAPIEndpoint();
        bt_steps.updateEmailTelephoneTransaction(id, bankTransaction.getEmail(),bankTransaction.getTelephone());
        Assert.assertEquals(bt_steps.getStatusCode(), 200, "Status is not correct");
        Assert.assertEquals(bt_steps.getBankTransactionResponse().getEmail(),  bankTransaction.getEmail(), "Job title is not correct");
    }

    @Test(description = "Verify if the endpoint is empty, if not, then delete all", dependsOnMethods = "putUpdateTransaction")
    public void verifyEndpointTest(){
        bt_steps.getBankTransactionsAPIEndpoint();
        bt_steps.getTransactions();
        log.info("Checking if the endpoint is empty");
        if(bt_steps.checkEndpoint()){
            log.info("The endpoint is empty");
            Assert.assertEquals(bt_steps.checkEndpoint(),true,"The endpoint is empty");
        }
        else{
            log.info("The endpoint is not empty, then, delete the endpoint");
            Assert.assertEquals(bt_steps.checkEndpoint(),false,"The endpoint is not empty");
            log.info("Deleting transactions");
            bt_steps.deleteAllTransactions(bt_steps.getBankTransactionList());
            log.info("Transaction deleting complete");
            bt_steps.getTransactions();
            Assert.assertEquals(bt_steps.checkEndpoint(),true,"The endpoint is empty");
            log.info("The endpoint is empty");
        }
    }
}
