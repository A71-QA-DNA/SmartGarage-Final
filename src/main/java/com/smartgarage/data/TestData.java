package com.smartgarage.data;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestData {

    public static final Faker faker = new Faker();

    public static final String ADMIN_USERNAME = "felix_jackson";
    public static final String ADMIN_PASSWORD = "password123%D";

    public static final String VALID_OWNER = "Annihilati0N";
    public static final String VALID_MODEL = "RS 2";// Maximum valid length
    public static final String VALID_ENGINE_TYPE = "2.2 I5 turbo";
    public static final String VALID_YEAR = "1994";

    public static final String INVALID_VIN_SHORT = "JF2BIOQC2FG00775";
    public static final String INVALID_VIN_LONG = "JF2BIOQC2FG0077533";

    public static final String SEARCH_OWNER_FIRSTNAME = "todor";
    public static final String SEARCH_OWNER_USERNAME = "Annihilati0N";
    public static final String UPDATED_MODEL = "A6";
    public static final String UPDATED_ENGINE_TYPE = "3.0 V6 turbo";
    public static final String UPDATED_YEAR = "2020";

    public static final String SORT_BY_FIRST_NAME = "Owner First Name";
    public static final String SORT_BY_USERNAME = "Owner Username";
    public static final String SORT_ORDER_ASC = "asc";
    public static final String SORT_ORDER_DESC = "desc";


    public static String generateVin(int n) {
        String alphabet = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(alphabet.charAt(faker.random().nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    public static String generateRandomLicensePlate() {
        final List<String> REGION_CODES = List.of(
                "A", "B", "CH", "Y", "TX", "H", "CC", "PP", "T", "P", "BT", "EB", "CT", "X", "K", "CM",
                "PB", "OB", "EH", "PA", "E", "KH", "PK", "CA", "C", "CB", "CO", "BP", "M", "BH"
        );

        final char[] VALID_SUFFIX = {
                'A', 'B', 'E', 'K', 'M', 'H', 'O', 'P', 'C', 'T', 'Y', 'X'
        };

        final Random RNG = ThreadLocalRandom.current();
        String region = REGION_CODES.get(RNG.nextInt(REGION_CODES.size()));
        String number = String.format("%04d", RNG.nextInt(10_000));
        char first = VALID_SUFFIX[RNG.nextInt(VALID_SUFFIX.length)];
        char second = VALID_SUFFIX[RNG.nextInt(VALID_SUFFIX.length)];
        return region + number + first + second;
    }
}