package lab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import lab.entities.Shampoo;
import lab.entities.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findAllBySizeOrderById(Size size);
    List<Shampoo> findAllBySizeOrLabelIdOrderByPrice(Size size, long id);
    List<Shampoo> findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    List<Shampoo> findAllByPriceLessThan(BigDecimal price);

    @Query(value = "SELECT DISTINCT s.brand FROM Shampoo AS s " +
            "JOIN s.ingredients AS i WHERE i.name IN :ingredients")
    List<String> findByIngredientsNames(@Param(value = "ingredients") Set<String> ingredients);

    @Query(value = "SELECT brand FROM shampoos AS s\n" +
            "JOIN shampoos_ingredients AS si\n" +
            "ON si.shampoo_id = s.id\n" +
            "GROUP BY s.id\n" +
            "HAVING count(si.ingredient_id) < :countIngredients", nativeQuery = true)
    List<String> findByCountIngredients(@Param(value = "countIngredients") int countIngredients);
}
