package softuni.exam.models.dto;

public class AgentsImportDTO {
    private String firstName;
    private String lastName;
    private String town;
    private String email;

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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValid() {
        if (this.firstName.length() < 2) {
            return false;
        }
        if (this.lastName.length() < 2) {
            return false;
        }
        if (!this.email.contains("@") || !this.email.contains(".")) {
            return false;
        }
        return true;
    }
}
