package com.automation.api.pojo;

public class BankTransaction {

    private String id;
    private String name;
    private String lastName;
    private String accountNumber;
    private double amount;
    private String transactionType;
    private String email;
    private boolean active;
    private String country;
    private String telephone;

    /**
     * Empty constructor.
     */
    public BankTransaction() {}

    /**
     * Normal constructor.
     * @param id A unique id of the transaction
     * @param name First name of the bank account owner
     * @param lastName Last name of the bank account owner
     * @param accountNumber Bank account number
     * @param amount Transferred or withdrawn money amount
     * @param transactionType Type of transaction
     * @param email Email address of the bank account owner
     * @param active Whether the bank account is active or inactive
     * @param country Residence country of the bank account owner
     * @param telephone Phone number of the bank account owner
     */
    public BankTransaction(String id, String name, String lastName, String accountNumber, double amount,
                           String transactionType, String email, boolean active, String country, String telephone) {
        this.id = id;
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
     * Get id.
     * @return The unique id of the transaction
     */
    public String getId() {
        return id;
    }

    /**
     * Set id.
     * @param id New id value
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get bank account owner's first name.
     * @return First name of the bank account owner
     */
    public String getName() {
        return name;
    }

    /**
     * Set bank account owner's first name.
     * @param name New name value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get bank account owner's last name.
     * @return Last name of the bank account owner
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the bank account owner's last name.
     * @param lastName New lastName value
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the bank account number.
     * @return Bank account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Set the bank account number.
     * @param accountNumber New accountNumber value
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Get the money amount used in the transaction.
     * @return Money amount registered in the transaction
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Set the transaction money amount.
     * @param amount New amount value
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Get the type of transaction.
     * @return Transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Set the type of transaction.
     * @param transactionType New transactionType value
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Get the bank account owner's email address.
     * @return Email address of the bank account owner
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the bank account owner's email address.
     * @param email New email value.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the account status.
     * @return Whether the account is active or inactive
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set the status of the bank account.
     * @param active New active value
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Get the bank account owner's residence country.
     * @return Bank account owner's country of residence
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the bank account owner's residence country.
     * @param country New country value
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get the bank account owner's telephone number.
     * @return Phone number of the bank account owner
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Set the bank account owner's telephone number.
     * @param telephone New telephone value
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", country='" + country + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
