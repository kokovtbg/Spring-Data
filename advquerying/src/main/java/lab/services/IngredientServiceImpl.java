package lab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lab.entities.Ingredient;
import lab.repositories.IngredientRepository;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> getAllIngredientsStartingWith(String letters) {
        return this.ingredientRepository.findAllByNameStartingWith(letters);
    }

    @Override
    public List<Ingredient> getAllByListOfNames(List<String> names) {
        return this.ingredientRepository.findAllByNameIn(names);
    }

    @Override
    public void updateAllIngredientPrice() {
        this.ingredientRepository.updateAllIngredientPrice();
    }

    @Override
    public void updateIngredientPriceByNameList(List<String> names) {
        this.ingredientRepository.updateIngredientPriceByNameList(names);
    }

    @Override
    public void deleteIngredientByName(String name) {
        this.ingredientRepository.deleteIngredientByName(name);
    }
}
