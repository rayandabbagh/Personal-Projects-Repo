public abstract class Computer {
    protected String memory;
    protected String hdd;
    protected String cpu;
    protected String gpu;


    public String toString() {
        return memory + " " + hdd + " " + cpu + " " + gpu;
    }

    public void create() {
        addMemory();
        addHdd();
        addCpu();
        addGpu();
    }

    public void addMemory() {
        System.out.println("      adding " + memory);
    }

    public void addHdd() {
        System.out.println("      adding " + hdd);
    }

    public void addCpu() {
        System.out.println("      adding " + cpu);
    }

    public void addGpu() {
        System.out.println("      adding " + gpu);
    }

}
