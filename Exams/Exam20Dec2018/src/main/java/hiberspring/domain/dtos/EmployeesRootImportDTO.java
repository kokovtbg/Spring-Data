package hiberspring.domain.dtos;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesRootImportDTO {
    @XmlElement(name = "employee")
    private List<EmployeeImportDTO> employees;

    public EmployeesRootImportDTO() {
    }

    public List<EmployeeImportDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeImportDTO> employees) {
        this.employees = employees;
    }
}
