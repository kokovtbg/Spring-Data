package lab;

import javax.persistence.*;

@Entity
@Table(name = "plate_numbers")
public class PlateNumber {
    private Long id;
    private String number;
    private Car car;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    @Column(name = "number")
    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    @OneToOne(mappedBy = "plateNumber")
    public Car getCar() { return car; }

    public void setCar(Car car) { this.car = car; }
}
