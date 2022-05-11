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

    public Transactions(String uri) {
        endpoint = uri + "/api/v1/bankTransactions/";
    }

    public void getTransactionsAPIEndpoint() {
        log.info(endpoint);
    }

    public void getTransaction(String id) {
        response = given()
                .get(endpoint + id);
    }

    public void getTransactions() {
        response = given()
                .get(endpoint);
    }

    public void createTransaction(BankTransaction bankTransaction) {
        response = given()
                .contentType(ContentType.JSON)
                .body(bankTransaction)
                .when()
                .post(endpoint);
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public List<BankTransaction> getBankTransactionsList() {
        return response
                .then()
                .extract()
                .response()
                .jsonPath()
                .getList("$", BankTransaction.class);
    }

    public void showActualTransactionsList() {
        try {
            log.info(getBankTransactionsList());
        } catch (io.restassured.path.json.exception.JsonPathException e) {
            log.info("API Endpoint Empty");
        }
    }

    public void deleteTransaction(String id) {
        response = given().delete(endpoint + id);
    }

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

    public void updateTransaction(BankTransaction bankTransaction, String accountNumber) {
        bankTransaction.setAccountNumber(accountNumber);

        response = given()
                .contentType(ContentType.JSON)
                .body(bankTransaction)
                .when()
                .put(endpoint + bankTransaction.getId());

    }

}
