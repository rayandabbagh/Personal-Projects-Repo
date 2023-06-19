public class HotFudge extends IceCreamDecorator {

    public HotFudge(IceCream iceCream) {
        this.iceCream = iceCream;
    }

    public String getDescription() {
        if (!iceCream.getDescription().contains(", hot fudge")) {
            return iceCream.getDescription() + ", hot fudge";
        } else {
            return iceCream.getDescription();
        }
    }

    public double cost() {
        if (!iceCream.getDescription().contains(", hot fudge")) {
            return iceCream.cost() + 0.99;
        } else {
            return iceCream.cost();
        }
    }
}
