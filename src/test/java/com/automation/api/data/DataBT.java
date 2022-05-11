package com.automation.api.data;

import com.automation.api.pojo.BankTransaction;
import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.Random;

/**
 * Class that contents the creates the data for the test
 * @author luis.pineda@globant.com
 */
public class DataBT {

    private static final Faker faker = new Faker();
    static final String[] transa_type = {"Payment", "Invoice", "Withdrawal", "Deposit"};

    @DataProvider(name = "bankTransaction")
    public Object[][] inputData() {

        Random transa_random = new Random();
        String transa_string = transa_type[transa_random.nextInt(transa_type.length)];

        BankTransaction bankTransaction = new BankTransaction(faker.name().firstName(),
                faker.name().lastName(),
                faker.code().gtin8(),
                transa_string,
                faker.internet().emailAddress(),
                faker.bool().bool(),
                faker.country().name(),
                faker.phoneNumber().cellPhone());
        return new Object[][] {{bankTransaction}};
    }
}