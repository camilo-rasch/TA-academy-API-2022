package util;

import com.github.javafaker.Faker;

import java.util.Random;

public class Test {
    static Faker faker = new Faker();
    static String[] transa_type = {"Payment", "Invoice", "Withdrawal", "Deposit"};
    static Random transa_random = new Random();
    static String transa_string = transa_type[transa_random.nextInt(transa_type.length)];

    public static void main(String[] args) {
        System.out.println(faker.name().firstName());
        System.out.println(faker.name().lastName());
        System.out.println(faker.code().gtin8());
        System.out.println(transa_string);
        System.out.println(faker.internet().emailAddress());
        System.out.println(faker.bool().bool());
        System.out.println(faker.country().name());
        System.out.println(faker.phoneNumber().cellPhone());
    }
}
