
interface CyberSecurity {
    void protect();
 }
 
class Firewall implements CyberSecurity {
    @Override
    public void protect() {
       System.out.println("Firewall activated.");
    }
 }
 
 class Antivirus implements CyberSecurity {
    @Override
    public void protect() {
       System.out.println("Antivirus activated.");
    }
 }
 
class AdaptiveSecurity {
    private final CyberSecurity security;
 
    public AdaptiveSecurity(CyberSecurity security) {
       this.security = security;
    }
 
    public void applySecurity() {
       if (isFirewallEnabled()) {
          security.protect();
       } else if (isAntivirusEnabled()) {
          security.protect();
       } else {
          System.out.println("No security measures enabled.");
       }
    }
 
    private boolean isFirewallEnabled() {
       // Check if firewall is enabled
       return true;
    }
 
    private boolean isAntivirusEnabled() {
       // Check if antivirus is enabled
       return true;
    }
 }
 
 public class adaptiveDesignPattern {
    public static void main(String[] args) {
       CyberSecurity firewall = new Firewall();
       CyberSecurity antivirus = new Antivirus();
 
       AdaptiveSecurity adaptiveFirewall = new AdaptiveSecurity(firewall);
       AdaptiveSecurity adaptiveAntivirus = new AdaptiveSecurity(antivirus);
 
       adaptiveFirewall.applySecurity(); // Output: Firewall activated.
       adaptiveAntivirus.applySecurity(); // Output: Antivirus activated.
    }
 }
 