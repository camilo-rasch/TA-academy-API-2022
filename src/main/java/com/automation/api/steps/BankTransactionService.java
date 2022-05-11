package com.automation.api.steps;

import com.automation.api.pojo.BankTransaction;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BankTransactionService {

    private final String endpoint;
    public Logger log;
    private Response response;

    /**
     * Constructor.
     * @param uri String
     */
    public BankTransactionService(String uri) {
        this.endpoint = uri + "/transactions/";
        this.log = Logger.getLogger(BankTransaction.class);
    }

    /**
     * GET Method transactions (list of transactions).
     */
    public void getTransactions() {
        response = given().get(endpoint);
    }

    /**
     * @author Sebastián Correa
     *
     * GET Method by transaction id.
     * @param id Unique id of the transaction
     */
    public void getTransactionById(int id) {
        response = given().get(endpoint + id);
    }

    /**
     * Print list of transactions.
     */
    public void showTransactionsList() {
        List<BankTransaction> transactions = response.then().extract().response().jsonPath().getList("$", BankTransaction.class);
        transactions.forEach(bankTransaction -> log.info(bankTransaction));
    }

    /**
     * Get response status code.
     * @return status code int
     */
    public int getStatusCode() {
        return response.getStatusCode();
    }

    /**
     * @author Sebastián Correa
     *
     * Get response object from payload.
     * @return Object from the response payload
     */
    public BankTransaction getResponseObject() {
        return response.then().extract().jsonPath().getObject("$", BankTransaction.class);
    }

    /**
     * @author Sebastián Correa
     *
     * Get all payload from the response.
     * @return Response payload as list
     */
    public List<BankTransaction> getResponsePayload() {
        return response.then().extract().jsonPath().getList("$", BankTransaction.class);
    }

    /**
     * @author Sebastián Correa
     *
     * PUT Method to update a transaction by its id.
     * @param id Unique id of the transaction to update
     * @param transaction BankTransaction object with the updated values
     */
    public void updateTransaction(int id, BankTransaction transaction) {
        response = given().contentType(ContentType.JSON).body(transaction).when().put(endpoint + id);
    }

    /**
     * @author Sebastián Correa
     *
     * This method posts a collection of transactions.
     * @param transactions List of transactions to post
     */
    public void createTransactions(List<BankTransaction> transactions) {
        transactions.forEach(this::createTransaction);
    }

    /**
     * @author Sebastián Correa
     *
     * POST method to create a transaction.
     * @param transaction BankTransaction object to send as body of the request
     */
    public void createTransaction(BankTransaction transaction) {
        response = given().contentType(ContentType.JSON).body(transaction).when().post(endpoint);
    }

    /**
     * @author Sebastián Correa
     *
     * This method deletes all the transactions of the endpoint after checking if the endpoint is already empty.
     *
     * @return A message indicating if the transactions were deleted or if the endpoint was already empty.
     */
    public String deleteEndpoint() {
        boolean endpointIsEmpty = checkIfEndpointIsEmpty();
        if (!endpointIsEmpty) {
            List<BankTransaction> transactions = response.then().extract().response().jsonPath().getList("$", BankTransaction.class);
            transactions.forEach(bankTransaction -> deleteTransaction(bankTransaction.getId()));
            return "All transactions deleted. Endpoint is empty";
        } else {
            return "Endpoint is already empty";
        }
    }

    /**
     * @author Sebastián Correa
     *
     * This method checks if the endpoint is empty by the status code of the response.
     * If the status code is 200, the endpoint has transactions data.
     * If the status code is 404, this means that the endpoint is empty.
     *
     * @return true if the endpoint is empty, otherwise false.
     */
    public boolean checkIfEndpointIsEmpty() {
        return !(getStatusCode() == 200) || (getStatusCode() == 404);
    }

    /**
     * @author Sebastián Correa
     *
     * DELETE method to remove a transaction using its id.
     * @param id The unique id of the transaction.
     */
    public void deleteTransaction(String id){
        response = when().delete(endpoint + id);
    }

    /**
     * @author Sebastián Correa
     *
     * This method checks if the list of transaction emails contains duplicated emails.
     * The validation is made by comparing a Set and a List, both containing the emails, since the Set doesn't allow duplicated entries.
     * If the endpoint is empty, it is considered that there are non duplicated emails.
     *
     * @return true if there are duplicated emails, otherwise false.
     */
    public boolean checkIfEmailsAreDuplicated() {
        boolean emailsAreDuplicated = false;
        if (!checkIfEndpointIsEmpty()) {
            List<BankTransaction> transactions = response.then().extract().response().jsonPath().getList("$", BankTransaction.class);
            List<String> currentEmailList = new ArrayList<>();
            transactions.forEach(bankTransaction -> currentEmailList.add(bankTransaction.getEmail()));
            Set<String> nonDuplicatedEmails = new HashSet<>(currentEmailList);
            emailsAreDuplicated = (nonDuplicatedEmails.size() != currentEmailList.size());
        }
        return emailsAreDuplicated;
    }
}
