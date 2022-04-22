package spring.advQuerying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.advQuerying.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
