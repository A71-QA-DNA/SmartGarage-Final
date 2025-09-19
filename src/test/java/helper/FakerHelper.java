package helper;

import com.github.javafaker.Faker;

public class FakerHelper {

    private static final Faker faker = new Faker();

    public static UserData createRandomUser() {
        String userName = faker.name().firstName();
        String userEmail = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String phone = faker.number().digits(10);
        return new UserData(userName, userEmail, firstName, lastName, phone);
    }
}
