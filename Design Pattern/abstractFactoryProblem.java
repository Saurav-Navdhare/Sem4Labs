interface Reader {
    void read();
}

class EmailReader implements Reader {
    @Override
    public void read() {
        System.out.println("Reading email...");
    }
}

class WebsiteReader implements Reader {
    @Override
    public void read() {
        System.out.println("Reading website...");
    }
}

interface ReaderFactory {
    Reader createReader();
}

class EmailReaderFactory implements ReaderFactory {
    @Override
    public Reader createReader() {
        return new EmailReader();
    }
}

class WebsiteReaderFactory implements ReaderFactory {
    @Override
    public Reader createReader() {
        return new WebsiteReader();
    }
}

class abstractFactoryProblem {
    public static void main(String[] args) {
        ReaderFactory emailReaderFactory = new EmailReaderFactory();
        Reader emailReader = emailReaderFactory.createReader();
        emailReader.read(); // Output: Reading email...

        ReaderFactory websiteReaderFactory = new WebsiteReaderFactory();
        Reader websiteReader = websiteReaderFactory.createReader();
        websiteReader.read(); // Output: Reading website...
    }
}
