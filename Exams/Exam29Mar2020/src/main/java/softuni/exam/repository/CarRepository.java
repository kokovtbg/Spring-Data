package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.Car;

import java.util.List;
import java.util.Set;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findByMakeAndModelAndKilometers(String make, String model, int kilometers);

    @Query("SELECT c FROM Car c LEFT JOIN c.pictures ORDER BY size(c.pictures) DESC, make")
    Set<Car> findAllOrderByPicturesCountDescAndMakeAsc();
}
