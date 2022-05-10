package com.automation.api.steps;

import io.restassured.response.Response;
import org.apache.log4j.Logger;

public class BankTransactions {

    private final String endpoint;
    private Response response;
    public Logger log = Logger.getLogger(Users.class);

    /**
     * Constructor.
     * @param base_url String
     */
    public BankTransactions(String base_url) {
        endpoint = base_url + "/v1/users/";
    }
}
