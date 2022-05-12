package com.automation.api.pojo;

import groovy.transform.builder.Builder;

@Builder
public class BankUser {

    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String country;
    private String telephone;
    private boolean active;
    private String accountNumber;
    private double amount;
    private String transactionType;


    /**
     * Contructor
     */
    public BankUser(){}


    /**
     * Bank user Constructor
     *   @param first_name String
     *   @param last_name String
     *   @param email String
     *   @param country String
     *   @param telephone String
     *   @param active boolean
     *   @param accountNumber String
     *   @param amount float
     *   @param transactionType String
     */

    public BankUser(String first_name, String last_name, String email, String country, String telephone, boolean active, String accountNumber, double amount, String transactionType){
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.country = country;
        this.telephone = telephone;
        this.active = active;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Override method toSting object.
     * @return String
     */
    @Override
    public String toString() {
        return "\nUser{" +
                "id='" + id + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", telephone='" + telephone + '\'' +
                ", active='" + active + '\'' +
                ", account_number='" + accountNumber + '\'' +
                ", amount='" + amount + '\'' +
                ", transaction_type= '" + transactionType + '\'' +
                '}';
    }
}
