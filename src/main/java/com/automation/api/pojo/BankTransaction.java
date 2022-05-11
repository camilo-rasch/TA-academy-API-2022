package com.automation.api.pojo;

import com.automation.api.util.RandomData;

public class BankTransaction {


    private String id;
    private String name;
    private String lastName;
    private String accountNumber;
    private String amount;
    private String transactionType;
    private String email;
    private boolean active;
    private String country;
    private String telephone;

    public BankTransaction() {
        RandomData rd = new RandomData();
        this.name = rd.getRandomName();
        this.lastName = rd.getRandomLastName();
        this.accountNumber = rd.getRandomAccountNumber();
        this.amount = rd.getRandomAmount();
        this.transactionType = rd.getRandomTransactionType();
        this.email = rd.getRandomEmail();
        this.active = rd.getRandomActive();
        this.country = rd.getRandomCountry();
        this.telephone = rd.getRandomTelephone();
    }


    public BankTransaction(String name, String lastName, String accountNumber, String amount, String transactionType, String email, boolean active, String country, String telephone) {
        this.name = name;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.email = email;
        this.active = active;
        this.country = country;
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public String getCountry() {
        return country;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount='" + amount + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", country='" + country + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
