package com.automation.api.steps;

import com.automation.api.pojo.Transaction;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Random;


import static io.restassured.RestAssured.given;


public class Transactions {

    private final String endpoint;
    private Response response;
    private boolean checkDuplicates;
    public Logger log = Logger.getLogger(Transactions.class);

    /**
     * Constructor
     * @param uri String
     */
    public Transactions(String uri) {
        endpoint = uri + "/api/v1/transactions/";
    }

    /**
     * Get check duplicates
     * @return boolean
     */
    public boolean isCheckDuplicates() {
        return checkDuplicates;
    }

    /**
     * Set check duplicates
     * @param checkDuplicates boolean
     */
    public void setCheckDuplicates(boolean checkDuplicates) {
        this.checkDuplicates = checkDuplicates;
    }

    /**
     * Get users endpoint print
     */
    public void getTransactionEndpoint(){
        log.info(endpoint);
    }

    /**
     * GET Method transactions (list of transactions)
     */
    public void getTransactions(){
        response = given().get(endpoint);
    }

    /**
     * GET method transactions/:id
     * @param id String
     */
    public void getTransaction(String id){
        response = given().get(endpoint + id);
        log.info(response.asString());
    }

    /**
     * POST Method create a new transactions
     * @param transaction {@link Transaction}
     */
    public void createTransaction(Transaction transaction){
        response = given().contentType(ContentType.JSON).body(transaction).when().post(endpoint);
    }

    /**
     * PUT Method update account number of a transaction
     * @param id String
     * @param accountNumber String
     */
    public void updateTransaction(String id, String accountNumber){
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);

        response = given().contentType(ContentType.JSON).body(transaction).when().put(endpoint + id);
    }

    /**
     * DELETE Method, delete user by id.
     * @param id String
     */
    public void deleteTransaction(String id){
        response = given().delete(endpoint + id);
    }

    /**
     * Get all Transactions as list
     * {@link Transaction}
     * @return list of transaction objects
     */
    public List<Transaction> getAllTransactions(){
        return response.then().extract().response().jsonPath().getList("$", Transaction.class);
    }

    /**
     * Get response status code of the method
     * @return status code Integer
     */
    public int getStatusCode(){
        return response.getStatusCode();
    }

    /**
     * Delete all transactions
     */
    public void deleteAllTransactions(){
        List<Transaction> transactions = getAllTransactions();
        for (Transaction item:transactions) {
            deleteTransaction(item.getId());
        }
    }

    /**
     * Check if exist duplicates in a list of transactions
     * @param transactionList {@link Transaction}
     */
    public void checkIfDuplicates(List<Transaction> transactionList){
        setCheckDuplicates(false);
        boolean flag = false;
        for(int i = 0; i < transactionList.size(); i++) {
            if(!flag){
                for(int j = i + 1; j < transactionList.size(); j++) {
                    if(j != i && transactionList.get(j).getEmail().equals(transactionList.get(i).getEmail())) {
                        setCheckDuplicates(true);
                        flag = true;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Get transaction response
     * @return Transaction
     */
    public Transaction getTransactionResponse() {
        return response.then().extract().as(Transaction.class);
    }

    /**
     * Generate a 8 random number
     * @return String
     */
    public String fakeNumber(){
        Random random = new Random();
        int randomNumber = random.nextInt(99999999);
        return String.format("%08d",randomNumber);
    }

    /**
     * Override method toString object
     * @return String
     */
    @Override
    public String toString() {
        return ""+ response.asString()+"";
    }
}
