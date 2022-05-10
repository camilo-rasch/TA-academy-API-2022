package com.automation.api.pojo;

/**
 * Class that represent a response after a get request
 * @author diego Cabulo
 */
public class Transaction {

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
     * Constructor
     */
    public  Transaction(){}

    /**
     * Constructor
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
    public Transaction(String name, String lastName, String accountNumber, String amount, String transactionType,
                       String email, boolean active, String country, String telephone) {
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
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Set id
     * @param id String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get name of user
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set name of user
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get lastname
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set lastname
     * @param lastName String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get transaction type
     * @return String
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Set Transaction type
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
     * Get if is active
     * @return boolean
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set if is active
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
     * Set Country
     * @param country String
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get Telephone
     * @return String
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Set Telephone
     * @param telephone String
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Get Amount
     * @return String
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Set Amount
     * @param amount String
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Get Account Number
     * @return String
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Set Account Number
     * @param accountNumber String
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Override method toString object
     * @return String
     */
    @Override
    public String toString() {
        return "\nTransaction{" +
                " name='" + name + '\'' +
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
