interface WebsiteCollector {
   public void collect();
}

interface WebsiteAnalyzer {
   public void analyze();
}

interface PhishingDetector {
   public void detect();
}

interface ResultReporter {
   public void report();
}

interface AbstractFactory{
   public WebsiteCollector createCollector();

   public WebsiteAnalyzer createAnalyzer();

   public PhishingDetector createDetector();

   public ResultReporter createReporter();
}

class RealTimeFactory implements AbstractFactory {
   public WebsiteCollector createCollector() {
      return new RealTimeCollector();
   }

   public WebsiteAnalyzer createAnalyzer() {
      return new RealTimeAnalyzer();
   }

   public PhishingDetector createDetector() {
      return new RealTimeDetector();
   }

   public ResultReporter createReporter() {
      return new RealTimeReporter();
   }
}

class RealTimeCollector implements WebsiteCollector {
   @Override
   public void collect() {
      // implementation for collecting websites in real-time
      System.out.println("Collecting websites in real-time");
   }
}

class RealTimeAnalyzer implements WebsiteAnalyzer {
   @Override
   public void analyze() {
      // implementation for analyzing websites in real-time
      System.out.println("Analyzing websites in real-time");
   }
}

class RealTimeDetector implements PhishingDetector {
   @Override
   public void detect() {
      // implementation for detecting phishing websites in real-time
      System.out.println("Detecting phishing websites in real-time");
   }
}

class RealTimeReporter implements ResultReporter {
   @Override
   public void report() {
      // implementation for reporting results in real-time
      System.out.println("Reporting results in real-time");
   }
}

class PhishingDetectionSystem {
   private WebsiteCollector collector;
   private WebsiteAnalyzer analyzer;
   private PhishingDetector detector;
   private ResultReporter reporter;

   public PhishingDetectionSystem(AbstractFactory factory) {
      collector = factory.createCollector();
      analyzer = factory.createAnalyzer();
      detector = factory.createDetector();
      reporter = factory.createReporter();
   }

   public void startDetection() {
      collector.collect();
      analyzer.analyze();
      detector.detect();
      reporter.report();
   }
}

public class Main1 {
   public static void main(String[] args) {
      AbstractFactory factory = new RealTimeFactory();
      PhishingDetectionSystem system = new PhishingDetectionSystem(factory);
      system.startDetection();
   }
}
