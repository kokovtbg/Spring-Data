package lab;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car {
    private Long id;
    private String fuelType;
    private String model;
    private BigDecimal price;
    private String type;
    private Integer seats;
    private PlateNumber plateNumber;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    @Column(name = "fuel_type")
    public String getFuelType() { return fuelType; }

    public void setFuelType(String fuelType) { this.fuelType = fuelType; }

    @Column(name = "model")
    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    @Column(name = "price", precision = 19, scale = 2)
    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    @Column(name = "type")
    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    @Column(name = "seats")
    public Integer getSeats() { return seats; }

    public void setSeats(Integer seats) { this.seats = seats; }

    @OneToOne
    @JoinColumn(name = "plateNumber_id", referencedColumnName = "id")
    public PlateNumber getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(PlateNumber plateNumber) {
        this.plateNumber = plateNumber;
    }
}
