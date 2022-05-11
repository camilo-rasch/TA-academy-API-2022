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
     * Show in console list of transactions
     */
    public List<Transaction> actualTransactionsList(){
        return response.then().extract().response()
                .jsonPath().getList("$", Transaction.class);
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
