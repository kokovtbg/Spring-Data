package jsonProcessing.carDealer.repositories;

import jsonProcessing.carDealer.entities.Car;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByMake(String make, Sort modelTravelledDistance);
}
