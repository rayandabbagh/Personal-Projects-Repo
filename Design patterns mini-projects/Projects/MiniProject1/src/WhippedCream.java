public class WhippedCream extends IceCreamDecorator {

    public WhippedCream(IceCream iceCream) {
        this.iceCream = iceCream;
    }

    public String getDescription() {
        if (!iceCream.getDescription().contains(", whipped cream")) {
            return iceCream.getDescription() + ", whipped cream";
        } else {
            return iceCream.getDescription();
        }
    }

    public double cost() {
        if (!iceCream.getDescription().contains(", whipped cream")) {
            return iceCream.cost() + 0.5;
        } else {
            return iceCream.cost();
        }
    }
}
