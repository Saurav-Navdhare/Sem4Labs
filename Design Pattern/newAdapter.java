// Target interface that the adapter will implement
interface ThreatScanner {
    public void scanThreats(String filePath);
}

// Adaptee class that we want to use with the Target interface
class LegacyThreatScanner {
    public void scanFile(String fileName) {
        System.out.println("Scanning file for threats: " + fileName);
        // Actual legacy threat scanning code here
    }
}

// Adapter class that adapts the Adaptee to the Target interface
class LegacyThreatScannerAdapter implements ThreatScanner {
    private LegacyThreatScanner legacyScanner;

    public LegacyThreatScannerAdapter(LegacyThreatScanner scanner) {
        this.legacyScanner = scanner;
    }

    private String extractFileNameFromPath(String filePath) {
        // Code to extract file name from file path here
        return filePath.split("/")[filePath.split("/").length - 1];
    }

    public void scanThreats(String filePath) {
        String fileName = extractFileNameFromPath(filePath);
        legacyScanner.scanFile(fileName);
    }
}

// Client code that uses the Target interface to scan threats
public class newAdapter {
    public static void main(String[] args) {
        // Create instance of Adaptee
        LegacyThreatScanner legacyScanner = new LegacyThreatScanner();

        // Create instance of Adapter with Adaptee instance
        ThreatScanner adapter = new LegacyThreatScannerAdapter(legacyScanner);

        // Use Adapter to scan threats
        adapter.scanThreats("C:/Documents/secret.docx");
    }
}