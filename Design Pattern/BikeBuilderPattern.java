public class BikeBuilderPattern {
    public static void main(String[] args) {
        Bike myBike = new Bike()
                            .setType("Racing Bike")
                            .setMaxSpeed(180)
                            .setGears(5);
        System.out.println(myBike);
    }
}

class Bike{
    private String type;
    private double mileage;
    private double maxSpeed;
    private double gears;

    Bike(){
        // default parameters
        type = "Mountain Bike";
        mileage = 50;
        maxSpeed = 100;
        gears = 6;
    }

    public Bike setType(String type){
        this.type = type;
        return this;
    }

    public Bike setMileage(double mileage){
        this.mileage = mileage;
        return this;
    }
    public Bike setMaxSpeed(double maxSpeed){
        this.maxSpeed = maxSpeed;
        return this;
    }
    public Bike setGears(double gears){
        this.gears = gears;
        return this;
    }
    public String getType(){
        return type;
    }
    public double getMileage(){
        return mileage;
    }
    public double getMaxSpeed(){
        return maxSpeed;
    }
    public double getGears(){
        return gears;
    }
    
    public String toString(){
        String x = "";
        if(type != null){
            x+= "Type: "+type;
        }
        if(mileage != 0){
            x+= " Mileage: "+mileage;
        }
        if(maxSpeed != 0){
            x+= " Max Speed: "+maxSpeed;
        }
        if(gears != 0){
            x+= " Gears: "+gears;
        }
        return x;
    }
    

}