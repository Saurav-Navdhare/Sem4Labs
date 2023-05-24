import java.util.ArrayList;

public class practice {
    public static void main(String[] args) throws CloneNotSupportedException {
        // create objects of farmer and add data to database
        Farmer Kishan = new Farmer("Kishan", 20, 10000, "Karnataka", "1234567890");
        Farmer Ramesh = new Farmer("Ramesh", 30, 20000, "Karnataka", "1234567890");
        Farmer Suresh = new Farmer("Suresh", 40, 30000, "Karnataka", "1234567890");
        Farmer Mahesh = new Farmer("Mahesh", 50, 40000, "Karnataka", "1234567890");
        Database myDB = new Database();

        myDB.addFarmer(Kishan);
        myDB.addFarmer(Ramesh);
        myDB.addFarmer(Suresh);
        myDB.addFarmer(Mahesh);

        // create a deep copy of database
        Database deepCopy = (Database) myDB.clone("Deep");
        // create a shallow copy of database
        Database shallowCopy = (Database) myDB.clone("");
        System.out.println("Original Database");
        myDB.printFarmers();
        System.out.println("Making Changes");
        myDB.get(0).setName("Sukesh");
        System.out.println("Original Copy");
        myDB.printFarmers();
        System.out.println("Deep Copy");
        deepCopy.printFarmers();
        System.out.println("Shallow Copy");
        shallowCopy.printFarmers();


    }
}

class Farmer {
    private String name;
    private int age;
    private double income;
    private String address;
    private String phone;

    Farmer(String name, int age, double income, String address, String phone) {
        this.name = name;
        this.age = age;
        this.income = income;
        this.address = address;
        this.phone = phone;
    }

    Farmer(Farmer f){
        this.name = f.name;
        this.age = f.age;
        this.income = f.income;
        this.address = f.address;
        this.phone = f.phone;
    }

    public void setName(String name){
        this.name = name;
    }

    public String toString(){
        return "Name: " + name + " Age: " + age + " Income: " + income + " Address: " + address + " Phone: " + phone;
    }
    
}

class Database implements Cloneable {
    // private List<farmer> farmers = new ArrayList<farmer>();
    // create an ArrayList of farmers
    private Farmer[] farmers;
    private int index = 0;
    Database(){
        farmers = new Farmer[10];
    }

    Database(Farmer[] f, int index){
        farmers = f;
        this.index = index;
    }

    public Farmer get(int i){
        if(i<=index){
            return farmers[i];
        }
        return null;
    }

    void addFarmer(Farmer f) {
        farmers[index++] = f;
    }

    void printFarmers() {
        for(int i=0; i<index; i++){
            System.out.println(farmers[i]);
        }
    }

    public Object clone(String typeOfCopy) throws CloneNotSupportedException { // deep copy of public
        if (typeOfCopy.equalsIgnoreCase("Deep")) {
            Farmer[] temp = new Farmer[10];
            for(int i=0; i < index; i++){
                temp[i] = new Farmer(farmers[i]);
            }
            return new Database(temp, index);
        }
        else{
            return super.clone();
        }
    }
}