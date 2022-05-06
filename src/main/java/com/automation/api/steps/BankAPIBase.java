package com.automation.api.steps;

import com.automation.api.pojo.BankUser;
import io.restassured.http.ContentType;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

public class BankAPIBase {

    private final String endpoint;

    private Response response;

    public Logger logger;

    /**
     *
     * @param uri of the webpage
     */
    public BankAPIBase(String uri) {
        this.endpoint = uri + "/bankUser/";
        this.logger = Logger.getLogger(BankAPIBase.class);
    }

    /**
     * print endpoint
     */
    public void getBankUsersEndpoint() {
        logger.info(endpoint);
    }

    /**
     * GET Method all users
     */
    public List<BankUser> getAllBankUsers() {
        response = given().get(endpoint);
        List<BankUser> userList;
        try {
            userList = response.then().extract().response().jsonPath().getList("$", BankUser.class);
        }
        catch (JsonPathException e){
            userList = null;
        }

        logger.info(userList);
        return userList;
    }

    /**
     * GET Method for a single bank user
     * @param id long
     * @return single BankUser
     */
    public BankUser getBankUserById(long id) {
        response = given().get(endpoint + id);
        logger.info(response.asString());
        return response.then().extract().response().jsonPath().getObject("$", BankUser.class);
    }

    /**
     * DELETE method all users
     */
    public void deleteAllBankUsers(List<BankUser> users) {
        users.forEach(user -> response = given().delete(endpoint + user.getId()));
    }

    /**
     * POST method new user
     * @param user BankUser
     */
    public void createNewBankUser(BankUser user) {
        response = given().contentType(ContentType.JSON).body(user).when().post(endpoint);
    }

    /**
     * GET method all user emails
     */
    public List<String> getAllBankUsersEmail() {
        response = given().get(endpoint);
        List<BankUser> userList = response.then().extract().response().jsonPath().getList("$", BankUser.class);
        return userList.stream().map(BankUser::getEmail).collect(Collectors.toList());
    }

    /**
     * PUT method update bank user account number
     * @param userToUpdate BankUser
     * @param accountNumber string
     */
    public void updateBankUser(BankUser userToUpdate, String accountNumber) {
        userToUpdate.setAccount_number(accountNumber);
        response = given().contentType(ContentType.JSON).body(userToUpdate).when().put(endpoint + userToUpdate.getId());
    }

    /**
     * GET method get the latest user registered
     */
    public void getLatestBankUserRegistered() {
        response = given().get(endpoint);
        List<BankUser> userList = response.then().extract().response().jsonPath().getList("$", BankUser.class);
        long id = userList.get(userList.size()-1).getId();
        response = given().get(endpoint + id);
        logger.info(response.asString());
    }

    /**
     * get response status code
     * @return status code
     */
    public int getStatusCode() {
        return response.getStatusCode();
    }


}
