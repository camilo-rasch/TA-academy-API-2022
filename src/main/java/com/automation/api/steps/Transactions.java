package com.automation.api.steps;

import com.automation.api.pojo.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class to provide functionality in tests layer.
 * @author js.lozano
 */
public class Transactions extends BaseStep {

    /**
     * Constructor
     * @param uri String
     */
    public Transactions(String uri){
        super(uri + "/v1/transaction/");
    }

    /**
     * POST method. Create a list of transactions
     * @param transactions List
     */
    public void postRequest(List<Transaction> transactions){
        for (Transaction value : transactions) {
            postRequest(value);
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

    /**
     * Return message "Not Found" if endpoint is empty
     * @return String
     */
    public String getEndpointEmptyMessage(){
        return response.then().extract().asString();
    }

    /**
     * Show in console list of transactions
     */
    public List<Transaction> actualTransactionsList(){
        return response.then().extract().response()
                .jsonPath().getList("$", Transaction.class);
    }

    /**
     * Get last transaction id in the endpoint
     * @return String
     */
    public String getLastTransactionId(){
        List<Transaction> transactions = response.then().extract().response()
                .jsonPath().getList("$", Transaction.class);

        return transactions.get(transactions.size() - 1).getId();
    }

    /**
     * Delete all transactions in endpoint
     */
    @Override
    public void deleteEndpoint() {
        this.getRequest();
        log.info(getStatusCode());

        if (getStatusCode() == 200){
            log.info(actualTransactionsList());
            List<Transaction> transactions = response.then().extract().response()
                    .jsonPath().getList("$", Transaction.class);

            List<String> transactionsId = new ArrayList<>();
            transactions.forEach(transaction-> transactionsId.add(transaction.getId()));
            for (String id : transactionsId) {
                deleteRequest(id);
            }
        }
    }

    /**
     * Get transaction response after GET request by id
     * @return {@link Transaction}
     */
    @Override
    public Transaction getObjectResponse(){
        return response.then().extract()
                .as(Transaction.class);
    }
}
