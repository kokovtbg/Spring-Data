package softuni.exam.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String make;
    private String model;
    private int kilometers;
    @Column(name = "registered_on")
    private LocalDate registeredOn;
    @OneToMany(targetEntity = Offer.class, mappedBy = "car")
    private Set<Offer> offers;
    @OneToMany(targetEntity = Picture.class, mappedBy = "car")
    private Set<Picture> pictures;

    public Car() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return String.format("Car make - %s, model - %s\n" +
                "\tKilometers - %d\n" +
                "\tRegistered on - %s\n" +
                "\tNumber of pictures - %d", this.getMake(), this.getModel(),
                this.getKilometers(), this.getRegisteredOn(), this.getPictures().size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && kilometers == car.kilometers && make.equals(car.make) && model.equals(car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, make, model, kilometers);
    }
}
