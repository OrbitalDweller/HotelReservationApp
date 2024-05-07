package model;

import java.util.regex.Pattern;

public class Customer {
    String firstName;
    String lastName;
    String email;

    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;

        String emailRegex = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (pattern.matcher(email).matches()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
    }

    @Override
    public String toString() {
        return "Customer " + firstName + " " + lastName + ", email: " + email;
    }
}
