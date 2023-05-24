// Memento class that stores the state of the security system
class SecurityMemento {
    private final String status;
    private final int errorCount;

    public SecurityMemento(String status, int errorCount) {
        this.status = status;
        this.errorCount = errorCount;
    }

    public String getStatus() {
        return status;
    }

    public int getErrorCount() {
        return errorCount;
    }
}

// Originator class that creates and stores the memento objects
class SecuritySystem {
    private String status;
    private int errorCount;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public int getErrorCount() {
        return errorCount;
    }

    // Creates a memento object with the current state of the security system
    public SecurityMemento save() {
        return new SecurityMemento(status, errorCount);
    }

    // Restores the security system's state from a memento object
    public void restore(SecurityMemento memento) {
        this.status = memento.getStatus();
        this.errorCount = memento.getErrorCount();
    }
}

// Caretaker class that manages the memento objects
class SecurityMementoManager {
    private SecurityMemento memento;

    public void saveState(SecuritySystem securitySystem) {
        this.memento = securitySystem.save();
        System.out.println("Saved security system state: " + memento.getStatus() + " - " + memento.getErrorCount() + " errors");
    }

    public void restoreState(SecuritySystem securitySystem) {
        securitySystem.restore(memento);
        System.out.println("Restored security system state: " + memento.getStatus() + " - " + memento.getErrorCount() + " errors");
    }
}

// Example usage
public class MementoDesignPattern {
    public static void main(String[] args) {
        // Create the originator
        SecuritySystem securitySystem = new SecuritySystem();

        // Create the caretaker
        SecurityMementoManager mementoManager = new SecurityMementoManager();

        // Set the security system's state
        securitySystem.setStatus("Active");
        securitySystem.setErrorCount(0);

        // Save the security system's state
        mementoManager.saveState(securitySystem);

        // Make some changes to the security system's state
        securitySystem.setStatus("Inactive");
        securitySystem.setErrorCount(3);

        // Restore the security system's state from the memento
        mementoManager.restoreState(securitySystem);
    }
}