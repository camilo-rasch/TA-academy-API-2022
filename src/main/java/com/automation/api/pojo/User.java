package com.automation.api.pojo;


/**
 * Class that represent a response after a get request
 * @author eliecer.martinez@globant.com
 */
public class User {

    private String id;
    private String name;
    private String lastName;
    private String email;
    private String country;
    private String telephone;
    private String accountNumber;
    private String transactionType;
    private float amount;
    private boolean active;
    private String jobTitle;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Constructor.
     */
    public User() {}

    /**
     * Constructor.
     * @param id String
     * @param name String
     * @param lastName String
     * @param email String
     * @param country String
     * @param telephone String
     * @param active boolean
     * @param jobTitle String
     */
    public User(String id, String name, String lastName, String accountNumber, float amount, String transactionType,
                String email, boolean active, String country, String telephone, String jobTitle) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.email = email;
        this.country = country;
        this.telephone = telephone;
        this.active = active;
        this.jobTitle = jobTitle;
    }

    /**
     * Constructor.
     * @param name String
     * @param lastName String
     * @param email String
     * @param country String
     * @param telephone String
     * @param active boolean
     * @param jobTitle String
     */
    public User(String name, String lastName, String accountNumber, float amount, String transactionType,
                String email, boolean active, String country, String telephone, String jobTitle) {
        this.name = name;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.email = email;
        this.country = country;
        this.telephone = telephone;
        this.active = active;
        this.jobTitle = jobTitle;
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
     * Get first name.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Set first name.
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get last name.
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
     * Get email.
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
     * Get country.
     * @return String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set country.
     * @param country String
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Get telephone.
     * @return String
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Set telephone
     * @param telephone String
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Get active.
     * @return boolean
     */
    public boolean getActive() {
        return active;
    }

    /**
     * Set active.
     * @param active boolean
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Get job title.
     * @return String
     */
    public String getJobTitle() {
        return jobTitle;
    }

    /**
     * Set job title.
     * @param job_title String
     */
    public void setJobTitle(String job_title) {
        this.jobTitle = job_title;
    }

    /**
     * Override method toSting object.
     * @return String
     */
    @Override
    public String toString() {
        return "\nUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", last_name='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", telephone='" + telephone + '\'' +
                ", active='" + active + '\'' +
                ", job_title='" + jobTitle + '\'' +
                '}';
    }
}
