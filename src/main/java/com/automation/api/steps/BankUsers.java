package com.automation.api.steps;

import com.automation.api.pojo.BankUser;
import io.restassured.http.ContentType;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class BankUsers {

    private final String endpoint;
    private Response response;
    public Logger log = Logger.getLogger(BankUsers.class);


    public BankUsers(String uri) {
        endpoint = uri + "/BankUsers/";
    }

    public void getResponseAPIEndpoint() {
        log.info(endpoint);
    }

    /**
     * Update user account number
     * @param id
     * @param accountNumber
     */
    public void updateUser(String id, String accountNumber) {
        BankUser user = new BankUser();
        user.setAccountNumber(accountNumber);
        response = given().contentType(ContentType.JSON).body(user).when().put(endpoint + id);
    }

    /**
     * Create bank user
     * @param user
     */
    public void createUser(BankUser user) {
        response = given().contentType(ContentType.JSON).body(user).when().post(endpoint);
    }

    /**
     * Delete all users in case endpoint is not empty
     * @param users
     */
    public void deleteAllUsers(List<BankUser> users) {
        users.forEach(bankUser -> response = given().delete(endpoint + bankUser.getId()));
    }

    /**
     * Retrieve all bank users
     * @return List-BankUser
     */
    public List<BankUser> getAllBankUsers() {
        response = given().get(endpoint);
        List<BankUser> bankUserList;
        try {
            bankUserList = response.then().extract().response().jsonPath().getList("$", BankUser.class);
        }
        catch (JsonPathException e){
            bankUserList = null;
        }
        return bankUserList;
    }

    /**
     * Retrieve all bank users email
     * @return List of strings
     */
    public List<String> getAllBankUsersEmail() {
        response = given().get(endpoint);
        List<BankUser> usersEmail = response.then().extract().response().jsonPath().getList("$", BankUser.class);
        return usersEmail.stream().map(BankUser::getEmail).collect(Collectors.toList());
    }

    /**
     * Retrieve the status code of api request
     * @return status code
     */
    public int getStatusCode() {
        return response.getStatusCode();
    }

    /**
     * Generate a random account number
     * @return random number as String
     */
    public String randomNumberGenerator() {
        Random random = new Random();
        String card =  "";
        for (int i = 0; i < 10; i++) {
            int n = random.nextInt(10) + 0;
            card += Integer.toString(n);
            System.out.print(card.charAt(i));
        }
        return card;
    }
}
