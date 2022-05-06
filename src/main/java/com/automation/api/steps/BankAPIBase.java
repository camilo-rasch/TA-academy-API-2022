package com.automation.api.steps;

import com.automation.api.pojo.BankUser;
import io.restassured.http.ContentType;
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
    public void getAllBankUsers() {
        response = given().get(endpoint);
    }

    /**
     * DELETE method all users
     */
    public void deleteAllBankUsers() {
        response =given().delete(endpoint);
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
        List<BankUser> userList = response.then().extract().response().jsonPath().getList("$", BankUser.class);
        return userList.stream().map(BankUser::getEmail).collect(Collectors.toList());
    }

    /**
     * PUT method update back user account number
     * @param id long
     * @param accountNumber string
     */
    public void updateBankUser(long id, String accountNumber) {
        BankUser user = new BankUser();
        user.setAccount_number(accountNumber);
        response = given().contentType(ContentType.JSON).body(user).when().put(endpoint + id); //TODO: check if this one works with put instead of post
    }

    /**
     * get response status code
     * @return status code
     */
    public int getStatusCode() {
        return response.getStatusCode();
    }


}
