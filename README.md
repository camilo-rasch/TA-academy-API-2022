# API Automation
  
INSTALL FRAMEWORK

1. git clone https://github.com/camilo-rasch/TA-academy-API-2022
2. mvn clean install -DskipTests
3. mvn eclipse:eclipse or mvn idea:idea

Create new branch

3. git checkout -b "name-branch"
4. git add .
5. git commit -m "mensaje"
6. git push origin "name-branch"

# Final Exercise 

### Test 1

First, the endpoint is validated with the 404 response message, the method in charge of this is a boolean.

Depending on the response, the test checks the response of the method, if it is empty it ends execution, otherwise it deletes all the data from the endpoint, and finally it checks again if the endpoint is empty.

### Test 2

The maven JavaFaker repository was used to generate 10 random transactions, therefore in this test a duplicate email check was not carried out since it guaranteed that there are no duplicate emails.

### Test 3

In this test, we bring all the transactions and through a function of the stream class within a boolean method, the test checks that there are no duplicate emails.

### Test 4

In this test, a transaction is inserted which is going to be updated; later it is searched, in case of not being found it will give the message of transaction not found.

If found, the test successfully updates the email and phone number of the transaction.
