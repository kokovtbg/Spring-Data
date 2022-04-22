package spring.advQuerying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.advQuerying.model.entity.Author;

import java.util.List;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByFirstNameEndingWith(String part);
}
