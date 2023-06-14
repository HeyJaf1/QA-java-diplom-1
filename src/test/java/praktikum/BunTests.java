package praktikum;

import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
@AllArgsConstructor
public class BunTests {

    private final String name;
    private final float price;

    @Parameterized.Parameters(name = "name, price")
    public static Object[][] getData() {
        return new Object[][] {
                {"Булочка", 100},
                {"Рагалик", 200},
                {"С изюмом", 0},
                {"С творогом", -1000},
                {"      ", 10},
                {"!!!!", 20},
                {"С маком", 0.10f},
                {"Трубочка", 2147483647},
        };
    }

    @Test
    public void successfulNameTest() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals("Assert", name, bun.getName());
        System.out.println("name " + bun.getName() + ".");
    }

    @Test
    public void successfulPriceTest() {
        Bun bun = new Bun(name, price);
        Assert.assertEquals("Assert", price, bun.getPrice(), 0.0f);
        System.out.println("Price " + bun.getPrice() + " рублей.");
    }
}