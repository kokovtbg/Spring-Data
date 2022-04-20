package softuni.exam.dto;

import softuni.exam.models.Rating;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "seller")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerImportDTO {
    @XmlElement(name = "first-name")
    private String firstName;
    @XmlElement(name = "last-name")
    private String lastName;
    @XmlElement
    private String email;
    @XmlElement
    private String rating;
    @XmlElement
    private String town;

    public SellerImportDTO() {
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public boolean isValid() {
        if (this.firstName.length() < 3 || this.firstName.length() > 19) {
            return false;
        }
        if (this.lastName.length() < 3 || this.lastName.length() > 19) {
            return false;
        }
        if (!this.email.contains("@") || !this.email.contains(".")) {
            return false;
        }
        if (!this.rating.equals("GOOD") && !this.rating.equals("BAD") && !this.rating.equals("UNKNOWN")) {
            return false;
        }
        return true;
    }
}
