interface DataProcessor {
    void processData(String data);
}

class DefaultDataProcessor implements DataProcessor {
    @Override
    public void processData(String data) {
        System.out.println("Processing data: " + data);
    }
}

class EncryptedDataProcessor implements DataProcessor {
    private DataProcessor wrapped;

    public EncryptedDataProcessor(DataProcessor wrapped) {
        this.wrapped = wrapped;
    }
    
    private String encrypt(String data) {
        // perform encryption
        return "encrypted-" + data;
    }

    @Override
    public void processData(String data) {
        System.out.println("Encrypting data: " + data);
        // perform encryption
        String encryptedData = encrypt(data);
        wrapped.processData(encryptedData);
    }
}

public class decoratorDesignPattern {
    public static void main(String[] args) {
        // Create an instance of the default data processor
        DataProcessor processor = new DefaultDataProcessor();

        // Wrap the default data processor with encryption decorator
        processor = new EncryptedDataProcessor(processor);

        // Process data
        processor.processData("sensitive data");
    }
}
