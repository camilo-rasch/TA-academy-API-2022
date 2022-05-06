package com.automation.api.data;

import com.automation.api.pojo.BankUser;
import com.automation.api.pojo.enums.AccountType;
import org.testng.annotations.DataProvider;

import java.util.Random;

public class RandomData {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    @DataProvider(name = "bank user")
    public Object[][] randomBankUser() {
        BankUser jaimito = BankUser.builder()
                .first_name(randomTextGenerator(5))
                .last_name(randomTextGenerator(7))
                .account_number("123456-12345-12")
                .amount(100_000_000)
                .account_type(AccountType.SAVINGS)
                .email(randomTextGenerator(9) + "@gmail.com")
                .active(true)
                .country("Colombia")
                .telephone("312123456")
                .build();

        return new Object[][] {{jaimito}};
    }

    private String randomTextGenerator(int textLength) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder(textLength);
        for (int i = 0; i < textLength; i++) {
            builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return builder.toString();
    }
}
