import java.util.Iterator;
import java.util.List;

// Define a CyberSecurityIterator interface for iterating over a collection of CyberSecurityObjects
// interface CyberSecurityIterator {
//     boolean hasNext();
//     CyberSecurityObject next();
// }

// Define a CyberSecurityObject class to represent a single CyberSecurity object
class CyberSecurityObject {
    private String name;
    private String type;
    private String description;

    public CyberSecurityObject(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    // Getters and setters for the class fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

// Define a CyberSecurityCollection class to represent a collection of CyberSecurity objects
class CyberSecurityCollection implements Iterable<CyberSecurityObject> {
    private List<CyberSecurityObject> collection;

    public CyberSecurityCollection(List<CyberSecurityObject> collection) {
        this.collection = collection;
    }

    // Implement the Iterable interface by returning an instance of the CyberSecurityIterator
    @Override
    public Iterator<CyberSecurityObject> iterator() {
        return new CyberSecurityIteratorImpl();
    }

    // Define an implementation of the CyberSecurityIterator interface for iterating over the CyberSecurityCollection
    private class CyberSecurityIteratorImpl implements Iterator<CyberSecurityObject> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < collection.size();
        }

        @Override
        public CyberSecurityObject next() {
            return collection.get(currentIndex++);
        }
    }
}

// Example usage of the CyberSecurityIterator pattern
public class iteratorDesignPattern {
    public static void main(String[] args) {
        List<CyberSecurityObject> collection = List.of(
            new CyberSecurityObject("Firewall", "Network Security", "A hardware or software solution that helps protect a computer network from unauthorized access."),
            new CyberSecurityObject("Antivirus software", "Endpoint Security", "A type of software that detects, prevents, and removes malicious software (malware) from a computer."),
            new CyberSecurityObject("Intrusion Detection System", "Network Security", "A type of security software that monitors network traffic for signs of a cyber attack.")
        );
        CyberSecurityCollection cyberSecurityCollection = new CyberSecurityCollection(collection);

        // Iterate over the collection using the CyberSecurityIterator
        for (CyberSecurityObject obj : cyberSecurityCollection) {
            System.out.println(obj.getName() + ": " + obj.getDescription());
        }
    }
}
