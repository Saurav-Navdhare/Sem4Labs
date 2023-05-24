import java.util.ArrayList;
import java.util.List;

// Subject interface that defines the methods for adding, removing and notifying observers
interface Subject {
    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObservers(String message);
}

// Concrete subject that maintains a list of observers and sends updates to them
class SecurityAlert implements Subject {
    private List<Observer> observers;
    private String alertMessage;

    public SecurityAlert() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void register(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        this.alertMessage = message;
        for (Observer observer : observers) {
            observer.update(alertMessage);
        }
    }

    public void setAlertMessage(String message) {
        notifyObservers(message);
    }
}

// Observer interface that defines the method for updating when the subject sends a message
interface Observer {
    void update(String message);
}

// Concrete observer that receives updates from the subject
class User implements Observer {
    private String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("User " + name + " received security alert: " + message);
    }
}

// Example usage
public class ObserverDesignPatternJava {
    public static void main(String[] args) {
        // Create the subject
        SecurityAlert securityAlert = new SecurityAlert();

        // Create some observers
        User alice = new User("Alice");
        User bob = new User("Bob");
        User charlie = new User("Charlie");

        // Register the observers with the subject
        securityAlert.register(alice);
        securityAlert.register(bob);
        securityAlert.register(charlie);

        // Send an alert message to all registered observers
        securityAlert.setAlertMessage("Security breach detected!");

        // Unregister an observer
        securityAlert.unregister(charlie);

        // Send another alert message to remaining registered observers
        securityAlert.setAlertMessage("Virus outbreak reported!");
    }
}
