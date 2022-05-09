package com.automation.api.steps;

import io.restassured.response.Response;

public class BaseStep {

    protected final String endpoint;
    protected Response response;

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


}
