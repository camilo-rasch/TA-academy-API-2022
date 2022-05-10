package com.automation.api.data;

import com.automation.api.pojo.Transaction;
import com.automation.api.pojo.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Data {

    @DataProvider(name = "users")
    public Object[][] inputData() {
        User carla = new User("carla",
                "pondrea",
                "lasdffuidsa@p.com",
                "Colombia",
                "3452345",
                true,
                "Java dev");
        return new Object[][] {{carla}};
    }

    /**
     * Provide ten instances of Transaction in a List
     * @return Object[][]
     */
    @DataProvider(name = "transactions")
    public Object[][] inputTransactions(){
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 1; i <= 10; i++){
            Transaction transaction = new Transaction("User " + i, "Globant ",
                    generateRandomLong(), generateRandomLong(), "Deposit",
                    generateRandomEmail(), true, "Colombia", generateRandomLong());
            transactions.add(transaction);
        }
        return new Object[][]{{transactions}};
    }

    /**
     * Provide a random account number
     * @return Object[][]
     */
    @DataProvider(name = "accountNumber")
    public Object[][] inputAccountNumber(){
        long randomAccountNumber = generateRandomLong();
        return new Object[][]{{randomAccountNumber}};
    }

    /**
     * Generate random string of length 6
     * @return String
     */
    private String generateRandomString(){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        int lengthOfString = 6;

        for(int i = 0; i < lengthOfString; i++){
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            randomString.append(randomChar);
        }
        return randomString.toString();
    }

    /**
     * Generate random email
     * @return String
     */
    private String generateRandomEmail(){
        return generateRandomString() + "@gmail.com";
    }

    /**
     * Generate random int from a min to a max value.
     * @return long
     */
    private long generateRandomLong(){
        int min = 1_000_000;
        int max = 9_999_999;

        return (int) (Math.random() * (max - min + 1) + min);
    }
}
