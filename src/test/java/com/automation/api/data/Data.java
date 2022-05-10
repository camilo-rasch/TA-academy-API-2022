package com.automation.api.data;

import com.automation.api.pojo.Transaction;
import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Random;

public class Data {

    Random random = new Random();

    @DataProvider(name = "transactions")
    public Object[][] inputData() {
        HashMap<String, Transaction> inputData = new HashMap<>();
        for (int i = 0;i<10;i++){
            inputData.put(String.format("%01d",i),new Transaction(fakeDataString(), fakeDataString(), fakeNumber(), fakeNumber(),
                    fakeTransactionType(), fakeDataString()+"@fake.com",fakeStatus(), fakeDataString(), fakeNumber()));
        }
        return new Object[][] {{inputData}};
    }

    public String fakeDataString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public Boolean fakeStatus(){
        return random.nextBoolean();
    }

    public String fakeNumber(){
        int randomNumber = random.nextInt(99999999);
        return String.format("%08d",randomNumber);
    }

    public String fakeTransactionType(){
        String[] transactionsTypes = {"payment", "withdrawal", "deposit","invoice"};
        int randomChoice = random.nextInt(transactionsTypes.length);
        return transactionsTypes[randomChoice];

    }
}
