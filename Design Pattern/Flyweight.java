import java.util.HashMap;

// This is the Flyweight factory class that manages the creation and sharing of Flyweight objects
class CybersecurityToolsFactory {
    // A HashMap to store Flyweight objects
    private static final HashMap<String, CybersecurityTool> toolsMap = new HashMap<>();

    // This method returns a Flyweight object based on the tool name
    public static CybersecurityTool getTool(String toolName) {
        // If the Flyweight object for the tool already exists, return it
        if (toolsMap.containsKey(toolName)) {
            return toolsMap.get(toolName);
        }

        // If the Flyweight object doesn't exist, create it, store it in the map, and return it
        CybersecurityTool tool = null;
        switch (toolName) {
            case "Nmap":
                tool = new NmapTool();
                break;
            case "Metasploit":
                tool = new MetasploitTool();
                break;
            case "Wireshark":
                tool = new WiresharkTool();
                break;
            // Add more cases for other cybersecurity tools
        }
        toolsMap.put(toolName, tool);
        return tool;
    }
}

// This is the Flyweight interface that all Flyweight objects implement
interface CybersecurityTool {
    public void useTool();
}

// This is one of the Flyweight objects that implements the Flyweight interface
class NmapTool implements CybersecurityTool {
    // Intrinsic state that is shared by all NmapTool objects
    private final String name = "Nmap";
    private final String description = "Network exploration tool and security scanner";

    // This method implements the useTool method of the Flyweight interface
    @Override
    public void useTool() {
        // Use the intrinsic state to perform the tool's function
        System.out.println(name + " - " + description);
        System.out.println("Running network scan...");
        // Code to run an Nmap network scan
    }
}

// This is another Flyweight object that implements the Flyweight interface
class MetasploitTool implements CybersecurityTool {
    // Intrinsic state that is shared by all MetasploitTool objects
    private final String name = "Metasploit";
    private final String description = "Penetration testing framework";

    // This method implements the useTool method of the Flyweight interface
    @Override
    public void useTool() {
        // Use the intrinsic state to perform the tool's function
        System.out.println(name + " - " + description);
        System.out.println("Running exploit...");
        // Code to run a Metasploit exploit
    }
}

// This is yet another Flyweight object that implements the Flyweight interface
class WiresharkTool implements CybersecurityTool {
    // Intrinsic state that is shared by all WiresharkTool objects
    private final String name = "Wireshark";
    private final String description = "Network protocol analyzer";

    // This method implements the useTool method of the Flyweight interface
    @Override
    public void useTool() {
        // Use the intrinsic state to perform the tool's function
        System.out.println(name + " - " + description);
        System.out.println("Capturing network traffic...");
        // Code to capture network traffic with Wireshark
    }
}

// This is the client code that uses the Flyweight factory to create and use Flyweight objects
public class Flyweight {
    public static void main(String[] args) {
        // Use the Flyweight factory to get a NmapTool object
        CybersecurityTool nmap = CybersecurityToolsFactory.getTool("Nmap");
        // Use the NmapTool object
        System.out.println("Using NmapTool:");
        nmap.useTool();

        // Use the Flyweight factory to get a MetasploitTool object
        CybersecurityTool metasploit = CybersecurityToolsFactory.getTool("Metasploit");
        // Use the MetasploitTool object
        System.out.println("\nUsing MetasploitTool:");
        metasploit.useTool();

        // Use the Flyweight factory to get a WiresharkTool object
        CybersecurityTool wireshark = CybersecurityToolsFactory.getTool("Wireshark");
        // Use the WiresharkTool object
        System.out.println("\nUsing WiresharkTool:");
        wireshark.useTool();

        // Get the same NmapTool object from the Flyweight factory again
        CybersecurityTool nmap2 = CybersecurityToolsFactory.getTool("Nmap");
        // Use the NmapTool object again
        System.out.println("\nUsing NmapTool again:");
        nmap2.useTool();
    }
}
