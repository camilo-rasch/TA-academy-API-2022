package com.automation.api.pojo;

public class Transaction
{
    private String id;
    private String first_name;
    private String last_name;
    private float amount;
    private String transaction_type;
    private String email;
    private boolean active;
    private String country;
    private String telephone;
    private String account_number;

    /**
     * Constructor.
     */
    public Transaction(){}

    /**
     * Constructor.
     * @param id String
     * @param firstName String
     * @param lastName String
     * @Param amount float
     * @param transactionType String
     * @param email String
     * @Param active boolean
     * @param country String
     * @param telephone String
     * @Param accountNumber String

     */
    public Transaction(String id, String firstName, String lastName, float amount, String transactionType, String email, boolean active, String country, String telephone, String accountNumber) {
        this.id = id;
        this.first_name = firstName;
        this.last_name = lastName;
        this.amount = amount;
        this.transaction_type = transactionType;
        this.email = email;
        this.active = active;
        this.country = country;
        this.telephone = telephone;
        this.account_number = accountNumber;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", firstName='" + first_name + '\'' +
                ", secondName='" + last_name + '\'' +
                ", amount=" + amount +
                ", transactionType='" + transaction_type + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", country='" + country + '\'' +
                ", telephone='" + telephone + '\'' +
                ", accountNumber='" + account_number + '\'' +
                '}';
    }
}
