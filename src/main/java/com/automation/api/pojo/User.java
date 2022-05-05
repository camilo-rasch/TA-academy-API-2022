package com.automation.api.pojo;


/**
 * Class that represent a response after a get request
 * @author eliecer.martinez@globant.com
 */
public class User {

    private String id;
    private String first_name;
    private String last_name;
    private String email;
    private String country;
    private String telephone;
    private boolean active;
    private String job_title;

    /**
     * Constructor.
     */
    public User() {}

    /**
     * Constructor.
     * @param id String
     * @param first_name String
     * @param last_name String
     * @param email String
     * @param country String
     * @param telephone String
     * @param active boolean
     * @param job_title String
     */
    public User(String id, String first_name, String last_name, String email,
                String country, String telephone, boolean active, String job_title) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.country = country;
        this.telephone = telephone;
        this.active = active;
        this.job_title = job_title;
    }

    /**
     * Constructor.
     * @param first_name String
     * @param last_name String
     * @param email String
     * @param country String
     * @param telephone String
     * @param active boolean
     * @param job_title String
     */
    public User(String first_name, String last_name, String email,
                String country, String telephone, boolean active, String job_title) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.country = country;
        this.telephone = telephone;
        this.active = active;
        this.job_title = job_title;
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
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Set first name.
     * @param first_name String
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Get last name.
     * @return String
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Set last name
     * @param last_name String
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
        return job_title;
    }

    /**
     * Set job title.
     * @param job_title String
     */
    public void setJobTitle(String job_title) {
        this.job_title = job_title;
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
                ", job_title='" + job_title + '\'' +
                '}';
    }
}
