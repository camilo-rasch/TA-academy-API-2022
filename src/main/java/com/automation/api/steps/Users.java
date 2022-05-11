package com.automation.api.steps;

import com.automation.api.pojo.User;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Optional;

public class Users extends BaseStep{

    /**
     * Constructor.
     * @param uri String
     */
    public Users(String uri) {
        super(uri + "/v1/users/");
    }

    /**
     * Get users endpoint print.
     */
    public void getUsersAPIEndpoint() {
        log.info(endpoint);
    }

    /**
     * PUT Method update job title of a user.
     * @param id String
     * @param jobTitle String
     */
    public void updateUser(String id, String jobTitle) {
        User user = new User();
        user.setJobTitle(jobTitle);

        response = given().contentType(ContentType.JSON).body(user).when().put(endpoint + id);
    }

    /**
     * Print list of users.
     */
    public void showActualUsersList() {
        List<User> users = response.then().extract().response().jsonPath().getList("$", User.class);
        log.info(users);
    }

    /**
     * Find first user with the name.
     * @param name String name
     * @return sting with the id
     */
    public String getUserID(String name) {
        List<User> users = response.then().extract().response().jsonPath().getList("$", User.class);

        Optional<User> id = users.stream().filter(user ->
                name.equals(user.getFirst_name() + " " + user.getLast_name()))
                .findFirst();

        if (id.isPresent())
            return id.get().getId();
        else
            return "";
    }

    /**
     * get user response.
     * @return User
     */
    @Override
    public User getObjectResponse() {
        return response.then().extract().as(User.class);
    }

}
