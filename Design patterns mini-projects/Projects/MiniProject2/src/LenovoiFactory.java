public class LenovoiFactory extends ComputerFactory {
    public Computer createComputer(String type) {
        if (type.equals("Lenovoi workstation")) {
            // implement when feature is added
            return null;
        } else if (type.equals("Lenovoi server")) {
            //implement when feature is added
            return null;
        } else if (type.equals("Lenovoi laptop")) {
            return new LenovoiLaptop();
        }
        throw new IllegalArgumentException("This type of computer cannot be created!");
    }
}
