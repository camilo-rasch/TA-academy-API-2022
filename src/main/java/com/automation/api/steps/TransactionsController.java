package com.automation.api.steps;

import com.automation.api.pojo.User;
import com.automation.api.pojo.UserTransaction;
import io.restassured.http.ContentType;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static io.restassured.RestAssured.*;

public class TransactionsController {
    private final String endpoint;
    private Response response;
    public Logger log = Logger.getLogger(TransactionsController.class);

    public TransactionsController(String uri) {
        endpoint = uri + "transactions/";
    }
    /**
     * Get users endpoint print.
     */
    public void getAPIEndpoint() {
        log.info(endpoint);
    }
    /**
     * GET Method users (list of transactions).
     */
    public void getTransactions(){
        response = given().get(endpoint);
    }

    /**
     * get List of transactions
     * @return List UserTransactions
     */
    public List<UserTransaction> getListTransactions() {
        return response.then().extract().response().jsonPath().getList("$",UserTransaction.class);
    }

    /**
     * POST Method create new user.
     * @param transaction {@link UserTransaction}
     */
    public void createTransaction(UserTransaction transaction) {
        response = given().contentType(ContentType.JSON).body(transaction).when().post(endpoint);
    }
    /**
     * get response status code.
     * @return status code int
     */
    public int getStatusCode() {
        return response.getStatusCode();
    }

    /**
     * POST Creation a Verified Transaction
     * @param transaction UserTransaction
     * @return variable isVerified that confirms new transaction with unique email
     */
    public boolean createTransactionVerified(UserTransaction transaction){
        boolean isVerified = true;
        getTransactions();
        if(!isDuplicatedEmail(transaction.getEmail()) && checkEmptiness()){
            log.info("Email is not repeated");
            createTransaction(transaction);
        }
        else{
            log.info("Email is already registered, Update it");
            createTransaction(transaction);
            isVerified=false;
        }
        return isVerified;
    }

    /**
     * Method that check duplicated emails among all emails
     * @return boolean that checks duplicated
     */
    public boolean anyDuplicatedEmail(){
        if(!checkEmptiness()){
            Set<String> setEmails= new HashSet<>(getAllEmails());
            return setEmails.size() == getAllEmails().size();
        }
        else{
            return true;
        }
    }

    /**
     * Method gives all emails in List of Strings
     * @return List Emails
     */
    public List<String> getAllEmails(){
        List<String> allEmails = new ArrayList<>();
        List<UserTransaction> transactions= getListTransactions();
        transactions.forEach(transaction -> allEmails.add(transaction.getEmail()) );
        return allEmails;
    }

    /**
     * Method to check one email, before create new transaction
     * @param email String email
     * @return boolean checking a specific duplicated email
     */
    public boolean isDuplicatedEmail(String email){
        AtomicBoolean isDuplicated = new AtomicBoolean(false);
        try{
            List<UserTransaction> transactions = getListTransactions();
            transactions.forEach(transaction -> {
                String emailRegistered = transaction.getEmail();
                if(Objects.equals(emailRegistered, email)){
                    isDuplicated.set(true);
                }
            });
        }
        catch (JsonPathException e) {
            isDuplicated.set(false);
        }
        return isDuplicated.get();
    }

    /**
     * DELETE all Transactions if they exist
     */
    public void deleteAllTransactions(){
        if(response.getStatusCode() == 200){
            List<UserTransaction> transactions = getListTransactions();
            int quantity = transactions.size();
            transactions.forEach(transaction -> response=given().delete(endpoint + transaction.getId()));
            log.info( quantity + " Transactions were deleted");
        }
        else{
            log.info("The Data is already Empty");
        }
    }

    /**
     * PUT update an account number of existing transaction
     * @param id String id
     * @param account int account
     */
    public void updateTransaction(String id, int account) {
        UserTransaction transaction = new UserTransaction();
        transaction.setAccountNumber(account);

        response = given().contentType(ContentType.JSON).body(transaction).when().put(endpoint + id);
    }

    /**
     * GET object by Id
     * @param id  string id
     * @return UserTransaction
     */
    public UserTransaction getTransactionById(String id){
        response = given().get(endpoint+id);
        return response.then().extract().response().jsonPath().getObject("$",UserTransaction.class);
    }

    /**
     * Methods that check emptiness in data transactions
     * @return boolean about Data emptiness
     */
    public boolean checkEmptiness(){
        boolean isEmpty = false;
        if(response.getStatusCode() == 200){
            List<UserTransaction> list = getListTransactions();
            log.info("There are "+list.size()+" transactions registered");
        }
        else if (response.getStatusCode() == 404){
            log.info("There's no Data");
            isEmpty = true;
        }
        return isEmpty;
    }

    /**
     * Method to get last id
     * @return String id
     */
    public String getLastId() {
        List<User> users =response.then().extract().response().jsonPath().getList("$", User.class);

        return users.get(users.size() - 1).getId();
    }

    /**
     * Method that check Status 200 and 404
     * @return boolean
     */
    public boolean checkStatusTransactions(){
        int status = response.getStatusCode();
        boolean goodStatus;
        goodStatus = status == 200 || status == 404;
        return goodStatus;
    }
}
