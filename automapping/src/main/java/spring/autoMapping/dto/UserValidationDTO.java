package spring.autoMapping.dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidationDTO {
    private String email;
    private String password;

    private final String passwordValidator =
            ".*[a-z]+.*[A-Z]+.*[0-9]+.*|.*[A-Z]+.*[a-z]+.*[0-9]+.*|.*[a-z]+.*[0-9]+.*[A-Z]+.*|" +
                    ".*[A-Z]+.*[0-9]+.*[a-z]+.*|.*[0-9]+.*[a-z]+.*[A-Z]+.*|.*[0-9]+.*[A-Z]+.*[a-z]+.*";

    public UserValidationDTO() {
    }

    public UserValidationDTO(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Incorrect email.");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password must be more than 6 symbols");
        }
        Pattern pattern = Pattern.compile(passwordValidator);
        Matcher matcher = pattern.matcher(password);
        boolean match = matcher.find();
        if (!match) {
            throw new IllegalArgumentException("Password must contain at least 1 uppercase, 1 lowercase letter and 1 digit");
        }
        this.password = password;
    }
}
