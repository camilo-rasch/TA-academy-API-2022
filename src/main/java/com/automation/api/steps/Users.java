package com.automation.api.steps;

import com.automation.api.pojo.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import org.apache.log4j.Logger;

import java.util.*;

public class Users {

    private final String endpoint;
    private Response response;
    public Logger log = Logger.getLogger(Users.class);

    /**
     * Constructor.
     * @param uri String
     */
    public Users(String uri) {
        endpoint = uri + "/bank/";
    }

    /**
     * Get users endpoint print.
     */
    public void getUsersAPIEndpoint() {
        log.info(endpoint);
    }

    /**
     * GET Method users/:id.
     * @param id String
     */
    public void getUser(String id) {
        response = given().get(endpoint + id);
        log.info(response.asString());
    }

    /**
     * GET Method users (list of users).
     */
    public void getUsers() {
        response = given().get(endpoint);
    }

    /**
     * POST Method create new user.
     * @param user {@link User}
     */
    public void createUser(User user) {
        response = given().contentType(ContentType.JSON).body(user).when().post(endpoint);

        String message = this.getStatusCode() == 201 ? "User created successfully": "An error " +
                "occurred creating the user";
        log.info(message);
    }

    /**
     * PUT Method update job title of a user.
     * @param id String
     * @param accountNumber String
     */
    public void updateUser(String id, String accountNumber) {
        User user = new User();
        user.setAccountNumber(accountNumber);
        response = given().contentType(ContentType.JSON).body(user).when().put(endpoint + id);
    }

    /**
     * DELETE Method, delete user by id.
     * @param id String
     */
    public void deleteUser(String id) {
        response = given().delete(endpoint + id);
        log.info(this.getStatusCode());
    }

    public void deleteAllUsers(){
        List<User> users = this.getUsersList();
        for (User user: users){
            deleteUser(user.getId());
        }
    }

    /**
     * get response status code.
     * @return status code int
     */
    public int getStatusCode() {
        return response.getStatusCode();
    }

    /**
     * Print list of users.
     */
    public List<User> getUsersList() {
        this.getUsers();
        List<User> users = new ArrayList<>();
        try {
            users = response.then().extract().response().jsonPath().getList("$", User.class);
        }
        catch (Exception ignore){
            // it's an empty list
             }
        return users;
    }


    public boolean hasDuplicatedEmails(){
        List<User> users = this.getUsersList();
        Set<String> emails = new HashSet<>();
        for (User user: users){
            String email = user.getEmail();
            if (emails.contains(email)){return true;}
            emails.add(email);
        }
        return emails.size() == users.size() ? false: true;

    }

    /**
     * Find first user with the name.
     * @param accountNumber String accountNumber
     * @return string with the id
     */
    public String getUserID(String accountNumber) {
        List<User> users = response.then().extract().response().jsonPath().getList("$", User.class);

        Optional<User> id = users.stream().filter(user ->
                        accountNumber.equals(user.getAccountNumber())).findFirst();

        if (id.isPresent())
            return id.get().getId();
        else
            return "";
    }
    /*public String getUserID(String name) {
        List<User> users = response.then().extract().response().jsonPath().getList("$", User.class);

        Optional<User> id = users.stream().filter(user ->
                name.equals(user.getFirst_name() + " " + user.getLast_name()))
                .findFirst();

        if (id.isPresent())
            return id.get().getId();
        else
            return "";
    }*/


    /**
     * Get last id in the list.
     * @return sting with the id
     */
    public String getLastId() {
        List<User> users =response.then().extract().response().jsonPath().getList("$", User.class);

        return users.get(users.size() - 1).getId();
    }

    /**
     * get user response.
     * @return User
     */
    public User getUserResponse() {
        return response.then().extract().as(User.class);
    }
}
