package com.automation.api.steps;

import com.automation.api.IApiAction;
import com.automation.api.pojo.Transaction;
import com.automation.api.pojo.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class Transactions implements IApiAction<Transaction>
{
    private final String endpoint;
    private Response response;
    public Logger log;

    /**
     * Constructor.
     * @param uri String
     */
    public Transactions(String uri)
    {
        this.endpoint = uri + "/bank_transactions/transactions/";
        this.log = Logger.getLogger(Transactions.class);
    }

    /**
     * Get transaction endpoint print.
     */
    @Override
    public void getApiEndpoint() {
        log.info(this.endpoint);
    }

    /**
     * GET Method transactions/:id.
     * @param id String
     */
    @Override
    public void getObject(String id)
    {
        response = given().get(this.endpoint+id);
        log.info(response.asString());
    }

    /**
     * GET Method transactions (list of transactions).
     */
    @Override
    public void getObjects()
    {
        response = given().get(endpoint);
    }

    /**
     * POST Method create new transaction.
     * @param transaction {@link Transaction}
     */
    @Override
    public void createObject(Transaction transaction) {
        response = given().contentType(ContentType.JSON).body(transaction).when().post(endpoint);
    }

    /**
     * PUT Method update amount of transaction.
     * @param id String
     * @param amount float
     */
    public void updateTransaction(String id, float amount) {

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);


        response = given().contentType(ContentType.JSON).body(transaction).when().put(endpoint + id);
    }

    /**
     * DELETE Method, delete transaction by id.
     * @param id String
     */
    @Override
    public void deleteObject(String id) {
        response = given().delete(endpoint + id);
    }

    /**
     * get response status code.
     * @return status code int
     */
    @Override
    public int getStatusCode() {
        return response.getStatusCode();
    }

    /**
     * Print list of transactions.
     */
    @Override
    public void showActualObjectList() {
        List<Transaction> transactions = response.then().extract().response().jsonPath().getList("$", Transaction.class);
        log.info(transactions);
    }

    /**
     * Find first transaction with the name.
     * @param firstName String name
     * @return string with the id
     */
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

    /**
     * Get last id in the list.
     * @return sting with the id
     */
    @Override
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


    /**
     * get transaction response.
     * @return Transaction
     */
    @Override
    public Transaction getObjectResponse() {
        return response.then().extract().as(Transaction.class);
    }
}
