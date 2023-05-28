public class HPiFactory extends ComputerFactory {
    public Computer createComputer(String type) {
        if (type.equals("HPi workstation")) {
            // implement when feature is added
            return null;
        } else if (type.equals("HPi server")) {
            return new HPiServer();
        } else if (type.equals("HPi laptop")) {
            //implement when feature is added
            return null;
        }
        throw new IllegalArgumentException("This type of computer cannot be created!");
    }
}
