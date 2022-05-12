# API Automation
  
INSTALL FRAMEWORK

1. git clone https://github.com/camilo-rasch/TA-academy-API-2022
2. mvn clean install -DskipTests
3. mvn eclipse:eclipse or mvn idea:idea

# STEPS TO RUN 

- Run suiteBankTest.xml file to run all the test.

# TESTS 

Test 1

- Test will execute the API and check if there is content as a response, if there is a body as a response the test will delete all the data of the endpoint

Test 2

- Test will create 10 users with all the necessary data, and then will post all the data in the endpoint

Test 3

- Test will verify if there are duplicated emails.

Test 4

- Test will update a users account number
