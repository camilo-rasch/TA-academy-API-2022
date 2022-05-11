package com.automation.api.steps;

import com.automation.api.pojo.BasePojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * POST method. Create an object
     * @param object Object
     */
    public void postRequest(Object object){
        response = given().contentType(ContentType.JSON).body(object).when().post(endpoint);
    }

    /**
     * UPDATE method. Update account number in transaction
     * @param id String
     * @param object Object
     */
    public void updateRequest(String id, Object object){
        response = given().contentType(ContentType.JSON).body(object)
                .when().put(endpoint + id);
    }

    /**
     * Get last transaction id in the endpoint.
     * @return String
     */
    public String getLastId(){
        List<BasePojo> objects = response.then().extract().response()
                .jsonPath().getList("$", BasePojo.class);

        return objects.get(objects.size() - 1).getId();
    }

    /**
     * Delete all records in endpoint.
     *
     */
    public void deleteEndpoint(){
        getRequest();
        log.info("Status code:" + getStatusCode());

        if (getStatusCode() == 200){
            List<BasePojo> objects = response.then().extract().response()
                    .jsonPath().getList("$", BasePojo.class);

            List<String> objectsId = new ArrayList<>();
            objects.forEach(object-> objectsId.add(object.getId()));
            for (String id : objectsId) {
                deleteRequest(id);
            }
        }
    }

    /**
     * Get response as a String
     * @return String
     */
    public String getResponseAsString(){
        return response.then().extract().asString();
    }

    public abstract Object getObjectResponse();
}
