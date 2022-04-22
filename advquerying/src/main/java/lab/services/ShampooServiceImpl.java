package lab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lab.entities.Shampoo;
import lab.entities.Size;
import lab.repositories.ShampooRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {
    private ShampooRepository shampooRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> getShampoosBySize(Size size) {
        return this.shampooRepository.findAllBySizeOrderById(size);
    }

    @Override
    public List<Shampoo> getShampoosBySizeOrLabel(Size size, long id) {
        return this.shampooRepository.findAllBySizeOrLabelIdOrderByPrice(size, id);
    }

    @Override
    public List<Shampoo> getShampoosByPriceGreaterThan(BigDecimal price) {
        return this.shampooRepository.findAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public List<Shampoo> getShampoosByPriceLowerThan(BigDecimal price) {
        return this.shampooRepository.findAllByPriceLessThan(price);
    }

    @Override
    public List<String> getShampoosByIngredients(Set<String> ingredients) {
        return this.shampooRepository.findByIngredientsNames(ingredients);
    }

    @Override
    public List<String> getShampoosByCountIngredients(int count) {
        return this.shampooRepository.findByCountIngredients(count);
    }
}
