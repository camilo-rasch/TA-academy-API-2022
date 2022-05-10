package com.automation.api.data;

import com.automation.api.pojo.BankTransaction;
import com.automation.api.pojo.User;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

/**
 * Class that contents the creates the data for the test
 * @author luis.pineda@globant.com
 */
public class DataBT {

    Faker faker = new Faker();

    @DataProvider(name = "bankTransaction")
    public Object[][] inputData() {
        BankTransaction bankTransaction = new BankTransaction();
        return new Object[][] {{bankTransaction}};
    }
}
