public class PineApple {
    ComputerFactory factory;

    public PineApple(ComputerFactory factory) {
        this.factory = factory;
    }

    public void orderComputer(String name, String type) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null!");
        }
        Computer computer = factory.createComputer(type);
        System.out.println(name + " ordered a " + type);
        System.out.println("--- Making a " + type + " ---");
        computer.create();
    }

    public void setFactory(ComputerFactory factory) { this.factory = factory; }
}