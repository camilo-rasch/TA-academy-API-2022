# API Automation

## Daniel Gonzalez

### Changes:

1. BankUser, this is the POJO used for the testing
2. AccountType, this is an enum for the account_type of the POJO (account type was used instead of transaction type)
3. BankApiBase, here there are the base methods used for the REST assured
4. Data, a new data provider was created, but it was only using for initial tests, later it was replaced by the data provider of a random user in RandomData
5. RandomData, a single data provider is present here that obtains random fields for its creation
6. BankTest, here you can find all the test created for this exercise

### Steps to run the application:

1. To run the application, please run the DanielSuite.xml
2. In DanielSuite.xml, you can change two parameters the "id" and the "account number"

### Problems being solved:

1. Right now the test can only be run once, since after the last test the database is deleted
2. Sometimes the method that deletes the whole database returns the error 429 this happens because the test is deleting individually all the users in the database
