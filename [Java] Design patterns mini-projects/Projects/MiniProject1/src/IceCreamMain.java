import java.text.DecimalFormat;

public class IceCreamMain {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {

        IceCream iceCream1 = new DoubleScoopIceCream("vanilla");
        iceCream1 = new HotFudge(iceCream1);
        iceCream1 = new Sprinkles(iceCream1);
        System.out.println(iceCream1.getDescription() + " $" + df.format(iceCream1.cost()));

        IceCream iceCream2 = new SingleScoopIceCream("chocolate");
        iceCream2 = new Caramel(iceCream2);
        iceCream2 = new WhippedCream(iceCream2);
        System.out.println(iceCream2.getDescription() + " $" + df.format(iceCream2.cost()));
    }
}