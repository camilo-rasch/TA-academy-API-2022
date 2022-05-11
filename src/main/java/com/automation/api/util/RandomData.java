package com.automation.api.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomData {

    static List<String> emailsGenerated = new ArrayList<>();
    private static Random random = new Random();
    private final String[] names = {"jose", "luis", "camilo", "pablo", "carlos"};
    private final String[] lastNames = {"tarazona", "florez", "quintero", "rodriguez", "garcia"};
    private final String[] transactionType = {"deposit", "invoice", "withdrawal", "payment", "credit card"};
    private final String[] country = {"colombia", "mexico", "spain", "germany", "turkey"};
    private String name;
    private String lastName;

    public int getRandomIndex() {
        return random.nextInt(5 + 0) + 0;
    }

    public String getRandomName() {
        this.name = names[getRandomIndex()];
        return this.name;
    }

    public String getRandomLastName() {
        this.lastName = lastNames[getRandomIndex()];
        return this.lastName;
    }

    public String getRandomAccountNumber() {
        return String.valueOf(random.nextInt(99999999 + 10000000) + 10000000);
    }

    public String getRandomAmount() {
        return String.valueOf(random.nextInt(9999 + 1000) + 1000);
    }

    public String getRandomTransactionType() {
        return transactionType[getRandomIndex()];
    }

    public String getRandomEmail() {
        String email = this.name + "." + this.lastName + getRandomIndex() + getRandomIndex() + getRandomIndex() + "@gmail.com";
        if (emailsGenerated.contains(email)) {
            this.getRandomEmail();
        } else {
            emailsGenerated.add(email);
        }
        return email;
    }

    public boolean getRandomActive() {
        return random.nextBoolean();
    }

    public String getRandomCountry() {
        return country[getRandomIndex()];
    }

    public String getRandomTelephone() {
        return String.valueOf(random.nextInt(999999999 + 100000000) + 100000000);
    }


}