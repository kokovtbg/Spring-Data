package softuni.exam.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private int population;
    private String guide;
    @OneToMany(targetEntity = Passenger.class, mappedBy = "town")
    private Set<Passenger> passengers;
    @OneToMany(targetEntity = Ticket.class, mappedBy = "fromTown")
    private Set<Ticket> ticketsFrom;
    @OneToMany(targetEntity = Ticket.class, mappedBy = "toTown")
    private Set<Ticket> ticketsTo;

    public Town() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Set<Ticket> getTicketsFrom() {
        return ticketsFrom;
    }

    public void setTicketsFrom(Set<Ticket> ticketsFrom) {
        this.ticketsFrom = ticketsFrom;
    }

    public Set<Ticket> getTicketsTo() {
        return ticketsTo;
    }

    public void setTicketsTo(Set<Ticket> ticketsTo) {
        this.ticketsTo = ticketsTo;
    }
}
