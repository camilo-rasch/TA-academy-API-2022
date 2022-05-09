package com.automation.api.data;

import com.automation.api.pojo.BankUser;
import com.automation.api.pojo.enums.AccountType;
import org.testng.annotations.DataProvider;

import java.util.Random;

public class RandomData {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @DataProvider(name = "randomBankUserDataProvider")
    public Object[][] randomBankUserDataProvider() {
        BankUser jaimito = BankUser.builder()
                .first_name(randomTextGenerator(5))
                .last_name(randomTextGenerator(7))
                .account_number(randomAccountNumberGenerator())
                .amount(randomAmountGenerator())
                .account_type(randomAccountType())
                .email(randomTextGenerator(9) + "@gmail.com")
                .active(true)
                .country("Colombia")
                .telephone(randomPhoneNumber())
                .build();

        return new Object[][] {{jaimito}};
    }

    public BankUser RandomBankUser() {
        return BankUser.builder()
                .first_name(randomTextGenerator(5))
                .last_name(randomTextGenerator(7))
                .account_number(randomAccountNumberGenerator())
                .amount(randomAmountGenerator())
                .account_type(randomAccountType())
                .email(randomTextGenerator(9) + "@gmail.com")
                .active(true)
                .country("Colombia")
                .telephone(randomPhoneNumber())
                .build();
    }

    private String randomTextGenerator(int textLength) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder(textLength);
        for (int i = 0; i < textLength; i++) {
            builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return builder.toString();
    }

    private String randomAccountNumberGenerator() {
        Random random = new Random();
        String first = String.format("%06d", random.nextInt(1_000_000));
        String second = String.format("%05d", random.nextInt(100_000));
        String third = String.format("%02d", random.nextInt(100));
        return first + "-" + second + "-" + third;
    }

    private double randomAmountGenerator() {
        return new Random().nextInt(1_000_000) * 1.0;
    }

    private AccountType randomAccountType() {
        return new Random().nextBoolean() ? AccountType.CURRENT : AccountType.SAVINGS;
    }

    private String randomPhoneNumber() {
        Random random = new Random();
        return "31" + String.format("%07d", random.nextInt(10_000_000));
    }
}
