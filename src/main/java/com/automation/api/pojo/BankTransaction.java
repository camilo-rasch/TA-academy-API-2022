package com.automation.api.pojo;

import com.automation.api.util.RandomData;

/**
 * Class that represent a response after a get request
 *
 * @author jose.tarazona
 */
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

    /**
     * Constructor with random data from util.RandomData.java
     */
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


    /**
     * Constructor
     *
     * @param name
     * @param lastName
     * @param accountNumber
     * @param amount
     * @param transactionType
     * @param email
     * @param active
     * @param country
     * @param telephone
     */
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

    /**
     * Get id
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Get name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get lastName
     *
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get accountNumber
     *
     * @return String
     */
    public String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Set accountNumber
     *
     * @param accountNumber
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Get amount
     *
     * @return String
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Get transactionType
     *
     * @return String
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Get email
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get active
     *
     * @return Boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Get country
     *
     * @return String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Get telephone
     *
     * @return String
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Override method toSting object.
     *
     * @return String
     */
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
