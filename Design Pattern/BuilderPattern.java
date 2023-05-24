public class BuilderPattern{
    public static void main(String[] args) {
        Phone p = new Phone()
                        .setBattery(4500)
                        .setOs("Android")
                        .setProcessor("Snapdragon 888")
                        .setram(8)
                        .setScreenSize(6.5);
        System.out.println(p);
    }
}

class Phone{
    private String os;
    private int ram;
    private String processor;
    private double screenSize;
    private int battery;

    public Phone(String os, int ram, String processor, double screenSize, int battery) {
        this.os = os;
        this.ram = ram;
        this.processor = processor;
        this.screenSize = screenSize;
        this.battery = battery;
    }

    public Phone() {}

    public Phone setOs(String os){
        this.os = os;
        return this;
    }
    public Phone setram(int ram){
        this.ram = ram;
        return this;
    }
    public Phone setProcessor(String processor){
        this.processor = processor;
        return this;
    }
    public Phone setScreenSize(double screenSize){
        this.screenSize = screenSize;
        return this;
    }
    public Phone setBattery(int battery){
        this.battery = battery;
        return this;
    }

    public String toString(){
        return "OS: "+this.os+" Ram: "+this.ram+" Processor: "+this.processor+" Screen Size: "+this.screenSize+" Battery: "+this.battery;
    }

}