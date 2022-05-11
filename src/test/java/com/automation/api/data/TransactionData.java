package com.automation.api.data;

import java.util.Random;

public class TransactionData
{
    private static Random random;
    private static final String[]names;
    private static final String[]lastNames;

    public static String ID;
    public static String FIRST_NAME;
    public static String LAST_NAME;
    public static float AMOUNT;
    public static String TRANSACTION_TYPE;
    public static String EMAIL;
    public static boolean ACTIVE;
    public static String COUNTRY;
    public static String TELEPHONE;
    public static String ACCOUNT_NUMBER;


    static {
        random = new Random();
        names = new String[]{"Juan", "Carlos", "Daniela", "Sara"};
        lastNames = new String[]{"Perez", "Argüelles", "Herandez", "Ardila"};

        ID = "0";
        AMOUNT = 0;
        TRANSACTION_TYPE = EMAIL = COUNTRY = TELEPHONE = ACCOUNT_NUMBER = "";
        ACTIVE = true;
    }

    public static void generateFirstName()
    {
        FIRST_NAME = names[random.nextInt(4)];
    }

    public static void generateLastName()
    {
        LAST_NAME = lastNames[random.nextInt(4)];
    }
    public static void generateAmount()
    {
        AMOUNT = (float)(random.nextInt(1000));
    }

}
