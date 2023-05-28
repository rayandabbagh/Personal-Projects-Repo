public class Sprinkles extends IceCreamDecorator{

    public Sprinkles(IceCream iceCream) {
        this.iceCream = iceCream;
    }

    public String getDescription() {
        if (!iceCream.getDescription().contains(", sprinkles")) {
            return iceCream.getDescription() + ", sprinkles";
        } else {
            return iceCream.getDescription();
        }
    }

    public double cost() {
        if (!iceCream.getDescription().contains(", sprinkles")) {
            return iceCream.cost() + 0.79;
        } else {
            return iceCream.cost();
        }
    }
}
