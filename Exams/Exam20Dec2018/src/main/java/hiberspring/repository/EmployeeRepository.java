package hiberspring.repository;

import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByCard(EmployeeCard cardByNumber);

    @Query("SELECT e FROM Employee e WHERE size(e.branch.products) > 0 " +
            "ORDER BY firstName, lastName, char_length(position) DESC")
    List<Employee> findByBranchCountProductsGreaterThanZero();
}
