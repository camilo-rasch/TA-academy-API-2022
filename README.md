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

### Test 1
  
The endpoint is validated with a response of 200 or 404, whether the endpoint has Data or not, then the existing transactions are deleted if they exist, and finally it is verified that there is no Data.

### Test 2
  
I initialize 10 random data through a dataProvider, with this I make use of the POJO class as the structure of the dataProvider, then I create a transaction that in turn verifies that there is no repeated email and verifies its response code.

To avoid duplicate emails, a string generator is used and concatenated with a date to reduce the probability of an email being repeated.

### Test 3
  
First, I call the endpoint to bring all the emails and convert this list into a new set to avoid duplicates, with this, I differentiate lengths, but before this, we verify that the endpoint is not empty.  

### Test 4
  
To update an existing account number, in this case the last of the records was chosen, and passing by parameter the account number to be updated, on the other hand, an assertion was made to verify that there's Data.
