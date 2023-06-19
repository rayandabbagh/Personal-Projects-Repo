public class PineappleDriver {
    public static void main(String[] args) {
        ComputerFactory factory = new DellisFactory();
        PineApple pineapple = new PineApple(factory);

        pineapple.orderComputer("Michael", "Dellis workstation");

        pineapple.setFactory(new HPiFactory());
        pineapple.orderComputer("Hanna", "HPi server");

        pineapple.setFactory(new LenovoiFactory());
        pineapple.orderComputer("Lenny", "Lenovoi laptop");
    }
}
