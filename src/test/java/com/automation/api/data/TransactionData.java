package com.automation.api.data;

import com.automation.api.pojo.Transaction;
import org.testng.annotations.DataProvider;

import java.util.Random;

public class TransactionData
{
    public enum NUMBER_TYPE
    {
        PHONE_NUMBER, ACCOUNT_NUMBER
    }
    private static Random random;
    private static final String[] firstNames;
    private static final int firstNameBound;
    private static final String[]lastNames;
    private static final int lastNameBound;
    private static final String[]transactionTypes;
    private static final int transactionTypeBound;

    private static final String[]countries;
    private static final int countriesBound;

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
        firstNames = new String[]{"Juan", "Carlos", "Daniela", "Sara"};
        firstNameBound = 4;
        lastNames = new String[]{"Perez", "Argüelles", "Herandez", "Ardila"};
        lastNameBound = 4;
        transactionTypes = new String[]{"Invoice", "Withdrawal", "Payment", "Deposit"};
        transactionTypeBound = 4;
        countries = new String[]{"Colombia", "Russia", "United States", "Germany"};
        countriesBound = 4;

        ID = "0";
        AMOUNT = 0;
        TRANSACTION_TYPE = EMAIL = COUNTRY = TELEPHONE = ACCOUNT_NUMBER = "";
        ACTIVE = true;
    }

    @DataProvider(name = "transactions")
    public Object[][]generatedData()
    {
        Transaction t1 = createTransaction();
        Transaction t2 = createTransaction();
        Transaction t3 = createTransaction();
        Transaction t4 = createTransaction();
        Transaction t5 = createTransaction();
        Transaction t6 = createTransaction();
        Transaction t7 = createTransaction();
        Transaction t8 = createTransaction();
        Transaction t9 = createTransaction();
        Transaction t10 = createTransaction();

        return new Object[][]{{t1},{t2}, {t3}, {t4}, {t5}, {t6}, {t7}, {t8}, {t9}, {t10}};
    }

    private static Transaction createTransaction()
    {
        int temporalIndex = Integer.parseInt(ID);
        ID = Integer.toString(temporalIndex++);
        generateFirstName();
        generateLastName();
        generateAmount();
        generateTransactionType();
        generateEmail();
        generateCountry();
        generateMutableNumber(NUMBER_TYPE.PHONE_NUMBER);
        generateMutableNumber(NUMBER_TYPE.ACCOUNT_NUMBER);
        return new Transaction(ID, FIRST_NAME, LAST_NAME, AMOUNT, TRANSACTION_TYPE,
                EMAIL, ACTIVE, COUNTRY, TELEPHONE, ACCOUNT_NUMBER);
    }

    public static void generateFirstName()
    {
        FIRST_NAME = firstNames[random.nextInt(firstNameBound)];
    }

    public static void generateLastName()
    {
        LAST_NAME = lastNames[random.nextInt(lastNameBound)];
    }
    public static void generateAmount()
    {
        AMOUNT = (float)(random.nextInt(1000));
    }

    public static void generateTransactionType()
    {
        TRANSACTION_TYPE = transactionTypes[random.nextInt(transactionTypeBound)];
    }

    public static void generateEmail()
    {
        StringBuilder email = new StringBuilder();

        for(int i = 0; i < 9; i++)
            email.append((char)(random.nextInt(26)+ 'a'));

        email.append("@yopmail.com");
        EMAIL = email.toString();
    }

    public static void generateCountry()
    {
        COUNTRY = countries[random.nextInt(countriesBound)];
    }

    public static void generateMutableNumber(NUMBER_TYPE type)
    {
        StringBuilder number = new StringBuilder();


        for(int i = 0; i < 9; i++)
            number.append((random.nextInt(9)));

        if(type == NUMBER_TYPE.PHONE_NUMBER)
        TELEPHONE = "(+"+random.nextInt(80)+") "+number.toString();
        else
            ACCOUNT_NUMBER = number.toString();
    }

}
