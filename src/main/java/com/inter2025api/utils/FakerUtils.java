package com.inter2025api.utils;

import java.util.concurrent.ThreadLocalRandom;

import com.github.javafaker.Faker;

public class FakerUtils {
    private static final Faker faker = new Faker();

    public static String generateRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateRandomName() {
        return faker.name().fullName();
    }

    public static String generateRandomPhoneNumber(String countryCode) {
       if("US".equals(countryCode)) {
        return faker.numerify("(505) 32#-###6");
       } else {
        return faker.phoneNumber().phoneNumber();
       }
    }

    public static String generateRandomAddress() {
        return faker.address().fullAddress();
    }

    public static String generateRandomListName() {
        return "List " + faker.number().numberBetween(1, 1000);
    }

    public static String generateRandomListDescription() {
        return faker.lorem().sentence();
    }
    public static String generateRandomComment() {
        return faker.lorem().sentence();
    }
    public static double generateRandomRating() {
        int steps = ThreadLocalRandom.current().nextInt(1, 21); 
        return steps * 0.5;
    }
}