package exercise.salesDatabase;

import lab.Plane;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Double quantity;
    private BigDecimal price;
    @OneToMany(mappedBy = "product", targetEntity = Sale.class)
    private Set<Sale> sales;
}
