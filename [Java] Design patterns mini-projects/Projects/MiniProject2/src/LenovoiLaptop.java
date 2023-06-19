public class LenovoiLaptop extends Computer {
    private String wifiCard;

    public LenovoiLaptop() {
        memory = "a light Lenovoi memory";
        hdd = "a light Lenovoi hdd";
        cpu = "a light Lenovoi cpu";
        gpu = "a light Lenovoi gpu";
        wifiCard = "a light WiFi card";
    }

    public String toString() {
        return super.toString() + " " + wifiCard;
    }

    public void create() {
        super.create();
        addWifiCard();
    }

    public void addWifiCard() {
        System.out.println("      adding " + wifiCard);
    }
}
