public class Caramel extends IceCreamDecorator {

    public Caramel(IceCream iceCream) {
        this.iceCream = iceCream;
    }

    public String getDescription() {
        if (!iceCream.getDescription().contains(", caramel")) {
            return iceCream.getDescription() + ", caramel";
        } else {
            return iceCream.getDescription();
        }
    }

    public double cost() {
        if (!iceCream.getDescription().contains(", caramel")) {
            return iceCream.cost() + 0.89;
        } else {
            return iceCream.cost();
        }
    }
}
