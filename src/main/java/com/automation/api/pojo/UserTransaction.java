package com.automation.api.pojo;
/**
 * Class that represent a response after a get request
 * @author juanfelipe.silva@globant.com
 */
public class UserTransaction {
    private String id;
    private String name;
    private String lastName;
    private int accountNumber;
    private int amount;
    private String transactionType;
    private String email;
    private boolean active;
    private String country;
    private String telephone;


    public UserTransaction( String name, String lastName, int accountNumber,
                           int amount, String transactionType, String email, boolean active,
                           String country, String telephone) {
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

    public UserTransaction() {

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

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
    }

    public int getAmount() {
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
}
