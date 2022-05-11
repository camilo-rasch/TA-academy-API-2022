package com.automation.api.data;

import java.util.Random;

public class TransactionData
{
    private static Random random;
    private static final String[]names;
    private static final String[]lastNames;

    public static float amount;

    static {
        random = new Random();
        names = new String[]{"Juan", "Carlos", "Daniela", "Sara"};
        lastNames = new String[]{"Perez", "Argüelles", "Herandez", "Ardila"};

        amount = 0;
    }

    public static void generateAmount()
    {
        amount = (float)(random.nextInt(1000));
    }
}
