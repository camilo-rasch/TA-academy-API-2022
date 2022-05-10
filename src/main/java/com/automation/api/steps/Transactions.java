package com.automation.api.steps;

import com.automation.api.pojo.Transaction;
import com.automation.api.pojo.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.Random;


import static io.restassured.RestAssured.given;


public class Transactions {

    private final String endpoint;
    private Response response;
    private boolean checkDuplicates;
    public Logger log = Logger.getLogger(Transactions.class);

    public boolean isCheckDuplicates() {
        return checkDuplicates;
    }

    public void setCheckDuplicates(boolean checkDuplicates) {
        this.checkDuplicates = checkDuplicates;
    }

    public Transactions(String uri) {
        endpoint = uri + "/api/v1/transactions/";
    }

    public void getTransactionEndpoint(){
        log.info(endpoint);
    }

    public void getTransactions(){
        response = given().get(endpoint);
    }

    public void getTransaction(String id){
        response = given().get(endpoint + id);
        log.info(response.asString());
    }

    public void createTransaction(Transaction transaction){
        response = given().contentType(ContentType.JSON).body(transaction).when().post(endpoint);
    }

    public void updateTransaction(String id, String accountNumber){
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);

        response = given().contentType(ContentType.JSON).body(transaction).when().put(endpoint + id);
    }

    public void deleteTransaction(String id){
        response = given().delete(endpoint + id);
    }

    public List<Transaction> getAllTransactions(){
        return response.then().extract().response().jsonPath().getList("$", Transaction.class);
    }

    public int getStatusCode(){
        return response.getStatusCode();
    }

    public void deleteAllTransactions(){
        List<Transaction> transactions = getAllTransactions();
        for (Transaction item:transactions) {
            deleteTransaction(item.getId());
        }
    }

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

    public Transaction getTransactionResponse() {
        return response.then().extract().as(Transaction.class);
    }


    public String fakeNumber(){
        Random random = new Random();
        int randomNumber = random.nextInt(99999999);
        return String.format("%08d",randomNumber);
    }

    @Override
    public String toString() {
        return ""+ response.asString()+"";
    }
}
