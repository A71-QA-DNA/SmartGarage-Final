package helper;

public class UserData {
    private final String userName;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String phone;

    public UserData(String userName, String email, String firstName, String lastName, String phone) {
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
    public String getUserName() {return userName;}

    public String getEmail() {return email;}

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public String getPhone() {return phone;}

}
