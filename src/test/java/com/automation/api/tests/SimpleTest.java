package com.automation.api.tests;

import com.automation.api.data.Data;
import com.automation.api.pojo.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.automation.api.steps.Users;

import java.util.List;


public class SimpleTest {

    private Users user_steps;

    @BeforeMethod
    @Parameters({"uri"})
    public void test(String uri){
        user_steps = new Users(uri);
    }

    @Test(description = "Empty endpoint")
    public void checkEmptyEndpointTest(){
        List<User> users = user_steps.getUsersList();
        if (users.size() > 0){
            user_steps.deleteAllUsers();
        }
        users = user_steps.getUsersList();
        Assert.assertEquals(users.size(), 0, "The endpoint is not empty");
    }

    @Test(description = "Create users", dataProvider = "users", dataProviderClass = Data.class)
    public void createUsersTest(User user){
        user_steps.createUser(user);
    }

    @Test(description = "check if duplicate emails exist")
    public void duplicateEmailsTest(){
        Assert.assertFalse(user_steps.hasDuplicatedEmails(), "The api has duplicate emails");
    }

    @Test(description = "update an existing AccountNumber")
    @Parameters({"accountNumber"})
    public void putTest(String accountNumber) {
        User user = new User();
        user.setAccountNumber(accountNumber);
        user_steps.getUsersAPIEndpoint();
        user_steps.getUsers();
        Assert.assertEquals(user_steps.getStatusCode(), 200, "Status is not correct");
        String id = user_steps.getUserID(user.getAccountNumber());
        Assert.assertNotEquals(id, "", "User doesn't exists");
        user_steps.getUsersAPIEndpoint();

        String newAccountNumber = "11111";
        user_steps.updateUser(id, newAccountNumber);
        Assert.assertEquals(user_steps.getStatusCode(), 200, "Status is not correct");
        Assert.assertEquals(user_steps.getUserResponse().getAccountNumber(), newAccountNumber, "Account Number is not correct");
    }

}
