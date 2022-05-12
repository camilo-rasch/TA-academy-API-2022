package com.automation.api;

public interface IApiAction<T>
{
    void getApiEndpoint();
    void getObject(String id);
    void getObjects();
    void createObject(T t);
    void deleteObject(String id);
    int getStatusCode();
    void showActualObjectList();
    String getLastId();

    T getObjectResponse();
}
