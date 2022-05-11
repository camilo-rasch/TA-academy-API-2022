package com.automation.api.data;

import com.automation.api.pojo.BankTransaction;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDataGenerator {

    /**
     * @author Sebastián Correa
     *
     * Using the random generators of this class, this method creates a list of 10 transactions with random values.
     * Since the data was created only to match the attributes types, some values may not be readable.
     * @return List containing randomly generated transactions
     */
    @DataProvider(name = "randomTransactions")
    public Object[][] generateRandomTransactions() {
        List<BankTransaction> randomTransactions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String name = generateRandomAlphabeticalString(10);
            String lastName = generateRandomAlphabeticalString(10);
            String accountNumber = generateRandomNumericalString();
            double amount = Double.parseDouble(generateRandomNumericalString().substring(0, 4));
            String email = generateRandomAlphabeticalString(8) + "@gmail.com";
            String country = generateRandomAlphabeticalString(6);
            String telephone = generateRandomNumericalString();
            BankTransaction randomTransaction = new BankTransaction(name, lastName, accountNumber, amount, "payment",
                    email, true, country, telephone);
            randomTransactions.add(randomTransaction);
        }
        return new Object[][]{{randomTransactions}};
    }

    /**
     * @author Sebastián Correa
     *
     * Given an int, this method generates a random alphabetical String, which size is the same as the specified number.
     * Note that the method uses only ASCII characters of lower case letters.
     * @param stringLength Desired alphabetical string length
     * @return Random alphabetical string
     */
    public String generateRandomAlphabeticalString(int stringLength) {
        Random random = new Random();
        StringBuilder randomString = new StringBuilder();

        for(int i = 0; i < stringLength; i++){
            randomString.append((char)(random.nextInt(122 - 97) + 97));
        }

        return randomString.toString();
    }

    /**
     * @author Sebastián Correa
     *
     * This method generates a random number of 8 digits.
     * @return Random 8 digit number as String
     */
    public String generateRandomNumericalString() {
        Random random = new Random();
        return "" + (random.nextInt(99999999 - 11111111) + 11111111);
    }
}
