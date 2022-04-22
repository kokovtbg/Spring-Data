package lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import lab.entities.Ingredient;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByNameStartingWith(String letters);
    List<Ingredient> findAllByNameIn(List<String> names);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Ingredient SET price = price * 1.1")
    void updateAllIngredientPrice();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Ingredient SET price = price * 1.1 WHERE name IN :names")
    void updateIngredientPriceByNameList(@Param(value = "names") List<String> names);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Ingredient WHERE name = :name")
    void deleteIngredientByName(@Param(value = "name") String name);
}
