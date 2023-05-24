import java.util.ArrayList;
import java.util.List;

// Model component
class ThreatLevel {
    private int level;
    private List<SecurityObserver> observers = new ArrayList<>();

    public void setLevel(int level) {
        this.level = level;
        notifyObservers();
    }

    public int getLevel() {
        return level;
    }

    public void addObserver(SecurityObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(SecurityObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (SecurityObserver observer : observers) {
            observer.update(this);
        }
    }
}

// View component
interface SecurityObserver {
    void update(ThreatLevel threatLevel);
}

class SecuritySystem implements SecurityObserver {
    private String name;

    public SecuritySystem(String name) {
        this.name = name;
    }

    public void update(ThreatLevel threatLevel) {
        System.out.println(name + " received update: Threat level is now " + threatLevel.getLevel());
    }
}

// Controller component
class SecurityController {
    private ThreatLevel threatLevel;

    public SecurityController(ThreatLevel threatLevel) {
        this.threatLevel = threatLevel;
    }

    public void setThreatLevel(int level) {
        threatLevel.setLevel(level);
    }
}

// Example usage
public class MVC {
    public static void main(String[] args) {
        // Create the model
        ThreatLevel threatLevel = new ThreatLevel();

        // Create the view
        SecuritySystem system1 = new SecuritySystem("System 1");
        SecuritySystem system2 = new SecuritySystem("System 2");

        // Register the view as an observer of the model
        threatLevel.addObserver(system1);
        threatLevel.addObserver(system2);

        // Create the controller
        SecurityController controller = new SecurityController(threatLevel);

        // Update the threat level through the controller
        controller.setThreatLevel(3);
    }
}
