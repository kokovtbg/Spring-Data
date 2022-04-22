package lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lab.entities.Ingredient;
import lab.entities.Shampoo;
import lab.entities.Size;
import lab.services.IngredientService;
import lab.services.ShampooService;

import java.math.BigDecimal;
import java.util.*;

@Component
public class ConsoleRunnerLab implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired
    public ConsoleRunnerLab(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
//        getAllShampoosBySize();//1.	Select Shampoos by Size
//        getAllShampoosBySizeOrLabel();//2.	Select Shampoos by Size or Label
//        getAllShampoosByPrice();//3.	Select Shampoos by Price
//        getAllIngredientsStartingWith();//4.	Select Ingredients by Name
//        getAllIngredientsByListOfNames();//5.	Select Ingredients by Names
//        getAllShampoosByPriceLowerThan();//6.	Count Shampoos by Price
//        getAllShampoosByListOfIngredients();//7.	Select Shampoos by Ingredients
//        getAllShampoosByCountIngredients();//8.	Select Shampoos by Ingredients Count
//        deleteIngredientByName();//9.	Delete Ingredients by Name
//        updateAllIngredientPrice();//10.	Update Ingredients by Price
//        updateIngredientPriceByNameList();//11.	Update Ingredients by Names
    }

    private void updateIngredientPriceByNameList() {
        List<String> names = List.of("Apple", "Lavender", "Herbs");
        this.ingredientService.updateIngredientPriceByNameList(names);
    }

    private void updateAllIngredientPrice() {
        this.ingredientService.updateAllIngredientPrice();
    }

    private void deleteIngredientByName() {
        String name = "Apple";
        this.ingredientService.deleteIngredientByName(name);
    }

    private void getAllShampoosByCountIngredients() {
        System.out.println("Enter Count Ingredients");
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        List<String> shampoosByCountIngredients = this.shampooService.getShampoosByCountIngredients(count);
        shampoosByCountIngredients.forEach(System.out::println);
    }

    private void getAllShampoosByListOfIngredients() {
        System.out.println("Enter Ingredient Names");
        Scanner scanner = new Scanner(System.in);
        String nameIngredient = scanner.nextLine();
        Set<String> ingredients = new LinkedHashSet<>();
        while (!nameIngredient.equals("End")) {
            ingredients.add(nameIngredient);
            nameIngredient = scanner.nextLine();
        }
        List<String> shampoosByIngredients = this.shampooService.getShampoosByIngredients(ingredients);
        shampoosByIngredients.forEach(s -> System.out.printf("%s%n", s));
    }

    private void getAllShampoosByPriceLowerThan() {
        System.out.println("Enter Price");
        Scanner scanner = new Scanner(System.in);
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
        List<Shampoo> shampoosByPriceLowerThan = shampooService.getShampoosByPriceLowerThan(price);
        System.out.println(shampoosByPriceLowerThan.size());
    }

    private void getAllIngredientsByListOfNames() {
        System.out.println("Enter Ingredient Names");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        List<String> ingredientsList = new ArrayList<>();
        while (!name.equals("End")) {
            ingredientsList.add(name);
            name = scanner.nextLine();
        }
        List<Ingredient> ingredients = this.ingredientService.getAllByListOfNames(ingredientsList);
        ingredients.stream().sorted(Comparator.comparing(Ingredient::getPrice))
                .forEach(i -> System.out.printf("%s%n", i.getName()));
    }

    private void getAllIngredientsStartingWith() {
        System.out.println("Enter Letters");
        Scanner scanner = new Scanner(System.in);
        String letters = scanner.nextLine();
        List<Ingredient> ingredients = this.ingredientService.getAllIngredientsStartingWith(letters);
        ingredients.forEach(i -> System.out.printf("%s%n", i.getName()));
    }

    private void getAllShampoosByPrice() {
        System.out.println("Enter Price");
        Scanner scanner = new Scanner(System.in);
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
        List<Shampoo> shampoosByPrice = this.shampooService.getShampoosByPriceGreaterThan(price);
        shampoosByPrice.forEach(s -> System.out.printf("%s %s %.2f%n", s.getBrand(), s.getSize(), s.getPrice()));
    }

    private void getAllShampoosBySizeOrLabel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Shampoo Size and Label Id");
        String size = scanner.nextLine();
        long id = Long.parseLong(scanner.nextLine());
        List<Shampoo> shampoos = shampooService.getShampoosBySizeOrLabel(Size.valueOf(size), id);
        shampoos.forEach(s -> System.out.printf("%s %s %.2flv.%n", s.getBrand(), s.getSize(), s.getPrice()));
    }

    private void getAllShampoosBySize() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shampoo Size");
        String size = scanner.nextLine();
        List<Shampoo> shampoos = shampooService.getShampoosBySize(Size.valueOf(size));
        shampoos.forEach(s -> System.out.printf("%s %s %.2flv.%n", s.getBrand(), s.getSize(), s.getPrice()));
    }
}
