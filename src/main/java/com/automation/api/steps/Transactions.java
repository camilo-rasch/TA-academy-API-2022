package com.automation.api.steps;

import com.automation.api.pojo.BankTransaction;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class Transactions {

    private final String endpoint;
    public Logger log = Logger.getLogger(Transactions.class);
    private Response response;

    /**
     * Constructor.
     *
     * @param uri String
     */
    public Transactions(String uri) {
        endpoint = uri + "/api/v1/bankTransactions/";
    }

    /**
     * Get transactions endpoint print.
     */
    public void getTransactionsAPIEndpoint() {
        log.info(endpoint);
    }

    /**
     * GET Method transactions/:id.
     *
     * @param id String
     */
    public void getTransaction(String id) {
        response = given()
                .get(endpoint + id);
    }

    /**
     * GET Method transactions (list of transactions).
     */
    public void getTransactions() {
        response = given()
                .get(endpoint);
    }

    /**
     * POST Method create new transaction.
     *
     * @param bankTransaction {@link BankTransaction}
     */
    public void createTransaction(BankTransaction bankTransaction) {
        response = given()
                .contentType(ContentType.JSON)
                .body(bankTransaction)
                .when()
                .post(endpoint);
    }

    /**
     * get response status code.
     *
     * @return status code int
     */
    public int getStatusCode() {
        return response.getStatusCode();
    }

    /**
     * method get for List of BankTransaction.
     *
     * @return List of BankTransaction
     */
    private List<BankTransaction> getBankTransactionsList() {
        return response
                .then()
                .extract()
                .response()
                .jsonPath()
                .getList("$", BankTransaction.class);
    }

    /**
     * Print list of transactions.
     */
    public void showActualTransactionsList() {
        try {
            log.info(getBankTransactionsList());
        } catch (io.restassured.path.json.exception.JsonPathException e) {
            log.info("API Endpoint Empty");
        }
    }

    /**
     * DELETE Method, delete transaction by id.
     *
     * @param id String
     */
    public void deleteTransaction(String id) {
        response = given().delete(endpoint + id);
    }

    /**
     * clean endpoint of transactions.
     */
    public void cleanTransactionsEndpoint() {
        try {
            List<BankTransaction> bankTransactionList = getBankTransactionsList();
            for (BankTransaction bk : bankTransactionList) {
                deleteTransaction(bk.getId());
            }
        } catch (io.restassured.path.json.exception.JsonPathException e) {
            log.info("API Endpoint Empty");
        }
    }

    /**
     * check if exists duplicate emails in endpoint.
     */
    public boolean checkDuplicateEmails() {
        Set<String> duplicated = new HashSet<>();
        try {
            List<BankTransaction> bankTransactionList = getBankTransactionsList();
            for (BankTransaction bk : bankTransactionList) {
                if (!duplicated.add(bk.getEmail())) {
                    log.info("duplicated email found");
                    return true;
                }
            }
        } catch (io.restassured.path.json.exception.JsonPathException e) {
            log.info("API Endpoint Empty");
        }
        return false;
    }

    /**
     * get last transaction register
     *
     * @return BankTransaction
     */
    public BankTransaction getLastTransaction() {
        List<BankTransaction> bankTransactionList = null;
        try {
            bankTransactionList = getBankTransactionsList();
            return bankTransactionList.get(bankTransactionList.size() - 1);
        } catch (io.restassured.path.json.exception.JsonPathException e) {
            log.info("API Endpoint Empty");
        }
        return bankTransactionList.get(bankTransactionList.size() - 1);
    }

    /**
     * PUT Method update accountNumber of a transaction.
     *
     * @param bankTransaction BankTransaction
     * @param accountNumber   String
     */
    public void updateTransaction(BankTransaction bankTransaction, String accountNumber) {
        bankTransaction.setAccountNumber(accountNumber);

        response = given()
                .contentType(ContentType.JSON)
                .body(bankTransaction)
                .when()
                .put(endpoint + bankTransaction.getId());

    }

}
