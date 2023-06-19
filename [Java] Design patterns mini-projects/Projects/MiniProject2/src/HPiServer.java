public class HPiServer extends Computer {
    private String networkCard;

    public HPiServer() {
        memory = "multiple HPi memory chips";
        hdd = "multiple HPi hdds";
        cpu = "a powerful HPi cpu with many cores";
        gpu = "a generic HPi gpu";
        networkCard = "a powerful HPi network card";
    }

    public String toString() {
        return super.toString() + " " + networkCard;
    }

    public void create() {
        super.create();
        addNetworkCard();
    }

    public void addNetworkCard() {
        System.out.println("      adding " + networkCard);
    }
}
