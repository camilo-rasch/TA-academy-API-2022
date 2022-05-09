package com.automation.api.tests;

import com.automation.api.data.RandomData;
import com.automation.api.pojo.BankUser;
import com.automation.api.steps.BankAPIBase;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import java.util.HashSet;
import java.util.List;

public class BankTest {

    private BankAPIBase base;

    private final Logger logger = Logger.getLogger(BankTest.class);

    @BeforeTest
    @Parameters({"uri"})
    public void fillingDataBaseWithRandomUsers(String uri) {
        //Adding 10 new random users to the database
        RandomData randomData = new RandomData();
        base = new BankAPIBase(uri);
        base.getBankUsersEndpoint();
        for (int i = 0; i < 10; i++) {
            BankUser user = randomData.RandomBankUser();
            base.createNewBankUser(user);
            assertEquals(base.getStatusCode(), 201, "Status code is not correct");
            logger.info("random user Nº " + i + " " + user.toString());
        }
    }

    @BeforeMethod
    @Parameters({"uri"})
    public void test(String uri){
        base = new BankAPIBase(uri);
        base.getBankUsersEndpoint();
    }

    @Test(description = "Verify that endpoint is empty, otherwise clear it", priority = 1)
    public void cleanBankUsers() {
        logger.info("Retrieving all bank users");
        List<BankUser> userList = base.getAllBankUsers();
        boolean statusCode = (base.getStatusCode() == 200) || (base.getStatusCode() == 404);
        assertTrue(statusCode);
        if (base.getStatusCode() == 200){
            logger.info("Deleting all users in the database");
            base.deleteAllBankUsers(userList);
            assertEquals(base.getStatusCode(), 200, "Status code is not correct");
        } else if (base.getStatusCode() == 404) {
            assertEquals(base.getStatusCode(), 404, "Status code is not correct");
            logger.info("The database was already empty");
        }
    }

    @Test(description = "check that there are not repeated emails then post a new user",  dataProviderClass = RandomData.class, dataProvider = "randomBankUserDataProvider")
    public void createNewUser(BankUser user) {
        logger.info("Retrieving all bank user emails");
        List<String> emailList = base.getAllBankUsersEmail();
        assertEquals(base.getStatusCode(), 200, "Status code is not correct");
        if (emailList.contains(user.getEmail())){
            logger.warn("*****Email already in the database*****");
        }
        else {
            logger.info("Creating new user");
            base.createNewBankUser(user);
            assertEquals(base.getStatusCode(), 201, "Status code is not correct");
            base.getLatestBankUserRegistered();
            assertEquals(base.getStatusCode(), 200, "Status code is not correct");
        }
    }

    @Test(description = "Checking that there are not repeated emails")
    public void checkingForRepeatedEmails() {
        logger.info("Retrieving all bank user emails");
        List<String> emailList = base.getAllBankUsersEmail();
        logger.info("Checking that there are not repeated emails in the database");
        assertTrue(emailList.stream().allMatch(new HashSet<>()::add));
    }

    @Test(description = "Update account of existing user")
    @Parameters({"id", "account number"})
    public void updateUserAccountNumber(long id, String accountNumber) {
        logger.info("Retrieving user information");
        BankUser user = base.getBankUserById(id);
        assertEquals(base.getStatusCode(), 200, "Status code is not correct");
        logger.info("Update user information account number");
        base.updateBankUser(user, accountNumber);
        assertEquals(base.getStatusCode(), 200, "Status code is not correct");
        base.getBankUserById(id);
    }
}
