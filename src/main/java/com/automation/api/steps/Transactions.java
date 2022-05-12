package com.automation.api.steps;

import com.automation.api.pojo.Transaction;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class Transactions
{
    private final String endpoint;
    private Response response;
    public Logger log;

    public Transactions(String uri)
    {
        this.endpoint = uri + "/bank_transactions/transactions/";
        this.log = Logger.getLogger(Transactions.class);
    }

    public void getTransactionApiEndpoint()
    {
        log.info(this.endpoint);
    }

    public void getTransaction(String id)
    {
        response = given().get(this.endpoint+id);
        log.info(response.asString());
    }

    public void getTransactions()
    {
        response = given().get(endpoint);
    }

    public void createTransaction(Transaction transaction) {
        response = given().contentType(ContentType.JSON).body(transaction).when().post(endpoint);
    }

    public void updateTransaction(String id, float amount) {

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);


        response = given().contentType(ContentType.JSON).body(transaction).when().put(endpoint + id);
    }

    public void deleteTransaction(String id) {
        response = given().delete(endpoint + id);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public void showActualTransactionList() {
        List<Transaction> transactions = response.then().extract().response().jsonPath().getList("$", Transaction.class);
        log.info(transactions);
    }

    public String getTransactionID(String firstName) {
        List<Transaction> transactions = response.then().extract().response().jsonPath().getList("$", Transaction.class);

        Optional<Transaction> id = transactions.stream().filter(transaction ->
                        firstName.equals(transaction.getFirst_name()))
                .findFirst();

        if (id.isPresent())
            return id.get().getId();
        else
            return "";
    }

    public String getLastId() {
        List<Transaction> transactions =response.then().extract().response().jsonPath().getList("$", Transaction.class);

        return transactions.get(transactions.size() - 1).getId();
    }

    public boolean isTransactionsEmpty()
    {
        try {
            List<Transaction> transactions = response.then().extract().response().jsonPath().getList("$", Transaction.class);
            return transactions == null || transactions.size()== 0;
        }catch (Exception e)
        {
            return true;
        }
    }

    public List<Transaction>getTransactionsList()
    {
        try {
            return response.then().extract().response().jsonPath().getList("$", Transaction.class);
        }catch (Exception e)
        {
            return null;
        }
    }


    public Transaction getTransactionResponse() {
        return response.then().extract().as(Transaction.class);
    }

}
