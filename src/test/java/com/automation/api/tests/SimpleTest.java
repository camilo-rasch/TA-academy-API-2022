package com.automation.api.tests;

import com.automation.api.data.Data;
import com.automation.api.pojo.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.automation.api.steps.Users;


public class SimpleTest {

    private Users user_steps;

    @BeforeMethod
    @Parameters({"uri"})
    public void test(String uri){
        user_steps = new Users(uri);
    }

    @Test(description = "Get list of users")
    public void getUsersTest(){
        user_steps.getApiEndpoint();
        user_steps.getObjects();
        Assert.assertEquals(user_steps.getStatusCode(), 200, "Status is not correct");
        user_steps.showActualObjectList();
    }

    @Test(description = "Get a user")
    @Parameters({"name", "email"})
    public void getUserTest(String name, String email){
        user_steps.getApiEndpoint();
        user_steps.getObjects();
        Assert.assertEquals(user_steps.getStatusCode(), 200, "Status is not correct");
        String id = user_steps.getUserID(name);
        Assert.assertNotEquals(id, "", "User doesn't exists");
        user_steps.getObject(id);
        Assert.assertEquals(user_steps.getStatusCode(), 200, "Status is not correct");
        User user = user_steps.getObjectResponse();
        Assert.assertEquals(user.getEmail(), email, "Email is not correct");
    }

    @Test(description = "Create a new user post", dataProviderClass = Data.class, dataProvider = "users")
    public void postUsersTest(User user){
        user_steps.getApiEndpoint();
        user_steps.createObject(user);
        Assert.assertEquals(user_steps.getStatusCode(), 201, "Status is not correct");
    }

    @Test(description = "Update user", dataProviderClass = Data.class, dataProvider = "users")
    public void putTest(User user){
        user.setJobTitle("Mesero");
        user_steps.getApiEndpoint();
        user_steps.getObjects();
        Assert.assertEquals(user_steps.getStatusCode(), 200, "Status is not correct");
        String id = user_steps.getUserID(user.getFirst_name() + " " + user.getLast_name());
        Assert.assertNotEquals(id, "", "User doesn't exists");
        user_steps.getApiEndpoint();
        user_steps.updateUser(id, user.getJobTitle());
        Assert.assertEquals(user_steps.getStatusCode(), 200, "Status is not correct");
        Assert.assertEquals(user_steps.getObjectResponse().getJobTitle(),  user.getJobTitle(), "Job title is not correct");
    }

    @Test(description = "delete las user")
    public void deleteUserTest(){
        user_steps.getApiEndpoint();
        user_steps.getObjects();
        Assert.assertEquals(user_steps.getStatusCode(), 200, "Status is not correct");
        String id = user_steps.getLastId();
        Assert.assertNotNull(id, "NOT HAVE USERS");
        user_steps.deleteObject(id);
        Assert.assertEquals(user_steps.getStatusCode(), 200, "Status is not correct");
        user_steps.getObject(id);
        Assert.assertEquals(user_steps.getStatusCode(), 404, "Status is not correct");
    }
}
