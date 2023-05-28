import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class IceCreamTests {

    @Before
    public void setUp() {
        SingleScoopIceCream singleScoopChocolateIceCream = new SingleScoopIceCream("chocolate");
        SingleScoopIceCream singleScoopVanillaIceCream = new SingleScoopIceCream("vanilla");
        DoubleScoopIceCream doubleScoopChocolateIceCream = new DoubleScoopIceCream("chocolate");
        DoubleScoopIceCream doubleScoopVanillaIceCream = new DoubleScoopIceCream("vanilla");
    }

    /**
     * Julius
     * This test case essentially checks if the String flavor passed into the parameters when a new
     * IceCream is initialized is valid. A valid parameter would be the string chocolate or vanilla.
     * If anything else is passed in, there will be an error.
     */
    @Test
    public void checkForNullAndInvalidFlavor() {
        SingleScoopIceCream singleScoopChocolateIceCream1 = new SingleScoopIceCream(null);
        SingleScoopIceCream singleScoopChocolateIceCream2 = new SingleScoopIceCream("asdfghjkl");
        SingleScoopIceCream singleScoopChocolateIceCream3 = new SingleScoopIceCream("vanilla");
        DoubleScoopIceCream doubleScoopChocolateIceCream1 = new DoubleScoopIceCream(null);
        DoubleScoopIceCream doubleScoopChocolateIceCream2 = new DoubleScoopIceCream("hello");
        DoubleScoopIceCream doubleScoopChocolateIceCream3 = new DoubleScoopIceCream("chocolate");
        assertEquals(singleScoopChocolateIceCream1.getDescription(), "");
        assertEquals(singleScoopChocolateIceCream2.getDescription(), "ice cream");
        assertEquals(singleScoopChocolateIceCream3.getDescription(), "A single scoop of vanilla");
        assertEquals(doubleScoopChocolateIceCream1.getDescription(), "");
        assertEquals(doubleScoopChocolateIceCream2.getDescription(), "ice cream");
        assertEquals(doubleScoopChocolateIceCream3.getDescription(), "A double scoop of chocolate");
    }

    /**
     * Prisha
     * The purpose of this test case is to ensure that a customer is not charged more than once for
     * adding the same topping. It also makes sure that a topping is not added to the description
     * of the order more than once.
     */
    @Test
    public void toppingsAddedOnlyOnce() {
        IceCream doubleScoopChocolateIceCream1 = new DoubleScoopIceCream("chocolate");
        doubleScoopChocolateIceCream1 = new HotFudge(doubleScoopChocolateIceCream1);
        doubleScoopChocolateIceCream1 = new HotFudge(doubleScoopChocolateIceCream1);
        doubleScoopChocolateIceCream1 = new HotFudge(doubleScoopChocolateIceCream1);
        assertEquals(doubleScoopChocolateIceCream1.getDescription(), "A double scoop of chocolate, hot fudge");
        assertEquals(4.98, doubleScoopChocolateIceCream1.cost(), 0.0);
        IceCream singleScoopChocolateIceCream1 = new SingleScoopIceCream("vanilla");
        singleScoopChocolateIceCream1 = new Sprinkles(singleScoopChocolateIceCream1);
        singleScoopChocolateIceCream1 = new Sprinkles(singleScoopChocolateIceCream1);
        assertEquals(singleScoopChocolateIceCream1.getDescription(), "A single scoop of vanilla, sprinkles");
        assertEquals(3.58, singleScoopChocolateIceCream1.cost(), 0.0);
    }

    /**
     * Aashna
     * This test case creates a custom order of a single scoop of chocolate ice cream consisting of all the
     * toppings sprinkles, caramel, hot fudge, and whipped cream. It checks if the correct string is
     * outputted and the cost of the order is calculated correctly.
     */
    @Test
    public void testCustomOrderWithAllToppings() {
        IceCream singleScoopVanillaIceCream = new SingleScoopIceCream("chocolate");
        singleScoopVanillaIceCream = new Sprinkles(singleScoopVanillaIceCream);
        singleScoopVanillaIceCream = new Caramel(singleScoopVanillaIceCream);
        singleScoopVanillaIceCream = new HotFudge(singleScoopVanillaIceCream);
        singleScoopVanillaIceCream = new WhippedCream(singleScoopVanillaIceCream);
        assertEquals(singleScoopVanillaIceCream.getDescription(), "A single scoop of chocolate, sprinkles, " +
                "caramel, hot fudge, whipped cream");
        assertEquals(5.96, singleScoopVanillaIceCream.cost(), 0.0);
    }

    /**
     * Rohith
     * This test case creates a custom order of a double scoop of vanilla ice cream consisting of the
     * toppings caramel and hot fudge. It checks if the correct string is outputted and the cost of
     * the order is calculated correctly.
     */
    @Test
    public void testCustomOrderWithTwoToppings() {
        IceCream doubleScoopChocolateIceCream = new DoubleScoopIceCream("vanilla");
        doubleScoopChocolateIceCream = new Caramel(doubleScoopChocolateIceCream);
        doubleScoopChocolateIceCream = new HotFudge(doubleScoopChocolateIceCream);
        assertEquals(doubleScoopChocolateIceCream.getDescription(), "A double scoop of vanilla, caramel, " +
                "hot fudge");
        assertEquals(5.87, doubleScoopChocolateIceCream.cost(), 0.0);
    }

    /**
     * Rayan
     * This test case creates a custom order of a double scoop of vanilla ice cream and a custom order of
     * a single scoop of chocolate ice cream. There are no toppings in either of these orders. The aim of the
     * test case is to check the price of the order and their descriptions.
     */
    @Test
    public void noToppingCheck() {
        IceCream singleScoopVanillaIceCream = new SingleScoopIceCream("chocolate");
        IceCream doubleScoopChocolateIceCream = new DoubleScoopIceCream("vanilla");
        assertEquals(2.79, singleScoopVanillaIceCream.cost(), 0.0);
        assertEquals(singleScoopVanillaIceCream.getDescription(), "A single scoop of chocolate");
        assertEquals(3.99, doubleScoopChocolateIceCream.cost(), 0.0);
        assertEquals(doubleScoopChocolateIceCream.getDescription(), "A double scoop of vanilla");
    }
}