package spring.autoMapping.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.autoMapping.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Game findByTitle(String title);
}
