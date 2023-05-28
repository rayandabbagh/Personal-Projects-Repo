import org.junit.Assert;
import org.junit.Test;


public class TestCases {

    @Test(expected = IllegalArgumentException.class)
    public void testNonexistingComputerType() {
        ComputerFactory factory = new DellisFactory();
        factory.createComputer("nonexistingComputer");
    }

    @Test
    public void testDellisWorkstation() {
        ComputerFactory factory = new DellisFactory();
        String CorrectString = "Dellis memory Dellis hdd Dellis cpu a powerful Dellis gpu an audio card a generic network card";
        Assert.assertEquals(CorrectString, factory.createComputer("Dellis workstation").toString());
    }

    @Test
    public void testHPiServer() {
        ComputerFactory factory = new HPiFactory();
        String CorrectString = "multiple HPi memory chips multiple HPi hdds"
                             + " a powerful HPi cpu with many cores a generic HPi gpu"
                             + " a powerful HPi network card";
        Assert.assertEquals(CorrectString, factory.createComputer("HPi server").toString());
    }

    @Test
    public void testLenovoiLaptop() {
        ComputerFactory factory = new LenovoiFactory();
        String CorrectString = "a light Lenovoi memory a light Lenovoi hdd a light Lenovoi cpu"
                             + " a light Lenovoi gpu a light WiFi card";
        Assert.assertEquals(CorrectString, factory.createComputer("Lenovoi laptop").toString());
    }

    @Test
    public void testComputerFactory() {
        ComputerFactory Dfactory = new DellisFactory();
        ComputerFactory Hfactory = new HPiFactory();
        ComputerFactory Lfactory = new LenovoiFactory();
        DellisWorkstation workstation = new DellisWorkstation();
        HPiServer server = new HPiServer();
        LenovoiLaptop laptop = new LenovoiLaptop();

        Assert.assertEquals(workstation.toString(), Dfactory.createComputer("Dellis workstation").toString());
        Assert.assertEquals(server.toString(), Hfactory.createComputer("HPi server").toString());
        Assert.assertEquals(laptop.toString(), Lfactory.createComputer("Lenovoi laptop").toString());
    }
}
