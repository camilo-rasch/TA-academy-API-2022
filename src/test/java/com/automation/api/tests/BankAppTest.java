package com.automation.api.tests;

import com.automation.api.data.Data;
import com.automation.api.pojo.BankUser;
import com.automation.api.steps.BankUsers;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class BankAppTest {

    private BankUsers bank_user_steps;
    public Logger log = Logger.getLogger(BankAppTest.class);

    @BeforeMethod
    @Parameters({"uri"})
    public void test(String uri){
        bank_user_steps = new BankUsers(uri);
    }

    @Test (description = "Check if endpoint is empty", priority = 1)
    public void checkEndpoint() {
        bank_user_steps.getResponseAPIEndpoint();
        List<BankUser> bankUsers = bank_user_steps.getAllBankUsers();
        if (bank_user_steps.getStatusCode() == 404) {
            log.info("Endpoint is empty");
            Assert.assertEquals(bank_user_steps.getStatusCode(), 404, "Status is not correct");
        } else {
            log.info("Endpoint is not empty");
            log.info("Deleting data");
            bank_user_steps.deleteAllUsers(bankUsers);
        }
    }

    @Test (description = "Create a POST request", dataProviderClass = Data.class, dataProvider = "randomData", priority = 2)
    public void makePOSTRequest(HashMap<String, BankUser> userHashMap) {
        bank_user_steps.getResponseAPIEndpoint();
        for (BankUser user:userHashMap.values()) {
            log.info("Create user with email: " + user.getEmail());
            bank_user_steps.createUser(user);
            log.info("Validate the status code");
            Assert.assertEquals(bank_user_steps.getStatusCode(), 201, "Status incorrect");
        }
    }

    @Test (description = "Validate duplicated emails", priority = 3)
    public void checkEmails() {
        log.info("Get users email list");
        List<String> bankUsersEmail = bank_user_steps.getAllBankUsersEmail();
        log.info("Validate duplicated emails");
        Assert.assertTrue(bankUsersEmail.stream().allMatch(new HashSet<>()::add));
    }

    @Test (description = "Update existing account number", priority = 4)
    public void updateAccount() {
        log.info("Get the users list");
        List<BankUser> users = bank_user_steps.getAllBankUsers();
        log.info("Get new account number to update");
        String newRandomNumber = bank_user_steps.randomNumberGenerator();
        log.info("Updating account number");
        bank_user_steps.updateUser(users.get(0).getId(), newRandomNumber);
        Assert.assertEquals(bank_user_steps.getStatusCode(), 200, "Status is not correct");
    }
}
