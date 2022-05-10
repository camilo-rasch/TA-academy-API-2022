package com.automation.api.tests;

import com.automation.api.data.Data;
import com.automation.api.pojo.Transaction;
import org.testng.annotations.Test;

import java.util.List;

public class PostTest extends BaseTest{

    @Test(dataProvider = "transactions", dataProviderClass = Data.class)
    public void testPostRequest(List<Transaction> transactions){
        transactions_steps.createTransaction(transactions);
        transactions_steps.getTransactions();
        transactions_steps.showActualTransactionsList();
    }
}
