package lab.services;

import lab.entities.Shampoo;
import lab.entities.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {
    List<Shampoo> getShampoosBySize(Size size);
    List<Shampoo> getShampoosBySizeOrLabel(Size size, long id);
    List<Shampoo> getShampoosByPriceGreaterThan(BigDecimal price);
    List<Shampoo> getShampoosByPriceLowerThan(BigDecimal price);

    List<String> getShampoosByIngredients(Set<String> ingredients);
    List<String> getShampoosByCountIngredients(int count);
}
