package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class IngredientTest {
    private final IngredientType type;
    private final String name;
    private final float price;
    private Ingredient ingredient;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "type, name, price")
    public static Object[][] getData() {
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100},
                {IngredientType.SAUCE, "sour cream", 200},
                {IngredientType.SAUCE, "chili sauce", 300},
                {IngredientType.FILLING, "cutlet", 100},
                {IngredientType.FILLING, "dinosaur", 200},
                {IngredientType.FILLING, "sausage", 300},
        };
    }

    @Before
    public void createIngredient() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void returnsCorrectPrice() {
        Assert.assertEquals("Wrong price", price, ingredient.getPrice(), 0);
    }

    @Test
    public void returnsCorrectName() {
        Assert.assertEquals("Incorrect ingredient name", name, ingredient.getName());
    }

    @Test
    public void returnsCorrectType() {
        Assert.assertEquals("Incorrect type ingredient", type, ingredient.getType());
    }
}