package com.automation.api.tests;

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

    @Test(description = "Verify if the endpoint is empty, if not, then delete all")
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
