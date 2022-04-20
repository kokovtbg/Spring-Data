package exam.dto;

import com.google.gson.annotations.SerializedName;
import exam.model.Town;

import java.time.LocalDate;

public class CustomersImportDTO {
    private String firstName;
    private String lastName;
    private String email;
    @SerializedName(value = "registeredOn")
    private String registeredOnString;
    @SerializedName(value = "town")
    private TownNameJSONDTO townNameDTO;
    private LocalDate registeredOnDate;
    private Town townInRepo;

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

    public String getRegisteredOnString() {
        return registeredOnString;
    }

    public void setRegisteredOnString(String registeredOnString) {
        this.registeredOnString = registeredOnString;
    }

    public TownNameJSONDTO getTownNameDTO() {
        return townNameDTO;
    }

    public void setTownNameDTO(TownNameJSONDTO townNameDTO) {
        this.townNameDTO = townNameDTO;
    }

    public LocalDate getRegisteredOnDate() {
        return registeredOnDate;
    }

    public void setRegisteredOnDate(LocalDate registeredOnDate) {
        this.registeredOnDate = registeredOnDate;
    }

    public Town getTownInRepo() {
        return townInRepo;
    }

    public void setTownInRepo(Town townInRepo) {
        this.townInRepo = townInRepo;
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
