package com.automation.api.steps;

import com.automation.api.pojo.UserTransaction;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Users2 {
    private final String endpoint;
    private Response response;
    public Logger log = Logger.getLogger(Users2.class);

    public Users2(String uri) {
        endpoint = uri + "transactions/";
    }
    /**
     * Get users endpoint print.
     */
    public void getAPIEndpoint() {
        log.info(endpoint);
    }
    /**
     * GET Method users (list of users).
     */
    public List<UserTransaction> getTransactions() {
        response = given().get(endpoint);
        return response.jsonPath().getList("$",UserTransaction.class);
    }

    public void showAllTransactions(){
        response = given().get(endpoint);
        List<UserTransaction> transactions = response.jsonPath().getList("$",UserTransaction.class);
        for(UserTransaction transaction : transactions){
            System.out.println("People : " + transaction.getId());
        }
    }
    /**
     * POST Method create new user.
     * @param transaction {@link UserTransaction}
     */
    public void createUser(UserTransaction transaction) {
        response = given().contentType(ContentType.JSON).body(transaction).when().post(endpoint);
    }
    /**
     * DELETE Method, delete user by id.
     * @param id String
     */
    public void deleteTransaction(String id) {
        response = given().delete(endpoint + id);
    }

    /**
     * get response status code.
     * @return status code int
     */
    public int getStatusCode() {
        return response.getStatusCode();
    }

    public void deleteAllTransactions(){

    }
}
