package hiberspring.domain.entities;

import javax.persistence.*;

@Entity
@Table(name = "employee_cards")
public class EmployeeCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String number;
    @OneToOne(targetEntity = Employee.class, mappedBy = "card")
    private Employee employee;

    public EmployeeCard() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
