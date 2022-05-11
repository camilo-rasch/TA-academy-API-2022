package com.automation.api.data;

import com.automation.api.pojo.UserTransaction;
import org.testng.annotations.DataProvider;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DataGenerator {
    @DataProvider(name = "transactions")
    public Object[][] inputData(){
        UserTransaction pipelon = new UserTransaction(getRandomText(7),getRandomText(8),
                getRandomNumber(9),getRandomNumber(4),getRandomType(),getRandomEmail(),
                true, "Colombia", getRandomPhone());
        return new Object[][] {{pipelon}};
    }
    public String getRandomText(int quantity){
        StringBuilder text = new StringBuilder();
        for(int i = 0; i<quantity;i++){
            text.append((char) getCodeASCII());
        }
        return text.toString();
    }
    public String getRandomEmail(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String strDate = dateFormat.format(date);
        return getRandomText(8)+strDate+"@"+getRandomText(4)+".com";
    }

    public int getRandomNumber(int quantity){
        int decimal = (int) Math.pow(10,quantity);
        return (int)(Math.random()*decimal);
    }
    public String getRandomPhone(){
        return "+"+getRandomNumber(2) + " " + getRandomNumber(9);
    }

    public int getCodeASCII(){
        Random rand = new Random();
        return 97 + rand.nextInt((122-97)+1);
    }

    public String getRandomType(){
        String type = null;
        int random = getRandomNumber(1);

        return "Payment";
    }

}
