interface PhishingDetector {
    void detect();
}

class EmailPhishingDetector implements PhishingDetector {
    public void detect() {
        // code for email phishing detection
        System.out.println("Email Phishing Attack Detector");
    }
}

class WebsitePhishingDetector implements PhishingDetector {
    public void detect() {
        // code for website phishing detection
        System.out.println("Website Phishing Attack Detector");
    }
}

class PhishingDetectorFactory {
    public PhishingDetector createDetector(String type) {
        if(type.equalsIgnoreCase("EMAIL")) {
            return new EmailPhishingDetector();
        } else if(type.equalsIgnoreCase("WEBSITE")) {
            return new WebsitePhishingDetector();
        }
        return null;
    }
}

//Test Class
public class PhishingAttackFactory{
    public static void main(String[] args) {
        PhishingDetectorFactory factory = new PhishingDetectorFactory();
        PhishingDetector detector = factory.createDetector("EMAIL");
        detector.detect();
    }
}