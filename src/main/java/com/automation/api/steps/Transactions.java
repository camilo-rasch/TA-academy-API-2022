package com.automation.api.steps;

import com.automation.api.pojo.Transaction;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class Transactions extends BaseStep {

    /**
     * Constructor
     * @param uri String
     */
    public Transactions(String uri){
        super(uri + "/v1/transaction/");
    }

    /**
     * GET method to get list of transactions
     */
    public void getTransactions(){
        response = when().get(endpoint);
    }

    /**
     * GET method to get transaction by id
     * @param id String
     */
    public void getTransaction(String id){
        response = when().get(endpoint + id);
    }

    /**
     * DELETE method. Delete transaction by id
     * @param id String
     */
    public void deleteTransaction(String id){
        response = when().delete(endpoint + id);
    }

    /**
     * POST method. Create transaction
     * @param transaction {@link Transaction}
     */
    public void createTransaction(Transaction transaction){
        response = given().contentType(ContentType.JSON).body(transaction).when().post(endpoint);
    }

    /**
     * UPDATE method. Update account number in transaction
     * @param accountNumber long
     */
    public void updateTransaction(String id, long accountNumber){
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);

        response = given().contentType(ContentType.JSON).body(transaction)
                .when().put(endpoint + id);
    }
}
