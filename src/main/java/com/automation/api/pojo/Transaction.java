package com.automation.api.pojo;

/**
 * Class representing a transaction after GET request
 * @author js.lozano
 */
public class Transaction extends BasePojo {

    private String firstName;
    private String lastName;
    private long accountNumber;
    private float amount;
    private String transactionType;
    private String email;
    private boolean active;
    private String country;
    private long telephone;

    /**
     * Constructor
     */
    public Transaction(){}

    /**
     * Constructor
     * @param name String
     * @param lastName String
     * @param accountNumber String
     * @param amount long
     * @param transactionType String
     * @param email String
     * @param active boolean
     * @param country String
     * @param telephone String
     */
    public Transaction(String name, String lastName, long accountNumber, float amount,
                       String transactionType, String email, boolean active, String country,
                       long telephone) {
        this.firstName = name;
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
     * Get name
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set first name
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get last name
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set last name
     * @param lastName String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get account number
     * @return long
     */
    public long getAccountNumber() {
        return accountNumber;
    }

    /**
     * Set account number
     * @param accountNumber long
     */
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Get amount
     * @return float
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Set amount
     * @param amount float
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Get transaction type
     * @return String
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Set transactyon type
     * @param transactionType String
     */
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Get email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get active status
     * @return boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set active
     * @param active boolean
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Get country
     * @return String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set country
     * @param country String
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get telephone
     * @return String
     */
    public long getTelephone() {
        return telephone;
    }

    /**
     * Set telephone
     * @param telephone String
     */
    public void setTelephone(long telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", accountNumber=" + accountNumber +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", country='" + country + '\'' +
                ", telephone=" + telephone +
                '}';
    }
}

