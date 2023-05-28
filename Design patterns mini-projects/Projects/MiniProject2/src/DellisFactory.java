public class DellisFactory extends ComputerFactory {
    public Computer createComputer(String type) {
        if (type.equals("Dellis workstation")) {
            return new DellisWorkstation();
        } else if (type.equals("Dellis server")) {
            // implement when feature is added
            return null;
        } else if (type.equals("Dellis laptop")) {
            //implement when feature is added
            return null;
        }
        throw new IllegalArgumentException("This type of computer cannot be created!");
    }
}
