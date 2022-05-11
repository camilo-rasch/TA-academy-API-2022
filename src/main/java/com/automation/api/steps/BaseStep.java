package com.automation.api.steps;

import io.restassured.response.Response;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public abstract class BaseStep {

    protected final String endpoint;
    protected Response response;
    protected Logger log = Logger.getLogger(BaseStep.class);

    public BaseStep(String uri){
        endpoint = uri;
    }

    /**
     * get response status code.
     * @return status code int
     */
    public int getStatusCode() {
        return response.getStatusCode();
    }

    /**
     * GET method to get a list of objects
     */
    public void getRequest(){
        response = when().get(endpoint);
    }

    /**
     * GET method to get a specific object by its id
     * @param id String
     */
    public void getRequest(String id){
        response = when().get(endpoint + id);
    }

    /**
     * DELETE method. Delete object by id
     * @param id String
     */
    public void deleteRequest(String id){
        response = when().delete(endpoint + id);
    }

    public abstract void deleteEndpoint();
}
