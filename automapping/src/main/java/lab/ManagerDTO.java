package lab;

import java.util.Collections;
import java.util.List;

public class ManagerDTO {
    private String firstName;
    private String lastName;
    private List<EmployeeDTO> employees;

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

    public List<EmployeeDTO> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    public void setEmployees(List<EmployeeDTO> employees) {
        this.employees = employees;
    }
}
