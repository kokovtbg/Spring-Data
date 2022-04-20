package softuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.Passenger;

import java.util.Set;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    Passenger findByEmail(String email);

    @Query("SELECT p FROM Passenger p JOIN p.tickets ORDER BY size(p.tickets) DESC, email")
    Set<Passenger> findAllOrderByTicketsCountAndEmail();
}
