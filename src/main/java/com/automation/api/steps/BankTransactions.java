package com.automation.api.steps;

import com.automation.api.pojo.BankTransaction;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class BankTransactions {

    private final String endpoint;
    private Response response;
    public Logger log = Logger.getLogger(BankTransactions.class);

    /**
     * Constructor.
     * @param base_url String
     */
    public BankTransactions(String base_url) {
        endpoint = base_url + "/api/v1/transact";
    }

    /**
     * Get bank transactions endpoint print.
     */
    public void getBankTransactionsAPIEndpoint() {
        log.info(endpoint);
    }

    /**
     * (GET Method) - transact/:id
     * @param id String
     */
    public void getTransact(String id) {
        response = given().get(endpoint + id);
        log.info(response.asString());
    }

    /**
     * (GET Method) - Transactions (list of transactions).
     */
    public void getTransactions() {
        response = given().get(endpoint);
    }

    /**
     * (POST Method) - Create new transaction.
     * @param bankTransaction {@link BankTransaction}
     */
    public void createTransaction(BankTransaction bankTransaction) {
        response = given().contentType(ContentType.JSON).body(bankTransaction).when().post(endpoint);
    }

    /**
     * (PUT Method) - Update an existing transaction by id
     * @param id String
     * @param email String
     * @param telephone String
     */
    public void updateEmailTelephoneTransaction(String id, String email, String telephone) {
        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setEmail(email);
        bankTransaction.setTelephone(telephone);

        response = given().contentType(ContentType.JSON).body(bankTransaction).when().put(endpoint + id);
    }

    /**
     * (DELETE Method) - Delete transaction by id.
     * @param id String
     */
    public void deleteTransaction(String id) {
        response = given().delete(endpoint + id);
    }

    /**
     * (GET Method) - Response status code.
     * @return status code int
     */
    public int getStatusCode() {
        return response.getStatusCode();
    }

    /**
     * Print list of transactions.
     */
    public void showActualTransactionsList() {
        List<BankTransaction> bankTransactionList = response.then().extract().response().jsonPath().getList("$", BankTransaction.class);
        log.info(bankTransactionList);
    }

    /**
     * Find a transaction by account number
     * @param account_number String
     * @return sting with the id
     */
    public String getTransactionID(String account_number) {
        List<BankTransaction> bankTransactionList = response.then().extract().response().jsonPath().getList("$", BankTransaction.class);

        Optional<BankTransaction> id = bankTransactionList.stream().filter(bankTransaction ->
                        account_number.equals(bankTransaction.getAccount_number()))
                .findFirst();

        if (id.isPresent())
            return id.get().getId();
        else
            return "";
    }

    /**
     * (GET) - Last id in the transaction list.
     * @return String with the id
     */
    public String getLastId() {
        List<BankTransaction> bankTransactionList =response.then().extract().response().jsonPath().getList("$", BankTransaction.class);

        return bankTransactionList.get(bankTransactionList.size() - 1).getId();
    }

    /**
     * (GET) - Bank Transaction response.
     * @return BankTransaction
     */
    public BankTransaction getBankTransactionResponse() {
        return response.then().extract().as(BankTransaction.class);
    }



}
