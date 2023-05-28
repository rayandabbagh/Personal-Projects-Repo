public class SingleScoopIceCream implements IceCream {

    private String description = "ice cream";
    private String flavor;

    public SingleScoopIceCream(String flavor) {
        this.flavor = flavor;
        if (this.flavor != null) {
            if (this.flavor.equals("vanilla") || this.flavor.equals("chocolate")) {
                description = "A single scoop of " + this.flavor + " ice cream";
            }
        } else {
            description = "";
        }
    }

    @Override
    public String getDescription() {
        return description;
    }

    public double cost() {
        return 2.79;
    }
}

