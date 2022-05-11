package com.automation.api.pojo;

/**
 * Class that represent a response after a get request
 * @author luis.pineda@globant.com
 */
public class BankTransaction {

    private String id;
    private String name;
    private String last_name;
    private String account_number;
    private String transaction_type;
    private String email;
    private boolean active;
    private String country;
    private String telephone;


    /**
     * Constructor.
     */
    public BankTransaction(){
        super();
    }

    /**
     * Complete Constructor.
     * @param id String
     * @param name String
     * @param last_name String
     * @param account_number String
     * @param transaction_type String
     * @param email String
     * @param active boolean
     * @param country String
     * @param telephone String
     */
    public BankTransaction(String id, String name, String last_name, String account_number, String transaction_type,
                           String email, boolean active, String country, String telephone) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
        this.account_number = account_number;
        this.transaction_type = transaction_type;
        this.email = email;
        this.active = active;
        this.country = country;
        this.telephone = telephone;
    }

    /**
     * Complete Constructor without id.
     * @param name String
     * @param last_name String
     * @param account_number String
     * @param transaction_type String
     * @param email String
     * @param active boolean
     * @param country String
     * @param telephone String
     */
    public BankTransaction(String name, String last_name, String account_number, String transaction_type,
                           String email, boolean active, String country, String telephone) {
        this.name = name;
        this.last_name = last_name;
        this.account_number = account_number;
        this.transaction_type = transaction_type;
        this.email = email;
        this.active = active;
        this.country = country;
        this.telephone = telephone;
    }

    /**
     * Minimal Requirements Constructor.
     * @param id String
     * @param last_name String
     * @param account_number String
     * @param transaction_type String
     * @param email String
     */
    public BankTransaction(String id, String last_name, String account_number, String transaction_type, String email) {
        this.id = id;
        this.last_name = last_name;
        this.account_number = account_number;
        this.transaction_type = transaction_type;
        this.email = email;
    }

    /**
     * Get id.
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Set id.
     * @param id String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get name.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get last name.
     * @return String
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Set the last name.
     * @param last_name String
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Get the account number.
     * @return String
     */
    public String getAccount_number() {
        return account_number;
    }

    /**
     * Set the account number.
     * @param account_number String
     */
    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    /**
     * Get the transaction type.
     * @return String
     */
    public String getTransaction_type() {
        return transaction_type;
    }

    /**
     * Set the transaction type.
     * @param transaction_type String
     */
    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    /**
     * Get the email.
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email.
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the active status.
     * @return boolean
     */
    public boolean getActive() {
        return active;
    }

    /**
     * Set the active status.
     * @param active boolean
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Get the country.
     * @return String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the country.
     * @param country String
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get the telephone.
     * @return String
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Set the telephone.
     * @param telephone String
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Override method toSting object.
     * @return String
     */
    @Override
    public String toString() {
        return "BankTransaction{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", account_number='" + account_number + '\'' +
                ", transaction_type='" + transaction_type + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", country='" + country + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
