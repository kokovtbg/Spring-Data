package softuni.exam.dto;

public class PassengersImportDTO {
    private String firstName;
    private String lastName;
    private int age;
    private String phoneNumber;
    private String email;
    private String town;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public boolean isValid() {
        if (this.firstName.length() < 2) {
            return false;
        }
        if (this.lastName.length() < 2) {
            return false;
        }
        if (this.age < 1) {
            return false;
        }
        if (!this.email.contains("@") || !this.email.contains(".")) {
            return false;
        }
        return true;
    }
}
