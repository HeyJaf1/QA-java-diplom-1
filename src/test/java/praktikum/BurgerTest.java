package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Database database = Mockito.mock(Database.class);
    private int ingredientsSize;
    private Burger burger = Mockito.spy(Burger.class);
    private Bun bun;
    private List<Ingredient> ingredients;

    public BurgerTest() {
        List<Bun> buns = new ArrayList<>();
        // Положительные
        buns.add(new Bun("С изюмом", 100));
        buns.add(new Bun("С творогом", 200));
        buns.add(new Bun("Простая", 300));

        //Отрицательные
        buns.add(new Bun("С шоколадом", 0));
        buns.add(new Bun("С соусом", -100));
        Mockito.when(database.availableBuns()).thenReturn(buns);
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(IngredientType.SAUCE, "sauce1", 100));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "sauce2", 200));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "sauce3", 300));
        ingredients.add(new Ingredient(IngredientType.FILLING, "steak", 100));
        ingredients.add(new Ingredient(IngredientType.FILLING, "steakMini", 200));
        ingredients.add(new Ingredient(IngredientType.FILLING, "steakMax", 300));
        Mockito.when(database.availableIngredients()).thenReturn(ingredients);
    }

    @Before
    public void createBurger() {
        burger = Mockito.spy(Burger.class);
        bun = database.availableBuns().get(0);
        ingredients = database.availableIngredients();
        burger.setBuns(bun);
        for (int i = 0; i < ingredients.size(); i++) {
            burger.addIngredient(database.availableIngredients().get(i));
        }
        ingredientsSize = burger.ingredients.size();
    }

    @Test
    public void returnsCorrectReceipt() {
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));
        for (Ingredient ingredient : ingredients) {
            receipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }
        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", burger.getPrice()));
        Assert.assertEquals("Wrong recipe", receipt.toString(), burger.getReceipt());
    }

    @Test
    public void returnsCorrectBun() {
        Assert.assertEquals("warning bun wrong", bun, burger.bun);
    }

    @Test
    public void returnsCorrectSizeOfIngredientsAfterAdd() {
        burger.addIngredient(database.availableIngredients().get(1));
        Assert.assertEquals("warning count ingredients", ingredients.size() + 1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientIsCorrect() {
        burger.removeIngredient(1);
        Assert.assertEquals("warning ingredients no delete", ingredientsSize - 1, burger.ingredients.size());
    }

    @Test
    public void movedIngredientInRightPlace() {
        Ingredient ingredientPosition = burger.ingredients.get(1);
        burger.moveIngredient(1, 0);
        Ingredient ingredientPositionAfterMoving = burger.ingredients.get(0);
        Assert.assertEquals("warning order", ingredientPositionAfterMoving, ingredientPosition);
    }

    @Test
    public void returnsCorrectPrice() {
        float expectedPrice = 100f;
        Mockito.when(burger.getPrice()).thenReturn(expectedPrice);
        Assert.assertEquals("price incorrectly", expectedPrice, burger.getPrice(), 0);
    }
}