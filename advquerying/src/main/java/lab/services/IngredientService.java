package lab.services;

import lab.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> getAllIngredientsStartingWith(String letters);
    List<Ingredient> getAllByListOfNames(List<String> names);
    void updateAllIngredientPrice();
    void updateIngredientPriceByNameList(List<String> names);
    void deleteIngredientByName(String name);
}
