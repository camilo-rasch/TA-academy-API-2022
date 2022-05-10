package com.automation.api.steps;

import com.automation.api.pojo.Transaction;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

public class Transactions extends BaseStep {

    private Logger log = Logger.getLogger(Transactions.class);

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
     * POST method. Create a list of transactions
     * @param transactions {@link Transaction}
     */
    public void createTransaction(List<Transaction> transactions){
        Transaction transaction;
        for (int i = 0; i < transactions.size(); i++){
            transaction = transactions.get(i);
            response = given().contentType(ContentType.JSON).body(transaction)
                    .when().post(endpoint);
        }
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

    /**
     * Show list of transactions after GET request
     */
    public void showListOfTransactions(){
        List<Transaction> transactions = response.then().extract().response()
                .jsonPath().getList("$", Transaction.class);
        log.info(transactions);
    }

    /**
     * Delete all transactions
     */
    public void deleteTransactions(){
        List<Transaction> transactions = response.then().extract().response()
                .jsonPath().getList("$", Transaction.class);
        List<String> transactionsId = new ArrayList<>();
        transactions.forEach(transaction-> transactionsId.add(transaction.getId()));
        for (String id : transactionsId) {
            deleteTransaction(id);
        }
    }

    /**
     * True if there are repeated emails, false otherwise.
     * @return boolean
     */
    public boolean areDuplicateEmails(){
        List<Transaction> transactions = response.then().extract().response()
                .jsonPath().getList("$", Transaction.class);
        List<String> emails = new ArrayList<>();
        transactions.forEach(transaction-> emails.add(transaction.getEmail()));
        Set<String> nonRepeatedEmails = new HashSet<>(emails);
        nonRepeatedEmails.addAll(emails);

        return !(emails.size() == nonRepeatedEmails.size());
    }
}
