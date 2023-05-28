public class DellisWorkstation extends Computer {
    private String audioCard;
    private String networkCard;

    public DellisWorkstation() {
        memory = "Dellis memory";
        hdd = "Dellis hdd";
        cpu = "Dellis cpu";
        gpu = "a powerful Dellis gpu";
        audioCard = "an audio card";
        networkCard = "a generic network card";
    }

    public String toString() {
        return super.toString() + " " + audioCard + " " + networkCard;
    }

    public void create() {
        super.create();
        addAudioCard();
        addNetworkCard();
    }

    public void addAudioCard() {
        System.out.println("      adding " + audioCard);
    }

    public void addNetworkCard() {
        System.out.println("      adding " + networkCard);
    }
}
