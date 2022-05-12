package com.automation.api.data;

import com.automation.api.pojo.BankUser;
import com.automation.api.pojo.User;
import org.testng.annotations.DataProvider;

import java.util.HashMap;
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

    Random random = new Random();

    @DataProvider(name = "randomData")
    public Object[][] randomUser() {
        HashMap<String, BankUser> bankUserList = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            bankUserList.put(String.format("%01d",i), new BankUser(
                    randomUserGenerator(),
                    randomUserGenerator(),
                    randomEmailGenerator(),
                    "Colombia",
                    randomNumberGenerator(),
                    randomStatus(),
                    randomNumberGenerator(),
                    randomAmount(),
                    transactionGenerator()));
        }
        return new Object[][] {{bankUserList}};
    }

    /**
     * Generate random string
     * @return String
     */
    public String randomUserGenerator() {
        String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        int length = 8;

        for(int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHABET.length());
            char randomChar = ALPHABET.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    /**
     * Generate a random Email
     * @return String
     */
    public String randomEmailGenerator() {
        return randomUserGenerator() + "@gmail.com";
    }

    /**
     * Generate a random number
     * @return String
     */
    public String randomNumberGenerator() {
        String card =  "";
        for (int i = 0; i < 10; i++) {
            int n = random.nextInt(10) + 0;
            card += Integer.toString(n);
            System.out.print(card.charAt(i));
        }
        return card;
    }

    /**
     * Generate the status of the transaction
     * @return Boolean
     */
    public Boolean randomStatus(){
        return random.nextBoolean();
    }

    /**
     * Generate a random amount
     * @return double
     */
    public double randomAmount() {
        return  random.nextDouble();
    }

    /**
     *  Generate a random transaction type
     * @return transaction type as a String
     */
    public String transactionGenerator() {
        String [] transactions = {"invoice", "withdrawal", "payment","deposit"};
        int transactionSelected = random.nextInt(transactions.length);
        return transactions[transactionSelected];
    }
}
